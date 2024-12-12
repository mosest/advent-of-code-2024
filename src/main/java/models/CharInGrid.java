package models;

import java.util.Objects;

public class CharInGrid {
    public int r;
    public int c;
    public char value = '?';

    public CharInGrid(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public CharInGrid(char value, int r, int c) {
        this.r = r;
        this.c = c;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Coords(" + r + "," + c + "): " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharInGrid newCoords) {
            return newCoords.r == this.r &&
                    newCoords.c == this.c &&
                    newCoords.value == this.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c, value);
    }
}
