package day06;

import models.Day;
import models.Direction;
import util.FileHelper;

import java.util.*;

public class Day6 extends Day {

    private final char[][] INPUT;
    private static final int MAX_TURN_COUNT = 100000;

    public Day6(boolean practice) {
        super("day6.txt", 130, 10, practice);
        INPUT = FileHelper.readIntoArray_Char_2D(INPUT_FILE_NAME, INPUT_NUM_LINES);
    }

    public int part1() {

        int guardStartR = -1;
        int guardStartC = -1;
        Direction guardStartDirection = Direction.UNKNOWN;

        for (int r = 0; r < INPUT.length && guardStartR == -1; r++) {
            for (int c = 0; c < INPUT[r].length && guardStartC == -1; c++) {
                char currentChar = INPUT[r][c];
                if (currentChar == '^' ||
                        currentChar == '>' ||
                        currentChar == '<' ||
                        currentChar == 'v') {
                    guardStartR = r;
                    guardStartC = c;
                    guardStartDirection = Direction.convertChar(currentChar);
                }
            }
        }

        Direction[][] visited = visitAndPopulateVisited(INPUT, guardStartR, guardStartC, guardStartDirection);
        return countVisited(visited);
    }

    public int part2() {

        int countTraps = 0;
        int guardStartR = -1;
        int guardStartC = -1;
        Direction guardStartDirection = Direction.UNKNOWN;

        // Find the starting point
        for (int r = 0; r < INPUT.length && guardStartR == -1; r++) {
            for (int c = 0; c < INPUT[r].length && guardStartC == -1; c++) {
                char currentChar = INPUT[r][c];
                if (currentChar == '^' ||
                        currentChar == '>' ||
                        currentChar == '<' ||
                        currentChar == 'v') {
                    guardStartR = r;
                    guardStartC = c;
                    guardStartDirection = Direction.convertChar(currentChar);
                }
            }
        }

        // Go through each spot in array, and put a blocker there. Then, track the guard and see if she's infinitely looping. If so, count++
        for (int r = 0; r < INPUT.length; r++) {
            for (int c = 0; c < INPUT[r].length; c++) {

                if (INPUT[r][c] == '#') continue;
                if (INPUT[r][c] == '^') continue;
                if (INPUT[r][c] == '>') continue;
                if (INPUT[r][c] == '<') continue;
                if (INPUT[r][c] == 'v') continue;

                char oldSpot = INPUT[r][c];
                INPUT[r][c] = '#'; // TODO TARA remember to undo this change before you loop again

                boolean looping = visitAndDetermineIfLooping(INPUT, guardStartR, guardStartC, guardStartDirection);
                if (looping) countTraps++;

                INPUT[r][c] = oldSpot;
            }
        }

        return countTraps;
    }

    //region Helpers

    private static int countVisited(Direction[][] arr) {

        int count = 0;
        for (Direction[] row : arr) {
            for (Direction d : row) {
                if (d != Direction.NULL) count++;
            }
        }
        return count;
    }

    private static Direction[][] visitAndPopulateVisited(char[][] input, int startR, int startC, Direction startDirection) {

        Direction[][] visited = new Direction[input.length][input[0].length];
        for (Direction[] arr : visited) {
            Arrays.fill(arr, Direction.NULL);
        }

        visited[startR][startC] = startDirection;

        int currentR = startR;
        int currentC = startC;
        Direction currentD = startDirection;

        int blockerC;
        int blockerR;

        // Walk like a guard: turn right at every blocker (#) (waaalk like an egyptian....)
        while(isOutOfBounds(input, currentR, currentC) == false) {

            //System.out.println("Guard at [" + currentR + "][" + currentC + "] facing " + currentD);

            // find first blocker in their direction
            if (currentD == Direction.LEFT) {

                blockerC = getColumnOfLeftBlocker(input, currentR, currentC);
                for (int i = currentC; i > blockerC && i >= 0; i--)
                    visited[currentR][i] = currentD;

                currentC = blockerC + 1;
                currentD = Direction.UP;
            }
            else if (currentD == Direction.RIGHT) {

                blockerC = getColumnOfRightBlocker(input, currentR, currentC);
                for (int i = currentC; i < blockerC && i < input[currentR].length; i++)
                    visited[currentR][i] = currentD;

                currentC = blockerC - 1;
                currentD = Direction.DOWN;
            }
            else if (currentD == Direction.UP) {

                blockerR = getRowOfTopBlocker(input, currentR, currentC);
                for (int i = currentR; i > blockerR && i >= 0; i--)
                    visited[i][currentC] = currentD;

                currentR = blockerR + 1;
                currentD = Direction.RIGHT;
            }
            else if (currentD == Direction.DOWN) {

                blockerR = getRowOfBottomBlocker(input, currentR, currentC);
                for (int i = currentR; i < blockerR && i < input.length; i++)
                    visited[i][currentC] = currentD;

                currentR = blockerR - 1;
                currentD = Direction.LEFT;
            }
        }

        return visited;
    }

    private static boolean isOutOfBounds(char[][] input, int r, int c) {
        if (r < 0) return true;
        if (c < 0) return true;
        if (r >= input.length) return true;
        if (c >= input[r].length) return true;
        return false;
    }

    private static int getColumnOfRightBlocker(char[][] input, int r, int c) {

        for (int i = c; i < input[r].length; i++) {
            if (input[r][i] == '#')
                return i;
        }

        return 999;
    }

    private static int getColumnOfLeftBlocker(char[][] input, int r, int c) {

        for (int i = c; i >= 0; i--) {
            if (input[r][i] == '#')
                return i;
        }

        return -999;
    }

    private static int getRowOfBottomBlocker(char[][] input, int r, int c) {

        for (int i = r; i < input.length; i++) {
            if (input[i][c] == '#')
                return i;
        }

        return 999;
    }

    private static int getRowOfTopBlocker(char[][] input, int r, int c) {

        for (int i = r; i >= 0; i--) {
            if (input[i][c] == '#')
                return i;
        }

        return -999;
    }

    private static boolean visitAndDetermineIfLooping(char[][] input, int startR, int startC, Direction startDirection) {

        Direction[][] visited = new Direction[input.length][input[0].length];
        for (Direction[] arr : visited) {
            Arrays.fill(arr, Direction.NULL);
        }

        visited[startR][startC] = startDirection;

        int currentR = startR;
        int currentC = startC;
        Direction currentD = startDirection;

        int blockerC;
        int blockerR;

        int turnCount = 0;

        // Walk like a guard: turn right at every blocker (#) (walk like an egyptian....)
        while(isOutOfBounds(input, currentR, currentC) == false && turnCount < MAX_TURN_COUNT) {

            //System.out.println("Guard at [" + currentR + "][" + currentC + "] facing " + currentD);

            // find first blocker in their direction
            if (currentD == Direction.LEFT) {

                blockerC = getColumnOfLeftBlocker(input, currentR, currentC);
                for (int i = currentC; i > blockerC && i >= 0; i--)
                    visited[currentR][i] = currentD;

                currentC = blockerC + 1;
                currentD = Direction.UP;
            }
            else if (currentD == Direction.RIGHT) {

                blockerC = getColumnOfRightBlocker(input, currentR, currentC);
                for (int i = currentC; i < blockerC && i < input[currentR].length; i++)
                    visited[currentR][i] = currentD;

                currentC = blockerC - 1;
                currentD = Direction.DOWN;
            }
            else if (currentD == Direction.UP) {

                blockerR = getRowOfTopBlocker(input, currentR, currentC);
                for (int i = currentR; i > blockerR && i >= 0; i--)
                    visited[i][currentC] = currentD;

                currentR = blockerR + 1;
                currentD = Direction.RIGHT;
            }
            else if (currentD == Direction.DOWN) {

                blockerR = getRowOfBottomBlocker(input, currentR, currentC);
                for (int i = currentR; i < blockerR && i < input.length; i++)
                    visited[i][currentC] = currentD;

                currentR = blockerR - 1;
                currentD = Direction.LEFT;
            }

            turnCount++;
        }

        return turnCount == MAX_TURN_COUNT; // we loopin (we slidin)
    }
}

