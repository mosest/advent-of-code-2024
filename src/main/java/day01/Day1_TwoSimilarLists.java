package day01;

import util.ArrayHelper;
import util.FileHelper;

import java.util.Arrays;
import java.util.HashMap;

import static java.util.Arrays.sort;

public class Day1_TwoSimilarLists {

    private String inputFileName = "day1.txt";
    private int inputLineCount = 1000;

    public Day1_TwoSimilarLists(boolean practice) {
        if (practice) {
            inputFileName = inputFileName.replaceAll("\\.", "-practice.");
            inputLineCount = 6;
        }
    }

    public int part1_GetDistanceBetweenLists() {

        int[][] input = FileHelper.readIntoArray_Int_2D_Sideways(inputFileName, inputLineCount, 2);
        ArrayHelper.printArray_Int_2D(input);

        sort(input[0]); // left  column of numbers
        sort(input[1]); // right column of numbers

        int sum = 0;
        for (int i = 0; i < inputLineCount; i++) {
            sum += Math.abs(input[0][i] - input[1][i]);
        }

        return sum;
    }

    public int part2_GetSimilarityScore() {

        int[][] input = FileHelper.readIntoArray_Int_2D_Sideways(inputFileName, inputLineCount, 2);
        ArrayHelper.printArray_Int_2D(input);

        // Hashmap where key is n:          a number in the right column of the input,
        // and the value is frequency(n):   the number of times that n shows up in the right column of the input
        HashMap<Integer, Integer> frequencies = populateFrequencies(input[1]);

        return Arrays.stream(input[0])
                .map(leftNum ->
                        leftNum * frequencies.getOrDefault(leftNum, 0)) // similarity score
                .sum();
    }

    private static HashMap<Integer, Integer> populateFrequencies(int[] searchList) {

        HashMap<Integer, Integer> result = new HashMap<>();

        for (int number : searchList) {
            if (result.containsKey(number)) {
                result.put(number, result.get(number) + 1);
            } else {
                result.put(number, 1);
            }
        }

        return result;
    }
}
