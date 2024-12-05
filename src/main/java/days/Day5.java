package days;

import org.apache.commons.lang3.ArrayUtils;
import util.ArrayHelper;
import util.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5 {

    private static final String FILENAME = "day5.txt";

    //region Day 1

    public static int countMiddleNumbersOfCorrectUpdates() {

        List<PageRule> pageRules = new ArrayList<>();
        List<int[]> updatesList = new ArrayList<>();

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

//        for (PageRule rule : pageRules) { System.out.println(rule.beforePage + " | " + rule.afterPage); }
//        for (int[] update : updatesList) { ArrayHelper.printArray_Int(update); System.out.println(); }

        int count = 0;

        List<int[]> correctUpdates = new ArrayList<>();

        for (int[] update : updatesList) {

            boolean updateIsValid = true;
            ArrayHelper.printArray_Int(update);

            // check if it's a correct update
            for (PageRule rule : pageRules) {

                int indexOfBeforePage = ArrayUtils.indexOf(update, rule.beforePage);
                int indexOfAfterPage = ArrayUtils.indexOf(update, rule.afterPage);

//                System.out.println("before=" + rule.beforePage + " at index " + indexOfBeforePage + ", after=" + rule.afterPage + " at index " + indexOfAfterPage);

                // if both pages don't exist in update, continue
                if (indexOfAfterPage < 0 || indexOfBeforePage < 0) continue;

                if (indexOfAfterPage <= indexOfBeforePage) {
//                    System.out.println("this update broke the rule: " + rule.beforePage + "|" + rule.afterPage);
                    updateIsValid = false;
                }
            }

            if (updateIsValid) {
                System.out.println("Found a valid update!");
                ArrayHelper.printArray_Int(update);
                correctUpdates.add(update);
            }
        }

        for (int[] correctUpdate : correctUpdates) {
            count += correctUpdate[correctUpdate.length / 2];
        }

        return count;
    }

    //endregion

    //region Day 2

    public static int fixIncorrectUpdates() {

        List<PageRule> pageRules = new ArrayList<>();
        List<int[]> updatesList = new ArrayList<>();

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

//        for (PageRule rule : pageRules) { System.out.println(rule.beforePage + " | " + rule.afterPage); }
//        for (int[] update : updatesList) { ArrayHelper.printArray_Int(update); System.out.println(); }

        int count = 0;

        List<int[]> incorrectUpdates = new ArrayList<>();

        for (int[] update : updatesList) {

            boolean updateIsValid = true;
            ArrayHelper.printArray_Int(update);

            // check if it's a correct update
            for (PageRule rule : pageRules) {

                int indexOfBeforePage = ArrayUtils.indexOf(update, rule.beforePage);
                int indexOfAfterPage = ArrayUtils.indexOf(update, rule.afterPage);

//                System.out.println("before=" + rule.beforePage + " at index " + indexOfBeforePage + ", after=" + rule.afterPage + " at index " + indexOfAfterPage);

                // if both pages don't exist in update, continue
                if (indexOfAfterPage < 0 || indexOfBeforePage < 0) continue;

                if (indexOfAfterPage <= indexOfBeforePage) {
//                    System.out.println("this update broke the rule: " + rule.beforePage + "|" + rule.afterPage);
                    updateIsValid = false;
                }
            }

            if (updateIsValid) {
                // ArrayHelper.printArray_Int(update);
            } else {
                System.out.println("Found an invalid update!");
                incorrectUpdates.add(update);
                ArrayHelper.printArray_Int(update);
            }
        }

        for (int[] incorrectUpdate : incorrectUpdates) {
            count += getMiddleOfFixedUpdate(incorrectUpdate, pageRules);
        }

        return count;
    }

    private static int getMiddleOfFixedUpdate(int[] badUpdate, List<PageRule> rules) {

        List<PageRule> pertinentRules = new ArrayList<>();

        for (PageRule rule : rules) {

            int indexOfBeforePage = ArrayUtils.indexOf(badUpdate, rule.beforePage);
            int indexOfAfterPage = ArrayUtils.indexOf(badUpdate, rule.afterPage);

            // only care about rules where both pieces of the rule are in the badUpdate.
            // make a list of pertinent rules
            if (indexOfBeforePage >= 0 && indexOfAfterPage >= 0)
                pertinentRules.add(rule);
        }

        List<Page> badPagesBefore;

        List<Page> badPagesNewest = Arrays.stream(badUpdate)
                .mapToObj(Page::new)
                .collect(Collectors.toList());

        int counter = 0;
        do {
            counter++;

            badPagesBefore = List.copyOf(badPagesNewest); // immutable shallow copy, so i can compare :)

            PageComparator pageComparator = new PageComparator(pertinentRules);
            badPagesNewest.sort(pageComparator);

            if (counter == 90) {
                int middleNum = badPagesNewest
                        .get(badPagesNewest.size() / 2)
                        .pageNumber;
                System.out.println("I'm in a loop. Welp! Fuck this! Outtie peace. The current middle number for this guy is: " + middleNum);
            }

        } while (badPagesBefore != badPagesNewest && counter < 100);

        // Grab the middle and return it
        return badPagesNewest
                .get(badPagesNewest.size() / 2)
                .pageNumber;
    }

//    private static int[] buildOrderedListOfPageRuleNumbers(List<PageRule> pertinentRules) {
//
//        HashMap<Integer, Integer> pagesWithWeight = new HashMap<>();
//
//        int defaultSmallWeight = 30;
//        int defaultBigWeight = 60;
//
//        for (PageRule rule : pertinentRules) {
//
//            if (pagesWithWeight.containsKey(rule.beforePage) == false) {
//                pagesWithWeight.put(rule.beforePage, defaultSmallWeight);
//            } else {
//                pagesWithWeight.put(rule.beforePage, pagesWithWeight.get(rule.afterPage) - 10);
//            }
//
//            if (pagesWithWeight.containsKey(rule.afterPage) == false) {
//                pagesWithWeight.put(rule.afterPage, defaultBigWeight);
//            }
//
//            // if the numbers exist, then put them before-and-after each other
//
//        }
//
//        // create pagesInOrder from hashmap
//        List<Integer> pagesInOrder = new ArrayList<>();
//
//        while(pagesWithWeight.keySet().isEmpty() == false) {
//
//            int smallestWeight = 99999;
//            int smallestPage = -1;
//            for (Map.Entry<Integer, Integer> entry : pagesWithWeight.entrySet()) {
//                if (smallestWeight > entry.getValue()) {
//                    smallestWeight = entry.getValue();
//                    smallestPage = entry.getKey();
//                }
//            }
//
//            // find the smallest, remove it, add to list
//            pagesWithWeight.remove(smallestPage);
//            pagesInOrder.add(smallestPage);
//        }
//
//        return pagesInOrder.stream()
//                .mapToInt(Integer::intValue)
//                .toArray();
//    }

    //endregion
}

class Page {
    int pageNumber;
    Page(int pageNumber) { this.pageNumber = pageNumber; }
}

class PageRule {
    int beforePage;
    int afterPage;

    PageRule(int b, int a) {
        beforePage = b;
        afterPage = a;
    }
}

class PageComparator implements Comparator<Page> {

    List<PageRule> rules;

    public PageComparator(List<PageRule> rules) { this.rules = rules; }

    @Override
    public int compare(Page a, Page b) {

        // Check each rule to see if there's something including both A and B.
        // if not... they're equal, I guess???
        for (PageRule rule : rules) {
            if (rule.beforePage == a.pageNumber && rule.afterPage == b.pageNumber) {
                return -1;
            } else if (rule.beforePage == b.pageNumber && rule.afterPage == a.pageNumber) {
                return 1;
            }
        }

        return 0;
    }
}
