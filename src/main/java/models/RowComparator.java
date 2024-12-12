package models;

import java.util.Comparator;
import java.util.List;

public class RowComparator implements Comparator<CharInGrid> {

    public RowComparator() { }

    @Override
    public int compare(CharInGrid a, CharInGrid b) {
        return Integer.compare(a.r, b.r);
    }
}