package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import java.util.HashMap;
import java.util.Map;

public class HobbylistRootAPI {

    private Map<String, Link> links;

    public HobbylistRootAPI() {
        links = new HashMap<String, Link>();
    }

    public Map<String, Link> getLinks() {
        return links;
    }

}
