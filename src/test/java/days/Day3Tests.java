package days;

import day03.Day3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day3_Part1_Practice() {
        int actual = new Day3(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(161, actual);
    }

    @Test
    public void Day3_Part1_Puzzle() {
        int actual = new Day3(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(184511516, actual);
    }

    @Test
    public void Day3_Part2_Practice() {
        int actual = new Day3(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(48, actual);
    }

    @Test
    public void Day3_Part2_Puzzle() {
        int actual = new Day3(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(90044227, actual);
    }
}