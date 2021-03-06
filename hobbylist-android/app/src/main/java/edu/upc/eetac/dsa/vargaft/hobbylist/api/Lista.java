package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vargaft on 29/05/2015.
 */
public class Lista {
    private int listid;
    private int hobbyid;
    private String classification;
    private String title;
    private String genre;
    private String synopsis;
    private String director;
    private String author;
    private String company;
    private String year;
    private String imageurl;
    private String tag;
    private String rank;
    private String username;
    private long creationTimestamp;
    private Map<String, Link> links = new HashMap<String, Link>();
    private String eTag;

    public int getListid() {
        return listid;
    }

    public void setListid(int listid) {
        this.listid = listid;
    }

    public int getHobbyid() {
        return hobbyid;
    }

    public void setHobbyid(int hobbyid) {
        this.hobbyid = hobbyid;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String ETag) {
        this.eTag = ETag;
    }
}
