package days;

import day04.Day4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day4_Part1_Practice() {
        int actual = new Day4(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(18, actual);
    }

    @Test
    public void Day4_Part1_Puzzle() {
        int actual = new Day4(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(2507, actual);
    }

    @Test
    public void Day4_Part2_Practice() {
        int actual = new Day4(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(9, actual);
    }

    @Test
    public void Day4_Part2_Puzzle() {
        int actual = new Day4(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(1969, actual);
    }
}