package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.InviteResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;

public class InviteCollection {
	@InjectLinks({
		@InjectLink(resource = InviteResource.class, style = Style.ABSOLUTE, rel = "create-invite", title = "Create invite", type = MediaType.HOBBYLIST_API_INVITE) }) 
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
