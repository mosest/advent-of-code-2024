package day04;

import models.Day;
import org.apache.commons.lang3.StringUtils;
import util.ArrayHelper;
import util.FileHelper;

import java.util.List;

public class Day4 extends Day {

    private final char[][] INPUT;
    private final char[][] INPUT_SIDEWAYS;

    public Day4(boolean practice) {
        super("day4.txt", 140, 10, practice);
        INPUT = FileHelper.readIntoArray_Char_2D(INPUT_FILE_NAME, INPUT_NUM_LINES);
        INPUT_SIDEWAYS = FileHelper.readIntoArray_Char_2D_Sideways(INPUT_FILE_NAME, INPUT_NUM_LINES, INPUT_NUM_LINES);
    }

    public int part1() {

        List<String> diagonalInput = ArrayHelper.transform2DCharIntoStringList_Diagonally_ForwardSlash(INPUT);
        List<String> diagonalInputBackslash = ArrayHelper.transform2DCharIntoStringList_Diagonally_Backslash(INPUT);

        int count = countHorizontalMatches(INPUT);
        count += countHorizontalMatches(INPUT_SIDEWAYS);
        count += countHorizontalMatches(diagonalInput);
        count += countHorizontalMatches(diagonalInputBackslash);

        return count;
    }

    /*
    Count the number of times a "MAS" shows up diagonally (forwards or backwards), like this:
        M M
         A
        S S
    */
    public int part2() {
        return traverseArrayAndInspectMASes(INPUT);
    }

    //region Helpers

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

    private static int traverseArrayAndInspectMASes(char[][] charArray) {

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
