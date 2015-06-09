package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.InviteResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;

public class Invite {
	@InjectLinks({
		@InjectLink(resource = InviteResource.class, style = Style.ABSOLUTE, rel = "invites", title = "Latest invites", type = MediaType.HOBBYLIST_API_INVITE_COLLECTION),
		@InjectLink(resource = InviteResource.class, style = Style.ABSOLUTE, rel = "self edit", title = "Invite", type = MediaType.HOBBYLIST_API_INVITE, method = "getInvite", bindings = @Binding(name = "invid", value = "${instance.invid}")) })
	private List<Link> links;
	private int invid;
	private String sender;
	private String receiver;
	private int stateid;
	private int gameid;
	private long creationTimestamp;
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public int getInvid() {
		return invid;
	}
	public void setInvid(int invid) {
		this.invid = invid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getStateid() {
		return stateid;
	}
	public void setStateid(int stateid) {
		this.stateid = stateid;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

}
