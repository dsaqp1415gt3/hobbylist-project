package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.FavsResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;

public class Fav {
	@InjectLinks({
		@InjectLink(resource = FavsResource.class, style = Style.ABSOLUTE, rel = "favs", title = "Latest favs", type = MediaType.HOBBYLIST_API_FAVS_COLLECTION),
		@InjectLink(resource = FavsResource.class, style = Style.ABSOLUTE, rel = "self edit", title = "Fav", type = MediaType.HOBBYLIST_API_FAVS, method = "getFav", bindings = @Binding(name = "favid", value = "${instance.favid}")) })
	private List<Link> links;
	private int favid;
	private int gameid;
	private String username;
	private String title;
	private int genreid;	
	private String synopsis;
	private String company;
	private String year;
	private String imageurl;
	private int rank;	
	private long creationTimestamp;
	private List<Platformgame> platformsgames;
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public int getFavid() {
		return favid;
	}
	public void setFavid(int favid) {
		this.favid = favid;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGenreid() {
		return genreid;
	}
	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public List<Platformgame> getPlatformsgames() {
		return platformsgames;
	}

	public void setPlatformsgames(List<Platformgame> platformsgames) {
		this.platformsgames = platformsgames;
	}
}
