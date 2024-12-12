package models;

import java.util.Comparator;

public class ColumnComparator implements Comparator<CharInGrid> {

    public ColumnComparator() { }

    @Override
    public int compare(CharInGrid a, CharInGrid b) {
        return Integer.compare(a.c, b.c);
    }
}