package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;



public class Platform {
	
	private List<Link> links;
	private int platformid;
	private String platformname;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public int getPlatformid() {
		return platformid;
	}
	public void setPlatformid(int platformid) {
		this.platformid = platformid;
	}
	public String getPlatformname() {
		return platformname;
	}
	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}

}
