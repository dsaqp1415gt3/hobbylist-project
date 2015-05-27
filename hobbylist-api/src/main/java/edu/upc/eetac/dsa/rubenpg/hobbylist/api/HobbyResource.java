package edu.upc.eetac.dsa.rubenpg.hobbylist.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.Hobby;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.HobbyCollection;
 
@Path("/hobbies")
public class HobbyResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
private String GET_HOBBIES_QUERY = "select * from hobbies";
	
	@GET
	@Produces(MediaType.HOBBYLIST_API_HOBBY_COLLECTION)
	public HobbyCollection getHobbies() {
		HobbyCollection hobbies = new HobbyCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {	
			stmt = conn.prepareStatement(GET_HOBBIES_QUERY);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Hobby hobby = new Hobby();
				hobby.setHobbyid(rs.getInt("hobbyid"));
				hobby.setClassification(rs.getString("classification"));
				hobby.setTitle(rs.getString("title"));
				hobby.setSynopsis(rs.getString("synopsis"));
				hobby.setGenre(rs.getString("genre"));
				hobby.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());			
				hobbies.addHobby(hobby);
			}		
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobbies;
	}
	
	private String GET_HOBBY_BY_ID_QUERY = "select * from hobbies where hobbyid=?";
	 
	@GET
	@Path("/{hobbyid}")
	@Produces(MediaType.HOBBYLIST_API_HOBBY)
	public Hobby getHobby(@PathParam("hobbyid") String hobbyid,	@Context Request request) {
		Hobby hobby = new Hobby();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {				
			stmt = conn.prepareStatement(GET_HOBBY_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(hobbyid));
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				hobby.setHobbyid(rs.getInt("hobbyid"));
				hobby.setClassification(rs.getString("classification"));
				hobby.setTitle(rs.getString("title"));
				hobby.setSynopsis(rs.getString("synopsis"));
				hobby.setGenre(rs.getString("genre"));
				hobby.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			}		
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobby;
	}
	
	private String GET_HOBBY_BY_TITLE_QUERY = "select * from hobbies where title like ?";
	 
	@GET
	@Path("/{title}")
	@Produces(MediaType.HOBBYLIST_API_HOBBY_COLLECTION)
	public Hobby getHobbybyTitle(@PathParam("title") String title,	@Context Request request) {
		Hobby hobby = new Hobby();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {				
			stmt = conn.prepareStatement(GET_HOBBY_BY_TITLE_QUERY);
			stmt.setString(1, "%" + title + "%");
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				hobby.setHobbyid(rs.getInt("hobbyid"));
				hobby.setClassification(rs.getString("classification"));
				hobby.setTitle(rs.getString("title"));
				hobby.setSynopsis(rs.getString("synopsis"));
				hobby.setGenre(rs.getString("genre"));
				hobby.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			}		
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobby;
	}
	
	private String GET_HOBBY_BY_GENRE_QUERY = "select * from hobbies where classification like ? AND genre like ?";
	 
	@GET
	@Path("/{classification}/{genre}")
	@Produces(MediaType.HOBBYLIST_API_HOBBY_COLLECTION)
	public HobbyCollection getHobbiesbyGenre(@PathParam("classification") String classification, @PathParam("genre") String genre) {
		HobbyCollection hobbies = new HobbyCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {			
			stmt = conn.prepareStatement(GET_HOBBY_BY_GENRE_QUERY);
			stmt.setString(1, "%" + classification + "%");
			stmt.setString(2, "%" + genre + "%");
						
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Hobby hobby = new Hobby();
				hobby.setHobbyid(rs.getInt("hobbyid"));
				hobby.setClassification(rs.getString("classification"));
				hobby.setTitle(rs.getString("title"));
				hobby.setSynopsis(rs.getString("synopsis"));
				hobby.setGenre(rs.getString("genre"));
				hobby.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				hobbies.addHobby(hobby);
			}		
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobbies;
	}
	
	private String INSERT_HOBBY_QUERY = "insert into hobbies (classification, title, synopsis, genre) values (?, ?, ?, ?)";
	 
	@POST
	@Consumes(MediaType.HOBBYLIST_API_HOBBY)
	@Produces(MediaType.HOBBYLIST_API_HOBBY)
	public Hobby createHobby(Hobby hobby) {
		validateHobby(hobby);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_HOBBY_QUERY,
					Statement.RETURN_GENERATED_KEYS);
	 
			stmt.setString(1, hobby.getClassification());
			stmt.setString(2, hobby.getTitle());
			stmt.setString(3, hobby.getSynopsis());
			stmt.setString(4, hobby.getGenre());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int hobbyid = rs.getInt(1);
	 
				hobby = getHobbyFromDatabase(Integer.toString(hobbyid));
			} else {
				// Something has failed...
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobby;
	}
	
	private String DELETE_HOBBY_QUERY = "delete from hobbies where hobbyid=?";
	 
	@DELETE
	@Path("/{hobbyid}")
	public void deleteHobby(@PathParam("hobbyid") String hobbyid) {
		validateUser(hobbyid);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_HOBBY_QUERY);
			stmt.setInt(1, Integer.valueOf(hobbyid));
	 
			int rows = stmt.executeUpdate();
			if (rows == 0)
				throw new NotFoundException("There's no hobby with hobbyid="
						+ hobbyid);
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	private String UPDATE_HOBBY_QUERY = "update hobbies set title=ifnull(?, title), synopis=ifnull(?, synopsis), genre=ifnull(?, genre) where hobbyid=?";
	 
	@PUT
	@Path("/{hobbyid}")
	@Consumes(MediaType.HOBBYLIST_API_HOBBY)
	@Produces(MediaType.HOBBYLIST_API_HOBBY)
	public Hobby updateHobby(@PathParam("hobbyid") String hobbyid, Hobby hobby) {
		validateUser(hobbyid);
		validateUpdateHobby(hobby);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_HOBBY_QUERY);
			stmt.setString(1, hobby.getTitle());
			stmt.setString(2, hobby.getSynopsis());
			stmt.setString(3, hobby.getGenre());
			stmt.setInt(4, Integer.valueOf(hobbyid));
	 
			int rows = stmt.executeUpdate();
			if (rows == 1)
				hobby = getHobbyFromDatabase(hobbyid);
			else {
				throw new NotFoundException("There's no hobby with hobbyid=" + hobbyid);
			}
	 
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobby;
	}
	
	private void validateHobby(Hobby hobby) {
		if (hobby.getClassification() == null)
			throw new BadRequestException("Classification can't be null.");
		if (hobby.getTitle() == null)
			throw new BadRequestException("Title can't be null.");
		if (hobby.getSynopsis() == null)
			throw new BadRequestException("Synopsis can't be null.");
		if (hobby.getGenre() == null)
			throw new BadRequestException("Genre can't be null.");
		if (hobby.getClassification().length() > 20)
			throw new BadRequestException("Classification can't be greater than 20 characters.");
		if (hobby.getTitle().length() > 100)
			throw new BadRequestException("Title can't be greater than 100 characters.");
		if (hobby.getSynopsis().length() > 500)
			throw new BadRequestException("Synopsis can't be greater than 500 characters.");
		if (hobby.getGenre().length() > 20)
			throw new BadRequestException("Genre can't be greater than 20 characters.");
	}
	
	private void validateUpdateHobby(Hobby hobby) {
		if (hobby.getTitle() != null && hobby.getTitle().length() > 100)
			throw new BadRequestException(
					"Title can't be greater than 100 characters.");
		if (hobby.getSynopsis() != null && hobby.getSynopsis().length() > 500)
			throw new BadRequestException(
					"Synopsis can't be greater than 500 characters.");
		if (hobby.getGenre() != null && hobby.getGenre().length() > 20)
			throw new BadRequestException(
					"Genre can't be greater than 20 characters.");
	}
	
	private Hobby getHobbyFromDatabase(String hobbyid) {
		Hobby hobby = new Hobby();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_HOBBY_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(hobbyid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				hobby.setHobbyid(rs.getInt("hobbyid"));
				hobby.setClassification(rs.getString("classification"));
				hobby.setTitle(rs.getString("title"));
				hobby.setSynopsis(rs.getString("synopsis"));
				hobby.setGenre(rs.getString("genre"));
				hobby.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			} else {
				throw new NotFoundException("There's no hobby with hobbyid="+ hobbyid);
				}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return hobby;
	}
	
	private void validateUser(String hobbyid) {
	    Hobby hobby = getHobbyFromDatabase(hobbyid);
	    String username = hobby.getUsername();
		if (!security.getUserPrincipal().getName().equals(username))
			throw new ForbiddenException(
					"You are not allowed to modify this hobby.");
	}
	
	@Context
	private SecurityContext security;

}
