package net.choreography.model;

import org.junit.jupiter.api.Test;

import java.util.ListIterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChoreographyTest {
    private static final Position TEST_FIRST_POSITION = new Position(Position.Embrace.CLOSED, Position.Sway.LEFT);
    private static final Position TEST_SECOND_POSITION = new Position(Position.Embrace.OPPOSITE, Position.Sway.RIGHT);
    private static final String TEST_FIRST_FIGURE_NAME = "Test 1";
    private static final String TEST_SECOND_FIGURE_NAME = "Test 2";
    private final Figure firstFigure = new Figure(TEST_FIRST_FIGURE_NAME, TEST_FIRST_POSITION, TEST_SECOND_POSITION, 2, 1);
    private final Figure secondFigure = new Figure(TEST_SECOND_FIGURE_NAME, TEST_SECOND_POSITION, TEST_FIRST_POSITION, 1, 1);

    private final DirectedFigure testPreparationStep = new DirectedFigure(firstFigure, DirectedFigure.Direction.FRONT_LEFT);

    @Test
    void shouldListOnlyPossibleConnectionsStartingFromFirstFigure() {
        Choreography choreography = new Choreography(testPreparationStep, Set.of(firstFigure, secondFigure));

        Set<Figure> possibleConnections = choreography.listPossibleConnections();
        assertEquals(1, possibleConnections.size());
        assertFalse(possibleConnections.contains(firstFigure));
        assertTrue(possibleConnections.contains(secondFigure));
    }

    @Test
    void shouldAddPossibleConnectionAndConsiderItAsLastWithCorrectRotation() {
        Choreography choreography = new Choreography(testPreparationStep, Set.of(firstFigure, secondFigure));

        choreography.connect(secondFigure);

        Set<Figure> possibleConnections = choreography.listPossibleConnections();
        ListIterator<DirectedFigure> choreographyIterator = choreography.getChoreographyIterator();
        assertEquals(1, possibleConnections.size());
        assertTrue(possibleConnections.contains(firstFigure));
        assertFalse(possibleConnections.contains(secondFigure));
        assertTrue(choreographyIterator.hasNext());
        assertFigure(firstFigure, DirectedFigure.Direction.FRONT_RIGHT, choreographyIterator.next());
        assertTrue(choreographyIterator.hasNext());
        assertFigure(secondFigure, DirectedFigure.Direction.RIGHT, choreographyIterator.next());
        assertFalse(choreographyIterator.hasNext());
    }

    private void assertFigure(Figure expectedFigure, DirectedFigure.Direction expectedDirection, DirectedFigure actual) {
        assertEquals(expectedFigure.name, actual.name);
        assertEquals(expectedFigure.start, actual.start);
        assertEquals(expectedFigure.end, actual.end);
        assertEquals(expectedFigure.rotation, actual.rotation);
        assertEquals(expectedFigure.duration, actual.duration);
        assertTrue(expectedFigure.getDecorations().containsAll(actual.getDecorations()));
        assertTrue(actual.getDecorations().containsAll(expectedFigure.getDecorations()));
        assertEquals(expectedDirection, actual.getFinalDirection());
    }
}