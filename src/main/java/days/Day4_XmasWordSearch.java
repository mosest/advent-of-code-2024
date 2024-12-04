package days;

import org.apache.commons.lang3.StringUtils;
import util.ArrayHelper;
import util.FileHelper;

import java.util.List;

public class Day4_XmasWordSearch {

    private static final String FILENAME = "day4.txt";
    private static final int NUM_LINES = 140;

    //region Day 1

    public static int countXmas() {

        char[][] input = FileHelper.readIntoArray_Char_2D(FILENAME, NUM_LINES);
        char[][] sidewaysInput = FileHelper.readIntoArray_Char_2D_Sideways(FILENAME, NUM_LINES, NUM_LINES);

        List<String> diagonalInput = ArrayHelper.transform2DCharIntoStringList_Diagonally_ForwardSlash(input);
        List<String> diagonalInputBackslash = ArrayHelper.transform2DCharIntoStringList_Diagonally_Backslash(input);

        System.out.println();
        System.out.println();
        ArrayHelper.printList_String(diagonalInput);

        System.out.println();
        System.out.println();
        ArrayHelper.printList_String(diagonalInputBackslash);

        int count = countHorizontalMatches(input);
        count += countHorizontalMatches(sidewaysInput);
        count += countHorizontalMatches(diagonalInput);
        count += countHorizontalMatches(diagonalInputBackslash);

        return count;
    }

    private static int countHorizontalMatches(char[][] array) {

        int sum = 0;

        for (char[] chars : array) {
            String line = new String(chars);

            sum += StringUtils.countMatches(line, "XMAS");
            sum += StringUtils.countMatches(line, "SAMX");
        }

        return sum;
    }


    private static int countHorizontalMatches(List<String> strings) {

        int sum = 0;

        for (String line : strings) {
            sum += StringUtils.countMatches(line, "XMAS");
            sum += StringUtils.countMatches(line, "SAMX");
        }

        return sum;
    }

    //endregion

    //region Day 2

    /*
    Count the number of times a "MAS" shows up diagonally (forwards or backwards), like this:
        M M
         A
        S S
    */
    public static int countMasXes() {
        char[][] input = FileHelper.readIntoArray_Char_2D(FILENAME, NUM_LINES);

        int count = traverseArrayAndInspectAs(input);

        return count;
    }

    private static int traverseArrayAndInspectAs(char[][] charArray) {

        int sum = 0;

        for (int r = 1; r < charArray.length - 1; r++) {
            for (int c = 1; c < charArray[r].length - 1; c++) {

                if (charArray[r][c] != 'A') continue;

                char nw = charArray[r-1][c-1];
                char ne = charArray[r-1][c+1];
                char sw = charArray[r+1][c-1];
                char se = charArray[r+1][c+1];

                if (weAreInSituation1(nw, ne, sw, se)) sum++;
                else if (weAreInSituation2(nw, ne, sw, se)) sum++;
                else if (weAreInSituation3(nw, ne, sw, se)) sum++;
                else if (weAreInSituation4(nw, ne, sw, se)) sum++;
            }
        }

        return sum;
    }

    private static boolean weAreInSituation1(char nw, char ne, char sw, char se) {
        if (nw == 'M' &&
                ne == 'M' &&
                sw == 'S' &&
                se == 'S')
            return true;
        return false;
    }

    private static boolean weAreInSituation2(char nw, char ne, char sw, char se) {
        if (nw == 'M' &&
                ne == 'S' &&
                sw == 'M' &&
                se == 'S')
            return true;
        return false;
    }

    private static boolean weAreInSituation3(char nw, char ne, char sw, char se) {
        if (nw == 'S' &&
                ne == 'M' &&
                sw == 'S' &&
                se == 'M')
            return true;
        return false;
    }

    private static boolean weAreInSituation4(char nw, char ne, char sw, char se) {
        if (nw == 'S' &&
                ne == 'S' &&
                sw == 'M' &&
                se == 'M')
            return true;
        return false;
    }

    //endregion
}
