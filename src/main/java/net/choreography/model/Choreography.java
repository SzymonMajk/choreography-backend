package net.choreography.model;

import java.util.*;
import java.util.stream.Collectors;

import static net.choreography.model.FigureRepository.PREPARATION_STEP;

public class Choreography {
    public static final String FIGURE_CANNOT_BE_CONNECTED = "Figure cannot be connected";
    private final DirectedFigure preparationStep;
    private final List<DirectedFigure> choreography = new LinkedList<>();
    private final Map<String, Figure> figures;

    public Choreography(Map<String, Figure> figures) {
        this.figures = figures;
        preparationStep = new DirectedFigure(figures.get(PREPARATION_STEP), DirectedFigure.Direction.FRONT_LEFT);
    }

    public Set<Figure> listPossibleConnections() {
        if (choreography.isEmpty()) {
            return listPossibleConnectionsWithFigure(preparationStep);
        } else {
            return listPossibleConnectionsWithFigure(getLastFigure());
        }
    }

    public void connect(Figure figure) {
        if (choreography.isEmpty()) {
            connectToPreviousFigure(preparationStep, figure);
        } else {
            connectToPreviousFigure(getLastFigure(), figure);
        }
    }

    private Set<Figure> listPossibleConnectionsWithFigure(DirectedFigure figure) {
        return figures.values().stream()
                .filter(figure::canConnect)
                .collect(Collectors.toSet());
    }

    private void connectToPreviousFigure(DirectedFigure previousFigure, Figure nextFigure) {
        if (previousFigure.canConnect(nextFigure)) {
            choreography.add(new DirectedFigure(nextFigure, previousFigure.getFinalDirection()));
        } else {
            throw new IllegalArgumentException(FIGURE_CANNOT_BE_CONNECTED);
        }
    }

    public List<DirectedFigure> calculateChoreography(int choreographyDuration) {
        List<DirectedFigure> calculatedChoreography = new ArrayList<>();
        calculatedChoreography.add(preparationStep);
        int figuresDuration = preparationStep.getDuration();
        int figureIndex = 0;

        while (choreographyDuration > figuresDuration) {
            DirectedFigure currentFigure = choreography.get(figureIndex % choreography.size());
            calculatedChoreography.add(currentFigure);
            figuresDuration += currentFigure.getDuration();
            figureIndex++;
        }

        return calculatedChoreography;
    }

    private DirectedFigure getLastFigure() {
        return choreography.get(choreography.size() - 1);
    }
}
