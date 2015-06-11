package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.PlatformgameResource;

public class PlatformgameCollection {
	@InjectLinks({
		@InjectLink(resource = PlatformgameResource.class, style = Style.ABSOLUTE, rel = "create-platformgame", title = "Create platformgame", type = MediaType.HOBBYLIST_API_PLATFORMGAME) }) 
	private List<Platformgame> platformsgames;

	public PlatformgameCollection() {
		super();
		platformsgames = new ArrayList<>();
	}

	public List<Platformgame> getPlatformsgames() {
		return platformsgames;
	}

	public void setPlatformsgames(List<Platformgame> platformsgames) {
		this.platformsgames = platformsgames;
	}	
	
	public void addPlatformgame(Platformgame platformgame) {
		platformsgames.add(platformgame);
	}
}
