package models;

public class Coords {
    public int r;
    public int c;
    public char value = '?';

    public Coords(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Coords(int r, int c, char value) {
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
        if (obj instanceof Coords newCoords) {
            return newCoords.r == this.r &&
                    newCoords.c == this.c &&
                    newCoords.value == this.value;
        }
        return false;
    }
}
