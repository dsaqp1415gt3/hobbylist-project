package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameCollection {
    private List<Game> games;
    private long newestTimestamp;
    private long oldestTimestamp;
    private Map<String, Link> links = new HashMap<String, Link>();

    public GameCollection() {
        super();
        games = new ArrayList<Game>();
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
