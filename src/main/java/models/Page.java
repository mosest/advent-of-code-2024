package models;

public class Page {
    public int pageNumber;
    public Page(int pageNumber) { this.pageNumber = pageNumber; }

    public String toString() {
        return "Page(" + pageNumber + ")";
    }
}
