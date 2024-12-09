package days;

import day05.Day5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day5_Part1_Practice() {
        int actual = new Day5(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(143, actual);
    }

    @Test
    public void Day5_Part1_Puzzle() {
        int actual = new Day5(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(5208, actual);
    }

    @Test
    public void Day5_Part2_Practice() {
        int actual = new Day5(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(123, actual);
    }

    @Test
    public void Day5_Part2_Puzzle() {
        int actual = new Day5(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(6732, actual);
    }
}