package models;

public class PageRule {
    public int beforePage;
    public int afterPage;

    public PageRule(int b, int a) { beforePage = b; afterPage = a; }

    public String toString() {
        return beforePage + " | " + afterPage;
    }
}
