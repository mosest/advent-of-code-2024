package day08;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static util.FileHelper.PATH_TO_INPUT_FILES;

public class Day8 {

    private String inputFileName = "day8.txt";
    private int inputNumLines = 50;
    private final HashMap<Character, List<Pair<Integer, Integer>>> input;

    public Day8(boolean practice) {
        if (practice) {
            inputFileName = inputFileName.replaceAll("\\.", "-practice.");
            inputNumLines = 12;
        }

        input = readIntoMap();
    }

    public long part1() {

        // go through each piece of input and determine where its antinodes are
        HashMap<Pair<Integer, Integer>, Integer> antinodes = new HashMap<>();
        for (Map.Entry<Character, List<Pair<Integer, Integer>>> entry : input.entrySet()) {
            fillMap(antinodes, entry.getKey(), entry.getValue());
        }

        return antinodes.keySet().stream()
                .filter(p ->
                            p.getKey() >= 0 &&
                            p.getKey() < inputNumLines &&
                            p.getValue() >= 0 &&
                            p.getValue() < inputNumLines)
                .toList()
                .size();
    }

    public long part2() {
        return -1;
    }

    //region Helpers

    private void fillMap(HashMap<Pair<Integer, Integer>, Integer> map, char character, List<Pair<Integer, Integer>> coordinatesList) {

        List<List<Pair<Integer, Integer>>> combinations =
                Lists.cartesianProduct(
                        ImmutableList.of(
                            List.copyOf(coordinatesList),
                            List.copyOf(coordinatesList)));

        for (List<Pair<Integer, Integer>> comboPair : combinations) {

            Pair<Integer, Integer> antenna1 = comboPair.get(0);
            Pair<Integer, Integer> antenna2 = comboPair.get(1);

            int antiNode1R = Math.max(antenna1.getKey(), antenna2.getKey()) + Math.abs(antenna1.getKey() - antenna2.getKey());
            int antiNode1C = Math.max(antenna1.getValue(), antenna2.getValue()) + Math.abs(antenna1.getValue() - antenna2.getValue());
            int antiNode2R = Math.min(antenna1.getKey(), antenna2.getKey()) - Math.abs(antenna1.getKey() - antenna2.getKey());
            int antiNode2C = Math.min(antenna1.getValue(), antenna2.getValue()) - Math.abs(antenna1.getValue() - antenna2.getValue());

            Pair<Integer, Integer> antinode1 = new ImmutablePair<>(antiNode1R, antiNode1C);
            Pair<Integer, Integer> antinode2 = new ImmutablePair<>(antiNode2R, antiNode2C);

            map.put(antinode1, -1); // TODO TARA: make sure duplicate pairs aren't added
            map.put(antinode2, -1);
        }
    }

    private HashMap<Character, List<Pair<Integer, Integer>>> readIntoMap() {

        // Not implemented yet lol
        return null;
    }

    //endregion
}

