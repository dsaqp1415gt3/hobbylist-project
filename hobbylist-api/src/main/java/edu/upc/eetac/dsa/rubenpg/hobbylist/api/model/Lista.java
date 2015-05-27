package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

public class Lista {
	private List<Link> links;
	private int listid;
	private int hobbyid;
	private String username;
	private String tag;
	private String rank;
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
}
