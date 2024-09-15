package net.choreography.model;

import java.util.Map;

public class FigureRepository {
    static final String PREPARATION_STEP = "Preparation Step";

    public Map<String, Figure> getWaltzFigures() {
        return Map.of(
                PREPARATION_STEP,
                new Figure(
                        "Waltz Preparation Step",
                        new Position(
                                Position.Embrace.CLOSED,
                                Position.StandingLeg.RIGHT,
                                Position.StandingLeg.LEFT
                        ),
                        new Position(
                                Position.Embrace.CLOSED,
                                Position.StandingLeg.LEFT,
                                Position.StandingLeg.RIGHT
                        ),
                        0, 2),
                "Natural Turn #1 part",
                new Figure("Natural Turn #1 part",
                        new Position(
                                Position.Embrace.CLOSED,
                                Position.StandingLeg.LEFT,
                                Position.StandingLeg.RIGHT
                        ),
                        new Position(
                                Position.Embrace.CLOSED,
                                Position.StandingLeg.RIGHT,
                                Position.StandingLeg.LEFT
                        ),
                        2, 1),
                "Natural Turn #2 part",
                new Figure("Natural Turn #2 part",
                        new Position(
                                Position.Embrace.CLOSED,
                                Position.StandingLeg.RIGHT,
                                Position.StandingLeg.LEFT
                        ),
                        new Position(
                                Position.Embrace.CLOSED,
                                Position.StandingLeg.LEFT,
                                Position.StandingLeg.RIGHT
                        ),
                        2, 1)
        );
    }
}
