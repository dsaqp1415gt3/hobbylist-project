package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookCollection {
    private List<Book> books;
    private long newestTimestamp;
    private long oldestTimestamp;
    private Map<String, Link> links = new HashMap<String, Link>();

    public BookCollection() {
        super();
        books = new ArrayList<Book>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }
}
