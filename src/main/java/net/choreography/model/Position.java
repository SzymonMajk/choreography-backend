package net.choreography.model;

import java.util.Objects;

class Position {
    private final Embrace embrace;
    private final StandingLeg leaderStandingLeg;
    private final StandingLeg followerStandingLeg;

    Position(Embrace embrace, StandingLeg leaderStandingLeg, StandingLeg followerStandingLeg) {
        this.embrace = embrace;
        this.leaderStandingLeg = leaderStandingLeg;
        this.followerStandingLeg = followerStandingLeg;
    }

    boolean canConnect(Position other) {
        return Objects.equals(this, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return embrace == position.embrace && leaderStandingLeg == position.leaderStandingLeg &&
                followerStandingLeg == position.followerStandingLeg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(embrace, leaderStandingLeg, followerStandingLeg);
    }

    @Override
    public String toString() {
        return String.format("{Embrace: %s, Leader leg: %s, Follower leg: %s}",
                embrace, leaderStandingLeg, followerStandingLeg);
    }

    enum Embrace {
        CLOSED, PROMENADE, OPPOSITE, SHADOW, CROSS_LEFT, CROSS_RIGHT, LINE_LEFT, LINE_RIGHT
    }

    enum StandingLeg {
        LEFT, RIGHT, BETWEEN
    }
}