package util;

import java.util.ArrayList;
import java.util.List;

public class ArrayHelper {

    public static void printArray_Int_2D(int[][] array) {

        for (int[] row : array) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public static void printArray_Boolean(boolean[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i]) System.out.println("[" + i + "]: TRUE");
            else System.out.println("[" + i + "]: FALSE");
        }
    }

    public static void printArray_String(String[] array) {

        for (String s : array) {
            System.out.println(s);
        }
    }

    public static void printArray_Int(int[] array) {

        for (int n : array) {
            System.out.println(n);
        }
    }

    public static void printArray_Char_2D(char[][] array) {

        for (char[] row : array) {
            for (char col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public static void printList_String(List<String> strings) {

        for (String str : strings) {
            System.out.println(str);
        }
    }

    public static List<String> transform2DCharIntoStringList_Diagonally_ForwardSlash(char[][] chars) {

        List<String> diagonalStrings = new ArrayList<>();

        // start at SW and go along left border, then top border, to NE
        for (int r = 0; r < chars.length; r++) {
            for (int c = 0; c < chars[r].length; c++) {

                if (r == 0 || c == 0) {

                    // If we're at the top row or the leftmost column, go get that diagonal.
                    StringBuilder diagonalString = new StringBuilder();

                    int dr = r;
                    int dc = c;

                    do {
                        diagonalString.append(chars[dr][dc]);
                        dr++;
                        dc++;
                    } while (dr < chars.length && dc < chars[dr].length);

                    diagonalStrings.add(diagonalString.toString());
                }
            }
        }

        return diagonalStrings;
    }

    public static List<String> transform2DCharIntoStringList_Diagonally_Backslash(char[][] chars) {

        List<String> diagonalStrings = new ArrayList<>();

        // start at SE and go along bottom border, then left border, to NW
        for (int r = 0; r < chars.length; r++) {
            for (int c = 0; c < chars[r].length; c++) {

                if (r == chars.length - 1 || c == 0) {

                    // If we're at the top row or the leftmost column, go get that diagonal.
                    StringBuilder diagonalString = new StringBuilder();

                    int dr = r;
                    int dc = c;

                    do {
                        diagonalString.append(chars[dr][dc]);
                        dr--;
                        dc++;
                    } while (dr >= 0 && dc < chars[dr].length);

                    diagonalStrings.add(diagonalString.toString());
                }
            }
        }

        return diagonalStrings;
    }
}
