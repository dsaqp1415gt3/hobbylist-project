package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public class GenreCollection {
	private List<Link> links;
	private List<Genre> genres;
	 
	public GenreCollection() {
		super();
		genres = new ArrayList<>();
	}
 
	public List<Genre> getGenres() {
		return genres;
	}
 
	public void setGenre(List<Genre> genres) {
		this.genres = genres;
	}
 
	public void addGenre(Genre genre) {
		genres.add(genre);
	}
	
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
