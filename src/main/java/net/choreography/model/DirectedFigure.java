package net.choreography.model;

public class DirectedFigure extends Figure {
    private final Direction finalDirection;

    DirectedFigure(Figure figure, Direction initialDirection) {
        super(figure.name, figure.start, figure.end, figure.rotation, figure.duration, figure.getDecorations());
        finalDirection = rotate(initialDirection, figure.rotation);
    }

    public Direction getFinalDirection() {
        return finalDirection;
    }

    private Direction rotate(Direction direction, int rotation) {
        Direction[] directions = Direction.values();
        return directions[(direction.ordinal() + rotation) % directions.length];
    }

    @Override
    public String toString() {
        return String.format("%s: start position - %s -- end position - %s -- final direction - %s -- duration - %d", name, start, end, finalDirection, duration);
    }

    enum Direction {
        FRONT, FRONT_RIGHT, RIGHT, BACK_RIGHT, BACK, BACK_LEFT, LEFT, FRONT_LEFT
    }
}
