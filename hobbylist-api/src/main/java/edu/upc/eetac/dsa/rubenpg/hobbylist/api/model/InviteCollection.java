package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public class InviteCollection {
	
	private List<Link> links;
	private List<Invite> invites;

	public InviteCollection() {
		super();
		invites = new ArrayList<>();
	}
 
	public List<Invite> getInvites() {
		return invites;
	}
 
	public void setInvites(List<Invite> invites) {
		this.invites = invites;
	}
 
	public void addInvite(Invite invite) {
		invites.add(invite);
	}
	
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
