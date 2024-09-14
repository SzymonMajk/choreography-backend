package net.choreography.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Choreography {
    public static final String FIGURE_CANNOT_BE_CONNECTED = "Figure cannot be connected";
    private final List<DirectedFigure> choreography = new LinkedList<>();
    private final Set<Figure> figures;

    public Choreography(DirectedFigure preparationStep, Set<Figure> figures) {
        this.figures = figures;
        choreography.add(preparationStep);
    }

    public Set<Figure> listPossibleConnections() {
        Figure lastFigure = getLastFigure();
        return figures.stream()
                .filter(lastFigure::canConnect)
                .collect(Collectors.toSet());
    }

    public void connect(Figure figure) {
        DirectedFigure lastFigure = getLastFigure();

        if (lastFigure.canConnect(figure)) {
            choreography.add(new DirectedFigure(figure, lastFigure.getFinalDirection()));
        } else {
            throw new IllegalArgumentException(FIGURE_CANNOT_BE_CONNECTED);
        }
    }

    public ListIterator<DirectedFigure> getChoreographyIterator() {
        return choreography.listIterator();
    }

    private DirectedFigure getLastFigure() {
        return choreography.get(choreography.size() - 1);
    }
}
