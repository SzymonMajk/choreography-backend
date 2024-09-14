package net.choreography.model;

import java.util.Set;

public class FigureRepository {
    private final Position waltzStartPosition = new Position(Position.Embrace.CLOSED, Position.Sway.NONE);

    public DirectedFigure getWaltzPreparationStep() {
        return new DirectedFigure(new Figure("Waltz Preparation Step", waltzStartPosition, waltzStartPosition, 2, 2), DirectedFigure.Direction.FRONT_LEFT);
    }

    public Set<Figure> getWaltzFigures() {
        return Set.of(
                getWaltzPreparationStep(),
                new Figure("Natural Turn #1 part",
                        new Position(Position.Embrace.CLOSED, Position.Sway.RIGHT),
                        new Position(Position.Embrace.CLOSED, Position.Sway.LEFT),
                        2, 1),
                new Figure("Natural Turn #2 part",
                        new Position(Position.Embrace.CLOSED, Position.Sway.LEFT),
                        new Position(Position.Embrace.CLOSED, Position.Sway.RIGHT),
                        2, 1)
        );
    }
}
