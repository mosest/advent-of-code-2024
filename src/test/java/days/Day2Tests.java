package days;

import day02.Day2_ReportsAndLevels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day2_Part1_Practice() {
        int actual = new Day2_ReportsAndLevels(USING_PRACTICE_INPUT)
                .part1_CountSafeReports();

        assertEquals(2, actual);
    }

    @Test
    public void Day2_Part1_Puzzle() {
        int actual = new Day2_ReportsAndLevels(USING_PUZZLE_INPUT)
                .part1_CountSafeReports();

        assertEquals(356, actual);
    }

    @Test
    public void Day2_Part2_Practice() {
        int actual = new Day2_ReportsAndLevels(USING_PRACTICE_INPUT)
                .part2_FixUnsafeReportsAndRecountSafeReports();

        assertEquals(4, actual);
    }

    @Test
    public void Day2_Part2_Puzzle() {
        int actual = new Day2_ReportsAndLevels(USING_PUZZLE_INPUT)
                .part2_FixUnsafeReportsAndRecountSafeReports();

        assertEquals(413, actual);
    }
}