package days;

import util.ArrayHelper;
import util.FileHelper;
import java.util.HashMap;

import static java.util.Arrays.sort;

public class Day1_DistanceBetweenLists {

    /*
        Smallest-to-smallest pair,
        Second-smallest-to-second-smallest pair,
        etc.

        Get absolute value of pair1 minus pair2

        Return total distance (added abs-values)
     */
    public static int GetTotalDistanceBetweenLists() {

        int numberOfLinesInInput = 1000;
        int numberOfThingsOnEachLine = 2;

        int[][] input = FileHelper.readIntoArray_Int_2D_Sideways("day1.txt", numberOfLinesInInput, numberOfThingsOnEachLine);
        ArrayHelper.printArray_Int_2D(input);

        sort(input[0]);
        sort(input[1]);

        int sum = 0;
        for (int i = 0; i < numberOfLinesInInput; i++) {
            sum += Math.abs(input[0][i] - input[1][i]);
        }

        return sum;
    }

    /*
        Figure out exactly how often each number from the left list appears in the right list

        Calculate a total similarity score by
        adding up each number in the left list
            after multiplying it by
            the number of times that number appears in the right list
     */
    public static int GetSimilarityScore() {

        int numberOfLinesInInput = 1000;
        int numberOfThingsOnEachLine = 2;

        int[][] input = FileHelper.readIntoArray_Int_2D_Sideways("day1.txt", numberOfLinesInInput, numberOfThingsOnEachLine);
        ArrayHelper.printArray_Int_2D(input);

        HashMap<Integer, Integer> frequencies = populateFrequencies(input[1]);

        int similarityScore = 0;
        for (int leftNumber : input[0]) {

            // Check that the leftNumber exists in the hashmap. If it doesn't, frequency = 0
            similarityScore += leftNumber * frequencies.getOrDefault(leftNumber, 0);
        }
        return similarityScore;
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
