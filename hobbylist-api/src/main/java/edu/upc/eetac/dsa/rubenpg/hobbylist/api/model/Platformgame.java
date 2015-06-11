package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.PlatformgameResource;



public class Platformgame {
	@InjectLinks({
		@InjectLink(resource = PlatformgameResource.class, style = Style.ABSOLUTE, rel = "platformsgames", title = "Latest Platformsgames", type = MediaType.HOBBYLIST_API_PLATFORMGAME_COLLECTION),
		@InjectLink(resource = PlatformgameResource.class, style = Style.ABSOLUTE, rel = "self edit", title = "Platformgame", type = MediaType.HOBBYLIST_API_GAME, method = "getPlatformgame", bindings = @Binding(name = "gameid", value = "${instance.gameid}")) })
	private List<Link> links;
	private int platformid;
	private int gameid;
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
	
	public int getGameid() {
		return gameid;
	}
	
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getPlatformname() {
		return platformname;
	}

	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}

}
