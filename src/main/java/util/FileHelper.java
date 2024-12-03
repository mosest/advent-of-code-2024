package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHelper {

    private static String pathToInputFiles = "src/main/resources/";

    public static int[][] readIntoArray_Int_2D_Sideways(String fileName, int numLines, int numPiecesOnEachLine) {

        int[][] array = new int[numPiecesOnEachLine][numLines];

        try {
            Scanner scanner = new Scanner(new File(pathToInputFiles + fileName));

            int r = 0;
            int c = 0;

            while (scanner.hasNextInt()) {

                // Add to array
                array[r][c] = scanner.nextInt();

                // Advance!
                r++;
                if (r == numPiecesOnEachLine) {
                    r = 0;
                    c++;
                }

                if (c == numLines) {
                    c = 0;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + fileName);
        }

        return array;
    }
}
