package models;

public class Day {

    public final boolean IS_PRACTICE;
    public final String INPUT_FILE_NAME;
    public final int INPUT_NUM_LINES;

    public Day(String baseFileName, int puzzleInputNumLines, int practiceInputNumLines, boolean practice) {
        IS_PRACTICE = practice;
        if (practice) {
            INPUT_FILE_NAME = baseFileName.replaceAll("\\.", "-practice.");
            INPUT_NUM_LINES = practiceInputNumLines;
        } else {
            INPUT_FILE_NAME = baseFileName;
            INPUT_NUM_LINES = puzzleInputNumLines;
        }
    }

    public Day(String baseFileName, boolean practice) {
        IS_PRACTICE = practice;
        if (practice) {
            INPUT_FILE_NAME = baseFileName.replaceAll("\\.", "-practice.");
            INPUT_NUM_LINES = 0;
        } else {
            INPUT_FILE_NAME = baseFileName;
            INPUT_NUM_LINES = 0;
        }
    }
}
