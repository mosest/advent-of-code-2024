package day08;

import models.CharInGrid;
import models.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static util.FileHelper.PATH_TO_INPUT_FILES;

public class Day8 extends Day {

    private final HashMap<Character, List<CharInGrid>> INPUT;

    public Day8(boolean practice) {
        super("day8.txt", 50, 12, practice);
        INPUT = readIntoMap();
    }

    public int part1() {

        // go through each different character in the input,
        // and determine where its antinodes are

        HashSet<CharInGrid> setOfDistinctAntinodes = new HashSet<>();

        for (Map.Entry<Character, List<CharInGrid>> entry : INPUT.entrySet()) {
            setOfDistinctAntinodes.addAll(
                    getSetOfDistinctAntinodes(entry.getValue()));
        }

        return setOfDistinctAntinodes.stream()
                .filter(charInGrid ->
                            charInGrid.r >= 0 &&
                            charInGrid.r < INPUT_NUM_LINES &&
                            charInGrid.c >= 0 &&
                            charInGrid.c < INPUT_NUM_LINES) // square array alert
                .toList()
                .size();
    }

    public int part2() {
        return -1;
    }

    //region Helpers

    private HashMap<Character, List<CharInGrid>> readIntoMap() {

        HashMap<Character, List<CharInGrid>> map = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File(PATH_TO_INPUT_FILES + INPUT_FILE_NAME));

            int r = -1;
            while (scanner.hasNextLine()) {

                r++;
                char[] line = scanner.nextLine().toCharArray();

                for (int c = 0; c < line.length; c++) {
                    char character = line[c];

                    if (character == '.') continue;

                    if (map.containsKey(character)) {
                        map.get(character).add(new CharInGrid(character, r, c));
                    } else {
                        List<CharInGrid> l = new ArrayList<>();
                        l.add(new CharInGrid(character, r, c));
                        map.put(character, l);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + INPUT_FILE_NAME);
        }

        return map;
    }

    private HashSet<CharInGrid> getSetOfDistinctAntinodes(List<CharInGrid> antennas) {

        HashSet<CharInGrid> set = new HashSet<>();

        List<List<CharInGrid>> combinationsOfTwo = getDistinctPairs(antennas);

        for (List<CharInGrid> pair : combinationsOfTwo) {
            set.add(getAntinode(pair.get(0), pair.get(1)));
            set.add(getAntinode(pair.get(1), pair.get(0)));
        }

        return set;
    }

    private CharInGrid getAntinode(CharInGrid startAntinode, CharInGrid farAwayAntinode) {

        int newR = startAntinode.r + (2 * (farAwayAntinode.r - startAntinode.r));
        int newC = startAntinode.c + (2 * (farAwayAntinode.c - startAntinode.c));
        return new CharInGrid(newR, newC);
    }

    private List<List<CharInGrid>> getDistinctPairs(List<CharInGrid> things) {

        List<List<CharInGrid>> list = new ArrayList<>();

        for (int i = 0; i < things.size(); i++) {
            for (int j = i + 1; j < things.size(); j++) {
                List<CharInGrid> pair = new ArrayList<>();
                pair.add(things.get(i));
                pair.add(things.get(j));
                list.add(pair);
            }
        }

        return list;
    }

    //endregion
}

