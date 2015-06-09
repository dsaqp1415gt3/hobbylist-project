package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;



import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.FavsResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;

public class FavCollection {
	@InjectLinks({
		@InjectLink(resource = FavsResource.class, style = Style.ABSOLUTE, rel = "create-fav", title = "Create fav", type = MediaType.HOBBYLIST_API_FAVS)})
	private List<Link> links;
	private List<Fav> favs;
	 
	public FavCollection() {
		super();
		favs = new ArrayList<>();
	}
	

	public List<Fav> getFavs() {
		return favs;
	}

	public void setFavs(List<Fav> favs) {
		this.favs = favs;
	}
	
	public void addFav(Fav fav) {
		favs.add(fav);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
}
