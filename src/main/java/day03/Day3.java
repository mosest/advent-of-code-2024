package day03;

import models.Day;
import util.FileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 extends Day {

    private String input;

    // "mul(x,y)" without quotes, where x and y are at least 1-digit numbers and at most 3-digit numbers
    private static final String REGEX_MUL = "mul\\(\\d{1,3},\\d{1,3}\\)";

    // do()
    private static final String REGEX_DO = "do\\(\\)";

    // don't()
    private static final String REGEX_DONT = "don't\\(\\)";

    public Day3(boolean practice) {
        super("day3.txt", practice);
        input = FileHelper.readIntoString(INPUT_FILE_NAME);
    }

    public int part1() {

        List<String> listOfMatchingStrings = new ArrayList<>();

        int sum = 0;

        // parse
        Pattern pattern = Pattern.compile(REGEX_MUL);
        Matcher matcher = pattern.matcher(input);

        Stream<String> outputStream = matcher.results().map(MatchResult::group);
        outputStream.forEach(listOfMatchingStrings::add);

        for (String str : listOfMatchingStrings) {

            str = str.substring(4, str.length() - 1);
            String[] stringsToMultiply = str.split(",");

            int a = Integer.parseInt(stringsToMultiply[0]);
            int b = Integer.parseInt(stringsToMultiply[1]);

            sum += a * b;
        }

        return sum;
    }

    public int part2() {

        if (IS_PRACTICE)
            input = FileHelper.readIntoString("day3-practice-2.txt"); // Day 3 is special, it has a different example for part 2

        List<MatchResult> listOfMuls = new ArrayList<>();
        List<MatchResult> listOfDos = new ArrayList<>();
        List<MatchResult> listOfDonts = new ArrayList<>();

        int sum = 0;

        // parse into lists of matches! GO!
        Pattern mulPattern = Pattern.compile(REGEX_MUL);
        Pattern doPattern = Pattern.compile(REGEX_DO);
        Pattern dontPattern = Pattern.compile(REGEX_DONT);

        Matcher mulMatcher = mulPattern.matcher(input);
        Matcher doMatcher = doPattern.matcher(input);
        Matcher dontMatcher = dontPattern.matcher(input);

        Stream<MatchResult> mulOutputStream = mulMatcher.results();
        mulOutputStream.forEach(listOfMuls::add);

        Stream<MatchResult> doOutputStream = doMatcher.results();
        doOutputStream.forEach(listOfDos::add);

        Stream<MatchResult> dontOutputStream = dontMatcher.results();
        dontOutputStream.forEach(listOfDonts::add);

        // Great, now we have all our muls, dos, and don'ts.
        // Now how to parse... hmm. What if I go through the dos and don'ts, and calculate a list of dead zones?
        // then I can go through the indices of muls, and check if it's within the dead zone, and add if not?

        // Calculate dead zones by putting it in a HashMap<index of input string, {true for multiply! false for ignore}>
        boolean[] aliveZones = new boolean[input.length()];

        boolean toggle = true;
        int indexOfNextDo = 0;
        int indexOfNextDont = 0;

        MatchResult nextDo = listOfDos.get(indexOfNextDo);
        MatchResult nextDont = listOfDonts.get(indexOfNextDont);

        for (int i = 0; i < input.length(); i++) {

            if (toggle && i == nextDont.start()) {
                toggle = false;

                // get the next don't that is past the index of your currentDo
                while (nextDont.start() < nextDo.start() && indexOfNextDont < listOfDonts.size() - 1) {
                    indexOfNextDont++;
                    nextDont = listOfDonts.get(indexOfNextDont);
                }
            }

            if (!toggle && i == nextDo.start()) {
                toggle = true;

                // get the next do that is past the index of your currentdont
                while (nextDo.start() < nextDont.start() && indexOfNextDo < listOfDos.size() - 1) {
                    indexOfNextDo++;
                    nextDo = listOfDos.get(indexOfNextDo);
                }
            }

            aliveZones[i] = toggle;
        }

        // Okay, assuming we built our bool[] right, we can now just check every mul-index and listen to its value
        for (MatchResult mr : listOfMuls) {

            if (aliveZones[mr.start()]) {
                String str = mr.group().substring(4, mr.group().length() - 1);
                String[] stringsToMultiply = str.split(",");

                int a = Integer.parseInt(stringsToMultiply[0]);
                int b = Integer.parseInt(stringsToMultiply[1]);

                // System.out.println("Adding " + a + " * " + b + " because it's in a safe zone at index " + mr.start() + "...");
                sum += a * b;
            } else {
                // System.out.println("Ignoring " + mr.group());
            }
        }

        return sum;
    }
}
