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

    public static void printArray_Boolean(boolean[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i]) System.out.println("[" + i + "]: TRUE");
            else System.out.println("[" + i + "]: FALSE");
        }
    }
}
