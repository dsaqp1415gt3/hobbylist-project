package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.BookResource;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.MediaType;

public class BookCollection {
	@InjectLinks({
		@InjectLink(resource = BookResource.class, style = Style.ABSOLUTE, rel = "create-book", title = "Create book", type = MediaType.HOBBYLIST_API_BOOK),
		@InjectLink(value = "/books?before={before}", style = Style.ABSOLUTE, rel = "previous", title = "Previous books", type = MediaType.HOBBYLIST_API_BOOK_COLLECTION, bindings = { @Binding(name = "before", value = "${instance.oldestTimestamp}") }),
		@InjectLink(value = "/books?after={after}", style = Style.ABSOLUTE, rel = "current", title = "Newest books", type = MediaType.HOBBYLIST_API_BOOK_COLLECTION, bindings = { @Binding(name = "after", value = "${instance.newestTimestamp}") }) })
	private List<Link> links;
	private List<Book> books;
	private long newestTimestamp;
	private long oldestTimestamp;
	 
	public BookCollection() {
		super();
		books = new ArrayList<>();
	}
 
	public List<Book> getBooks() {
		return books;
	}
 
	public void setBooks(List<Book> books) {
		this.books = books;
	}
 
	public void addBook(Book book) {
		books.add(book);
	}
	
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}
 
	public long getNewestTimestamp() {
		return newestTimestamp;
	}
 
	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}
 
	public long getOldestTimestamp() {
		return oldestTimestamp;
	}
 
	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}
}
