package day02;

import models.Day;
import util.FileHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    private final int[][] INPUT;

    public Day2(boolean practice) {
        super("day2.txt", 1000, 6, practice);
        INPUT = FileHelper.readIntoArray_Int_2D(INPUT_FILE_NAME, INPUT_NUM_LINES);
    }

    public int part1() {
        return Arrays.stream(INPUT)
                .filter(Day2::isSafeReport)
                .toList()
                .size();
    }

    public int part2() {

        int safeReports = 0;

        for (int[] report : INPUT) {

            if (isSafeReport(report))
                safeReports++;
            else {
                // Try to fix the report by taking one level away. just iterate through the levels, check the new report,
                // and if you get a safe one, stop. otherwise keep returning false

                for (int levelIndex = 0; levelIndex < report.length; levelIndex++) {

                    // build modified report by removing level from the array
                    List<Integer> modifiedReport = Arrays.stream(report)
                            .boxed()
                            .collect(Collectors.toList());

                    modifiedReport.remove(levelIndex);

                    if (isSafeReport(modifiedReport.stream().mapToInt(Integer::intValue).toArray())) {
                        safeReports++;
                        break;
                    }
                }
            }
        }

        return safeReports;
    }

    private static boolean isSafeReport(int[] report) {

        int prevNumber = report[0];
        int currentNumber = report[1];

        // direction = -1 for descending, 1 for ascending, 0 for equal. if 0, automatically unsafe
        int direction = currentNumber - prevNumber;

        if (direction == 0)
            return false;

        for (int i = 1; i < report.length; i++) {

            currentNumber = report[i];
            prevNumber = report[i-1];

            if (Math.abs(currentNumber - prevNumber) > 3) return false;

            if (direction > 0) { // supposed to be increasing
                if (currentNumber - prevNumber <= 0) return false;
            }

            if (direction < 0) { // supposed to be decreasing: i.e. currentNum has to be smaller than prevNum for it to be safe
                if (currentNumber - prevNumber >= 0) return false;
            }
        }

        return true;
    }
}
