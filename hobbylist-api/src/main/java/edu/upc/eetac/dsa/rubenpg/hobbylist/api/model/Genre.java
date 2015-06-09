package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

public class Genre {

	private List<Link> links;
	private int genreid;
	private String genrename;
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
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

}
