package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.GameResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;

public class GameCollection {
	@InjectLinks({
		@InjectLink(resource = GameResource.class, style = Style.ABSOLUTE, rel = "create-game", title = "Create game", type = MediaType.HOBBYLIST_API_GAME) }) 
	private List<Link> links;
	private List<Game> games;
	 
	public GameCollection() {
		super();
		games = new ArrayList<>();
	}
 
	public List<Game> getGames() {
		return games;
	}
 
	public void setGames(List<Game> games) {
		this.games = games;
	}
 
	public void addGame(Game game) {
		games.add(game);
	}
	
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Game getGamebyId(int gameid) {
		Game game;
		Iterator iterator = games.iterator();
		while (iterator.hasNext()) {
			game = (Game) iterator.next();
			if (gameid == game.getGameid()) {
				return game;
			}
		}
		return null;
	}
	
}
