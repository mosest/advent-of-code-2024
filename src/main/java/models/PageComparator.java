package models;

import java.util.Comparator;
import java.util.List;

public class PageComparator implements Comparator<Page> {

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