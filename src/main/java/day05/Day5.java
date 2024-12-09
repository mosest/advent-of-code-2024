package day05;

import models.Day;
import models.Page;
import models.PageComparator;
import models.PageRule;

import org.apache.commons.lang3.ArrayUtils;

import util.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5 extends Day {

    private final List<PageRule> INPUT_PAGE_RULES = new ArrayList<>();
    private final List<int[]> INPUT_UPDATES_LIST = new ArrayList<>();

    public Day5(boolean practice) {
        super("day5.txt", practice);
        readInputAndPopulateLists(INPUT_PAGE_RULES, INPUT_UPDATES_LIST);
    }

    public int part1() {

        // Filter down to just the page subsections ("updates") that were in order. The good noodles :)
        List<int[]> correctUpdates =
                INPUT_UPDATES_LIST.stream()
                    .filter(update ->
                            updateFollowsAllRules(update))
                    .toList();

        // Sum up the middle page numbers of each correct update
        return correctUpdates.stream()
                .map(arr -> arr[arr.length / 2])    // get middle page number
                .mapToInt(Integer::intValue)        // turn it into an IntStream so I can use the sweet sweet helper methods IntStream gives :)
                .sum();                             // it's go time boys
    }

    public int part2() {

        // Filter down to just the ones that are a little broken inside
        List<int[]> disorderedUpdates =
                INPUT_UPDATES_LIST.stream()
                        .filter(update ->
                                updateFollowsAllRules(update) == false)
                        .toList();

        return disorderedUpdates.stream()
                .map(this::sortByPageRules)    // put array in the correct order
                .map(inOrderArray -> inOrderArray[inOrderArray.length / 2])             // get middle page number
                .mapToInt(Integer::intValue)
                .sum();
    }

    //region Helpers

    private void readInputAndPopulateLists(List<PageRule> pageRules, List<int[]> updatesList) {

        try {
            Scanner scanner = new Scanner(new File(FileHelper.PATH_TO_INPUT_FILES + INPUT_FILE_NAME));

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
            System.out.println("[ERROR] FileNotFoundException while looking for file: " + INPUT_FILE_NAME);
        }
    }

    private boolean updateFollowsAllRules(int[] update) {

        for (PageRule rule : INPUT_PAGE_RULES) {

            int indexOfBeforePage = ArrayUtils.indexOf(update, rule.beforePage);
            int indexOfAfterPage = ArrayUtils.indexOf(update, rule.afterPage);

            // if both pages don't exist in update, continue
            if (indexOfAfterPage < 0 || indexOfBeforePage < 0) continue;

            // rule broken
            if (indexOfAfterPage <= indexOfBeforePage) return false;
        }

        return true;
    }

    private int[] sortByPageRules(int[] pageNumbersOutOfOrder) {

        List<PageRule> pertinentRules =
                INPUT_PAGE_RULES.stream()
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
