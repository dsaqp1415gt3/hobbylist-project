package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;
 
public class Game {
	private List<Link> links;
	private int gameid;
	private String username;
	private String title;	
	private String synopsis;
	private int genreid;
	private String genrename;
	private String company;
	private String year;	
	private String imageurl;
	private long creationTimestamp;
 
	public int getGameid() {
		return gameid;
	}
 
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}
	
	public String getGenrename() {
		return genrename;
	}
 
	public void setGenrename(String genrename) {
		this.genrename = genrename;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
 
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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

	public long getCreationTimestamp() {
		return creationTimestamp;
	}
 
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
 
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
}