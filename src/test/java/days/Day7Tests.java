package days;

import day07.Day7;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day7_Part1_Practice() {
        long actual = new Day7(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(3749, actual);
    }

    @Test
    public void Day7_Part1_Puzzle() {
        long actual = new Day7(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(Long.parseLong("1153997401072"), actual);
    }

    @Test
    public void Day7_Part2_Practice() {
        long actual = new Day7(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(11387, actual);
    }

    @Test
    public void Day7_Part2_Puzzle() {
        long actual = new Day7(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(Long.parseLong("97902809384118"), actual);
    }
}