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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.Fav;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.FavCollection;

@Path("/favs")
public class FavsResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_FAVS_BY_USER_QUERY = "select * from games h, favs l where h.gameid = l.gameid AND l.username like ?";
	
	@GET
	@Path("/users/{username}")
	@Produces(MediaType.HOBBYLIST_API_FAVS_COLLECTION)
	public FavCollection getFavs(@PathParam("username") String username) {
		FavCollection favs = new FavCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {	
			stmt = conn.prepareStatement(GET_FAVS_BY_USER_QUERY);
			stmt.setString(1, "%" + username + "%");
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fav fav = new Fav();
				fav.setGameid(rs.getInt("gameid"));
				fav.setUsername(rs.getString("username"));
				fav.setTitle(rs.getString("title"));
				fav.setSynopsis(rs.getString("synopsis"));
				fav.setGenreid(rs.getInt("genreid"));
				fav.setCompany(rs.getString("company"));
				fav.setYear(rs.getString("year"));
				fav.setImageurl(rs.getString("imageurl"));
				fav.setRank(rs.getInt("rank"));				;
				fav.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				favs.addFav(fav);
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
	 
		return favs;
	}
	
	private String GET_FAVS_BY_ID_QUERY = "select * from games h, favs l where l.favid=? AND h.gameid = l.gameid";
	 
	@GET
	@Path("/{favid}")
	@Produces(MediaType.HOBBYLIST_API_FAVS)
	public Fav getFav(@PathParam("favid") String favid) {
		Fav fav = new Fav();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {				
			stmt = conn.prepareStatement(GET_FAVS_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(favid));
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				fav.setGameid(rs.getInt("gameid"));
				fav.setUsername(rs.getString("username"));
				fav.setTitle(rs.getString("title"));
				fav.setSynopsis(rs.getString("synopsis"));
				fav.setGenreid(rs.getInt("genreid"));
				fav.setCompany(rs.getString("company"));
				fav.setYear(rs.getString("year"));
				fav.setImageurl(rs.getString("imageurl"));
				fav.setRank(rs.getInt("rank"));
				fav.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
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
	 
		return fav;
	}

	private String INSERT_FAV_QUERY = "insert into favs (gameid, username, rank) values (?, ?, ?)";
	 
	@POST
	@Consumes(MediaType.HOBBYLIST_API_FAVS)
	@Produces(MediaType.HOBBYLIST_API_FAVS)
	public Fav createFav(Fav fav) {
		validateFav(fav);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_FAV_QUERY,
					Statement.RETURN_GENERATED_KEYS);	 
			stmt.setInt(1, fav.getGameid());
			stmt.setString(2, security.getUserPrincipal().getName());
			stmt.setInt(3, fav.getRank());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int favid = rs.getInt(1);
	 
				fav = getFavFromDatabase(Integer.toString(favid));
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
	 
		return fav;
	}
	
	private String DELETE_FAV_QUERY = "delete from favs where favid=?";
	 
	@DELETE
	@Path("/{favid}")
	public void deleteFav(@PathParam("favid") String favid) {
		validateUser(favid);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_FAV_QUERY);
			stmt.setInt(1, Integer.valueOf(favid));
	 
			int rows = stmt.executeUpdate();
			if (rows == 0)
				throw new NotFoundException("There's no fav with favid="
						+ favid);
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
	
	private String UPDATE_FAV_QUERY = "update favs set username=ifnull(?, username), rank=ifnull(?, rank) where favid=?";
	 
	@PUT
	@Path("/{favid}")
	@Consumes(MediaType.HOBBYLIST_API_FAVS)
	@Produces(MediaType.HOBBYLIST_API_FAVS)
	public Fav updateFav(@PathParam("favid") String favid, Fav fav) {
		validateUpdateFav(fav);
		validateUser(favid);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_FAV_QUERY);
			stmt.setString(1, fav.getUsername());
			stmt.setInt(2, fav.getRank());
			stmt.setInt(3, Integer.valueOf(favid));
	 
			int rows = stmt.executeUpdate();
			if (rows == 1)
				fav = getFavFromDatabase(favid);
			else {
				throw new NotFoundException("There's no list with favid=" + favid);
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
	 
		return fav;
	}
	
	private void validateFav(Fav fav) {		
		if (fav.getUsername() == null)
			throw new BadRequestException("Username can't be null.");
		if (fav.getUsername().length() > 20)
			throw new BadRequestException("Username can't be greater than 100 characters.");
	}
	
	private void validateUpdateFav(Fav fav) {
		if (fav.getUsername() != null && fav.getUsername().length() > 20)
			throw new BadRequestException(
					"Username can't be greater than 20 characters.");
	}
	
	private Fav getFavFromDatabase(String favid) {
		Fav fav = new Fav();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_FAVS_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(favid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				fav.setFavid(rs.getInt("favid"));
				fav.setGameid(rs.getInt("gameid"));
				fav.setUsername(rs.getString("username"));
				fav.setRank(rs.getInt("rank"));
			} else {
				throw new NotFoundException("There's no fav with favid="+ favid);
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
	 
		return fav;
	}
	
	private void validateUser(String favid) {
	    Fav fav = getFavFromDatabase(favid);
	    String username = fav.getUsername();
		if (!security.getUserPrincipal().getName().equals(username))
			throw new ForbiddenException(
					"You are not allowed to modify this fav.");
	}
	
	@Context
	private SecurityContext security;

}
