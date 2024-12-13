package days;

import day08.Day8;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Tests {

    public static final boolean USING_PRACTICE_INPUT = true;
    public static final boolean USING_PUZZLE_INPUT = false;

    @Test
    public void Day8_Part1_Practice() {
        int actual = new Day8(USING_PRACTICE_INPUT)
                .part1();

        assertEquals(14, actual);
    }

    @Test
    public void Day8_Part1_Puzzle() {
        int actual = new Day8(USING_PUZZLE_INPUT)
                .part1();

        assertEquals(220, actual);
    }

    @Test
    public void Day8_Part2_Practice() {
        int actual = new Day8(USING_PRACTICE_INPUT)
                .part2();

        assertEquals(34, actual);
    }

    @Test
    public void Day8_Part2_Puzzle() {
        int actual = new Day8(USING_PUZZLE_INPUT)
                .part2();

        assertEquals(-1, actual);
    }
}

/*


##....#....#
.#.#....0...
..#.#0....#.
..##...0....
....0....#..
.#...#A....#
...#..#.....
#....#.#....
..#.....A...
....#....A..
.#........#.
...#......##


 */