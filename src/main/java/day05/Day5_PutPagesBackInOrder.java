package day05;

import models.Page;
import models.PageComparator;
import models.PageRule;

import org.apache.commons.lang3.ArrayUtils;

import util.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5_PutPagesBackInOrder {

    private static final String FILENAME = "day5.txt";

    public static int part1_CountMiddleNumbersOfCorrectUpdates() {

        List<PageRule> pageRules = new ArrayList<>();
        List<int[]> updatesList = new ArrayList<>();
        readInputAndPopulateLists(pageRules, updatesList);

        // Filter down to just the page subsections ("updates") that were in order. The good noodles :)
        List<int[]> correctUpdates =
                updatesList.stream()
                    .filter(update ->
                            updateFollowsAllRules(update, pageRules))
                    .toList();

        // Sum up the middle page numbers of each correct update
        return correctUpdates.stream()
                .map(arr -> arr[arr.length / 2])    // get middle page number
                .mapToInt(Integer::intValue)        // turn it into an IntStream so I can use the sweet sweet helper methods IntStream gives :)
                .sum();                             // it's go time boys
    }

    public static int part2_FixUpdatesAndCountMiddleNumbers() {

        List<PageRule> pageRules = new ArrayList<>();
        List<int[]> updatesList = new ArrayList<>();
        readInputAndPopulateLists(pageRules, updatesList);

        // Filter down to just the ones that are a little broken inside
        List<int[]> disorderedUpdates =
                updatesList.stream()
                        .filter(update ->
                                updateFollowsAllRules(update, pageRules) == false)
                        .toList();

        return disorderedUpdates.stream()
                .map(outOfOrderArray -> sortByPageRules(outOfOrderArray, pageRules))    // put array in the correct order
                .map(inOrderArray -> inOrderArray[inOrderArray.length / 2])             // get middle page number
                .mapToInt(Integer::intValue)
                .sum();
    }

    //region Helpers

    private static void readInputAndPopulateLists(List<PageRule> pageRules, List<int[]> updatesList) {

        try {
            Scanner scanner = new Scanner(new File(FileHelper.PATH_TO_INPUT_FILES + FILENAME));

            String currentLine = scanner.nextLine();

            // get those page rules
            while (scanner.hasNextLine() && !currentLine.isEmpty()) {
                String[] x = currentLine.split("\\|");
                pageRules.add(new PageRule(
                        Integer.parseInt(x[0]),
                        Integer.parseInt(x[1])));
                currentLine = scanner.nextLine();
            }

            // now get the updates
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                updatesList.add(
                        Arrays
                                .stream(currentLine.split(","))
                                .mapToInt(Integer::parseInt)
                                .toArray());
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + FILENAME);
        }

        // DEBUG: Print
        // for (PageRule rule : pageRules) { System.out.println(rule.beforePage + " | " + rule.afterPage); }
        // for (int[] update : updatesList) { ArrayHelper.printArray_Int(update); System.out.println(); }
    }

    private static boolean updateFollowsAllRules(int[] update, List<PageRule> allRules) {

        for (PageRule rule : allRules) {

            int indexOfBeforePage = ArrayUtils.indexOf(update, rule.beforePage);
            int indexOfAfterPage = ArrayUtils.indexOf(update, rule.afterPage);

            // if both pages don't exist in update, continue
            if (indexOfAfterPage < 0 || indexOfBeforePage < 0) continue;

            // rule broken
            if (indexOfAfterPage <= indexOfBeforePage) return false;
        }

        return true;
    }

    private static int[] sortByPageRules(int[] pageNumbersOutOfOrder, List<PageRule> allRules) {

        List<PageRule> pertinentRules =
                allRules.stream()
                    .filter(rule ->
                            ArrayUtils.contains(pageNumbersOutOfOrder, rule.beforePage) &&
                            ArrayUtils.contains(pageNumbersOutOfOrder, rule.afterPage))
                    .toList();

        List<Page> badPagesBefore;

        List<Page> badPagesNewest =
                Arrays.stream(pageNumbersOutOfOrder)
                    .mapToObj(Page::new)
                    .collect(Collectors.toList()); // can't use .toList() because that would make badPagesNewest immutable

        int sortCount = 0;
        final int maxSortCount = 100;
        do {
            badPagesBefore = List.copyOf(badPagesNewest); // immutable shallow copy, so i can compare :)

            PageComparator pageComparator = new PageComparator(pertinentRules);
            badPagesNewest.sort(pageComparator);
            sortCount++;

            if (sortCount == maxSortCount - 5) {
                int middleNum = badPagesNewest
                        .get(badPagesNewest.size() / 2)
                        .pageNumber;
                System.out.println("I'm in a loop. Welp! Fuck this! Outtie peace. The current middle number for this guy is: " + middleNum);
            }

        } while (badPagesBefore != badPagesNewest && sortCount < maxSortCount);

        return badPagesNewest.stream()
                .map(page -> page.pageNumber)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    //endregion
}
