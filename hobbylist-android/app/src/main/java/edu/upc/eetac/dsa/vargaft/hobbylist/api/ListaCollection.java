package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vargaft on 29/05/2015.
 */
public class ListaCollection {

    private List<Lista> lists;
    private Map<String, Link> links = new HashMap<String, Link>();

    public ListaCollection() {
        super();
        lists = new ArrayList<Lista>();
    }

    public List<Lista> getLists() {
        return lists;
    }

    public void setLists(List<Lista> lists) {
        this.lists = lists;
    }

    public void addLista(Lista lista) {
        lists.add(lista);
    }

    public Map<String, Link> getLinks() {
        return links;
    }
}
