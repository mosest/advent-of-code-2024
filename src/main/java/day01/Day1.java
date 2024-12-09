package day01;

import models.Day;
import util.ArrayHelper;
import util.FileHelper;

import java.util.Arrays;
import java.util.HashMap;

import static java.util.Arrays.sort;

public class Day1 extends Day {

    private final int[][] INPUT;

    public Day1(boolean practice) {
        super("day1.txt", 1000, 6, practice);
        INPUT = FileHelper.readIntoArray_Int_2D_Sideways(INPUT_FILE_NAME, INPUT_NUM_LINES, 2);
    }

    public int part1() {

        ArrayHelper.printArray_Int_2D(INPUT);

        sort(INPUT[0]); // left  column of numbers
        sort(INPUT[1]); // right column of numbers

        int sum = 0;
        for (int i = 0; i < INPUT_NUM_LINES; i++) {
            sum += Math.abs(INPUT[0][i] - INPUT[1][i]);
        }

        return sum;
    }

    public int part2() {

        ArrayHelper.printArray_Int_2D(INPUT);

        // Hashmap where key is n:          a number in the right column of the input,
        // and the value is frequency(n):   the number of times that n shows up in the right column of the input
        HashMap<Integer, Integer> frequencies = populateFrequencies(INPUT[1]);

        return Arrays.stream(INPUT[0])
                .map(leftNum ->
                        leftNum * frequencies.getOrDefault(leftNum, 0)) // similarity score
                .sum();
    }

    //region Helpers

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

    //endregion
}
