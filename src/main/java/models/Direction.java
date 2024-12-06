package models;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UNKNOWN, NULL;

    public static Direction convertChar(char c) {
        if (c == '^') return Direction.UP;
        if (c == '>') return Direction.RIGHT;
        if (c == '<') return Direction.LEFT;
        if (c == 'v') return Direction.DOWN;
        return Direction.UNKNOWN;
    }
}
