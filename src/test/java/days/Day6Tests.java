package days;

import day06.Day6;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day6_Part1_Practice() {
        int actual = new Day6(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(41, actual);
    }

    @Test
    public void Day6_Part1_Puzzle() {
        int actual = new Day6(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(5030, actual);
    }

    @Test
    public void Day6_Part2_Practice() {
        int actual = new Day6(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(6, actual);
    }

    @Test
    public void Day6_Part2_Puzzle() {
        int actual = new Day6(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(1928, actual);
    }
}