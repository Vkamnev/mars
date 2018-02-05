package org.kamnev;

public enum Direction {

    N(0, 0, 1),
    E(1, 1, 0),
    S(2, 0, -1),
    W(3, -1, 0);

    private static final int DIRECTIONS_SIZE = Direction.values().length;

    private int index;
    private int deltaX;
    private int deltaY;

    Direction(int index, int deltaX, int deltaY) {
        this.index = index;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getIndex() {
        return index;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public Direction turnRight() {
        return getRotatedDirection(1);
    }

    public Direction turnLeft() {
        return getRotatedDirection(-1);
    }

    private Direction getRotatedDirection(int delta) {
        int index = (DIRECTIONS_SIZE + this.getIndex() + delta) % DIRECTIONS_SIZE;
        return Direction.values()[index];
    }
}
