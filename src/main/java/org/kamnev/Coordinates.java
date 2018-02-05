package org.kamnev;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates newCoordinatesAfterMoveIn(Direction direction) {
        return new Coordinates(x + direction.getDeltaX(), y + direction.getDeltaY());
    }

    public boolean beyondTheWorld(int worldWidth, int worldHeight) {
        return x > worldWidth
                || y > worldHeight
                || x < 0
                || y < 0;
    }
}
