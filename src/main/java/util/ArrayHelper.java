package util;

public class ArrayHelper {

    public static void printArray_Int_2D(int[][] array) {

        for (int[] row : array) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
