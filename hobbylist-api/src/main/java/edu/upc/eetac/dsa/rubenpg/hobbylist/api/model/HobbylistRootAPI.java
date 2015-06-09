package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;
 





import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.HobbylistRootAPIResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.FavsResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;
 
public class HobbylistRootAPI {
	@InjectLinks({
            @InjectLink(resource = HobbylistRootAPIResource.class, style = Style.ABSOLUTE, rel = "self bookmark home", title = "Hobbylist Root API"),
            @InjectLink(resource = FavsResource.class, style = Style.ABSOLUTE, rel = "collection", title = "Latest favs", type = MediaType.HOBBYLIST_API_FAVS_COLLECTION),
            @InjectLink(resource = FavsResource.class, style = Style.ABSOLUTE, rel = "create-fav", title = "Create new fav", type = MediaType.HOBBYLIST_API_FAVS)})
          
    	private List<Link> links;
 
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}
}