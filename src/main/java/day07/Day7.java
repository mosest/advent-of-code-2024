package day07;

import models.Day;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static util.FileHelper.PATH_TO_INPUT_FILES;

public class Day7 extends Day {

    private final List<Pair<Long, List<Long>>> INPUT;

    public Day7(boolean practice) {
        super("day7.txt", practice);
        INPUT = readIntoMap();
    }

    public long part1() {
        return INPUT.stream()
                .filter(pair -> canBeAnEquation(pair, pair.getValue(), false))
                .map(Pair::getKey)
                .mapToLong(Long::longValue)
                .sum();
    }

    public long part2() {
        return INPUT.stream()
                .filter(pair -> canBeAnEquation(pair, pair.getValue(), true))
                .map(Pair::getKey)
                .mapToLong(Long::longValue)
                .sum();
    }

    //region Helpers

    private boolean canBeAnEquation(Pair<Long, List<Long>> pair, List<Long> numbersLeftOver, boolean weCanTryConcatenation) {

        // base case: quit
        if (numbersLeftOver.size() < 2) return false;

        long a = numbersLeftOver.get(0);
        long b = numbersLeftOver.get(1);

        // base case: success!
        if (numbersLeftOver.size() == 2) {

            if (pair.getKey() == a * b) {
                return true;
            } else if (pair.getKey() == a + b) {
                return true;
            } else if (weCanTryConcatenation && pair.getKey() == concat(a, b)) {
                return true;
            }

        // recursive case
        } else {

            List<Long> newNumbersLeftOverPostMultiplication =
                    new ArrayList<>(numbersLeftOver.subList(2, numbersLeftOver.size()));
            newNumbersLeftOverPostMultiplication.add(0, a*b);

            List<Long> newNumbersLeftOverPostAddition =
                    new ArrayList<>(numbersLeftOver.subList(2, numbersLeftOver.size()));
            newNumbersLeftOverPostAddition.add(0, a+b);

            if (weCanTryConcatenation == false)
                // Part 1
                return canBeAnEquation(pair, newNumbersLeftOverPostAddition, weCanTryConcatenation) ||
                       canBeAnEquation(pair, newNumbersLeftOverPostMultiplication, weCanTryConcatenation);
            else {
                // Part 2
                List<Long> newNumbersLeftOverPostConcat =
                        new ArrayList<>(numbersLeftOver.subList(2, numbersLeftOver.size()));
                newNumbersLeftOverPostConcat.add(0, concat(a, b));

                return canBeAnEquation(pair, newNumbersLeftOverPostAddition, weCanTryConcatenation) ||
                       canBeAnEquation(pair, newNumbersLeftOverPostMultiplication, weCanTryConcatenation) ||
                       canBeAnEquation(pair, newNumbersLeftOverPostConcat, weCanTryConcatenation);
            }
        }

        return false;
    }

    private long concat(long a, long b) {
        return Long.parseLong(a + "" + b);
    }

    private List<Pair<Long, List<Long>>> readIntoMap() {
        List<Pair<Long, List<Long>>> map = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + INPUT_FILE_NAME));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] linePieces = line.split(": ");

                long result = Long.parseLong(linePieces[0]);

                List<Long> operands = Arrays.stream(linePieces[1].split(" "))
                        .map(Long::parseLong)
                        .toList();

                map.add(new MutablePair<>(result, operands));
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + INPUT_FILE_NAME);
        }

        return map;
    }

    //endregion
}

