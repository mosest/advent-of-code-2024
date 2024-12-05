package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FileHelper {

    public static final String PATH_TO_INPUT_FILES = "src/main/resources/";

    public static String readIntoString(String fileName) {

        StringBuilder result = new StringBuilder();

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + fileName));

            while (scanner.hasNext()) {
                result.append(scanner.next());
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + fileName);
        }

        return result.toString();
    }

    public static String[] readIntoArray_String(String fileName, int numLines) {

        String[] result = new String[numLines];

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + fileName));

            for (int i = 0; i < numLines; i++) {
                result[i] = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + fileName);
        }

        return result;
    }

    public static int[][] readIntoArray_Int_2D(String fileName, int numLines) {

        int[][] array = new int[numLines][];

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + fileName));

            int r = 0;

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                array[r] = Arrays.stream(line)
                        .flatMapToInt(str ->
                                IntStream.of(Integer.parseInt(str)))
                        .toArray();
                r++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + fileName);
        }

        return array;
    }


    public static int[][] readIntoArray_Int_2D_Sideways(String fileName, int numLines, int numPiecesOnEachLine) {

        int[][] array = new int[numPiecesOnEachLine][numLines];

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + fileName));

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

    public static char[][] readIntoArray_Char_2D(String fileName, int numLines) {

        char[][] array = new char[numLines][];

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + fileName));

            int r = 0;
            while (scanner.hasNextLine()) {
                array[r] = scanner.nextLine().toCharArray();
                r++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + fileName);
        }

        return array;
    }

    public static char[][] readIntoArray_Char_2D_Sideways(String fileName, int numLines, int numPiecesOnEachLine) {

        char[][] array = new char[numPiecesOnEachLine][numLines];

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + fileName));

            int c = 0;
            while (scanner.hasNextLine()) {

                char[] line = scanner.nextLine().toCharArray();
                for (int i = 0; i < line.length; i++) {
                    array[i][c] = line[i];
                }

                c++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + fileName);
        }

        return array;
    }
}
