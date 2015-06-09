package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public class GameCollection {
	
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

}
