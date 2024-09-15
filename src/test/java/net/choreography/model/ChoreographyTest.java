package net.choreography.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.choreography.model.FigureRepository.PREPARATION_STEP;
import static org.junit.jupiter.api.Assertions.*;

class ChoreographyTest {
    private static final Position TEST_FIRST_POSITION = new Position(Position.Embrace.CLOSED, Position.StandingLeg.LEFT, Position.StandingLeg.RIGHT);
    private static final Position TEST_SECOND_POSITION = new Position(Position.Embrace.OPPOSITE, Position.StandingLeg.LEFT, Position.StandingLeg.RIGHT);
    private static final String TEST_FIGURE_NAME = "Test 2";
    private final Figure firstFigure = new Figure(PREPARATION_STEP, TEST_FIRST_POSITION, TEST_SECOND_POSITION, 2, 1);
    private final Figure secondFigure = new Figure(TEST_FIGURE_NAME, TEST_SECOND_POSITION, TEST_FIRST_POSITION, 1, 1);
    private final Map<String, Figure> testFigures = Map.of(PREPARATION_STEP, firstFigure, TEST_FIGURE_NAME, secondFigure);

    @Test
    void shouldListOnlyPossibleConnectionsStartingFromFirstFigure() {
        Choreography choreography = new Choreography(testFigures);

        Set<Figure> possibleConnections = choreography.listPossibleConnections();
        assertEquals(1, possibleConnections.size());
        assertFalse(possibleConnections.contains(firstFigure));
        assertTrue(possibleConnections.contains(secondFigure));
    }

    @Test
    void shouldAddPossibleConnectionAndConsiderItAsLastWithCorrectRotation() {
        Choreography choreography = new Choreography(testFigures);

        choreography.connect(secondFigure);

        Set<Figure> possibleConnections = choreography.listPossibleConnections();
        List<DirectedFigure> calculatedChoreography = choreography.calculateChoreography(2);
        assertEquals(1, possibleConnections.size());
        assertTrue(possibleConnections.contains(firstFigure));
        assertFalse(possibleConnections.contains(secondFigure));
        assertEquals(2, calculatedChoreography.size());
        assertFigure(firstFigure, DirectedFigure.Direction.FRONT_RIGHT, calculatedChoreography.get(0));
        assertFigure(secondFigure, DirectedFigure.Direction.RIGHT, calculatedChoreography.get(1));
    }

    @Test
    void shouldShouldLoopChoreographyWhenChoreographyDurationLongerThanFiguresDuration() {
        Choreography choreography = new Choreography(testFigures);

        choreography.connect(secondFigure);
        choreography.connect(firstFigure);

        List<DirectedFigure> calculatedChoreography = choreography.calculateChoreography(4);
        assertEquals(4, calculatedChoreography.size());
        assertFigure(firstFigure, DirectedFigure.Direction.FRONT_RIGHT, calculatedChoreography.get(0));
        assertFigure(secondFigure, DirectedFigure.Direction.RIGHT, calculatedChoreography.get(1));
        assertFigure(firstFigure, DirectedFigure.Direction.BACK, calculatedChoreography.get(2));
        assertFigure(secondFigure, DirectedFigure.Direction.RIGHT, calculatedChoreography.get(3));
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