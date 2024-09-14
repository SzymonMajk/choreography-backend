package net.choreography.model;

import java.util.Collections;
import java.util.Set;

public class Figure {
    protected final String name;
    protected final Position start;
    protected final Position end;
    protected final int rotation;
    protected final int duration;
    protected final Set<String> decorations;//TODO same for footwork, actions etc.

    Figure(String name, Position start, Position end, int rotation, int duration, Set<String> decorations) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.rotation = rotation;
        this.duration = duration;
        this.decorations = decorations;
    }

    Figure(String name, Position start, Position end, int rotation, int duration) {
        this(name, start, end, rotation, duration, Collections.emptySet());
    }

    int getDuration() {
        return duration;
    }

    Set<String> getDecorations() {
        return decorations;
    }

    boolean canConnect(Figure other) {
        return end.canConnect(other.start);
    }

    @Override
    public String toString() {
        return String.format("%s: start position - %s -- end position - %s -- duration - %d", name, start, end, duration);
    }
}