package days;

import day01.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day1_Part1_Practice() {
        int actual = new Day1(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(11, actual);
    }

    @Test
    public void Day1_Part1_Puzzle() {
        int actual = new Day1(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(1320851, actual);
    }

    @Test
    public void Day1_Part2_Practice() {
        int actual = new Day1(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(31, actual);
    }

    @Test
    public void Day1_Part2_Puzzle() {
        int actual = new Day1(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(26859182, actual);
    }
}