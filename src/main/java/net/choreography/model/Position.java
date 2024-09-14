package net.choreography.model;

import java.util.Objects;

class Position {
    private final Embrace embrace;
    private final Sway sway;

    Position(Embrace embrace, Sway sway) {
        this.embrace = embrace;
        this.sway = sway;
    }

    boolean canConnect(Position other) {
        return Objects.equals(this, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return embrace == position.embrace && sway == position.sway;
    }

    @Override
    public int hashCode() {
        return Objects.hash(embrace, sway);
    }

    @Override
    public String toString() {
        return String.format("{Embrace: %s, Sway %s}", embrace, sway);
    }

    enum Embrace {
        CLOSED, PROMENADE, OPPOSITE, SHADOW, CROSS_LEFT, CROSS_RIGHT, LINE_LEFT, LINE_RIGHT
    }

    enum Sway {
        NONE, LEFT, RIGHT;
    }

}