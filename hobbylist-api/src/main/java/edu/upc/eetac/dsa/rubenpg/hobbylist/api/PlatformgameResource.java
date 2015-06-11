package edu.upc.eetac.dsa.rubenpg.hobbylist.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.Platformgame;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.PlatformgameCollection;

@Path("/plat")
public class PlatformgameResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_PLATFORMS_QUERY = "select platforms.platformname,platformsgames.platformid,games.gameid from platforms inner join platformsgames on platformsgames.platformid=platforms.platformid inner join games on games.gameid=platformsgames.gameid;";
	@GET
	@Produces(MediaType.HOBBYLIST_API_PLATFORMGAME_COLLECTION)
	public PlatformgameCollection getPlatformsgames() {
		PlatformgameCollection platformsgames = new PlatformgameCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {	
			stmt = conn.prepareStatement(GET_PLATFORMS_QUERY);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Platformgame platformgame = new Platformgame();
				platformgame.setPlatformid(rs.getInt("platformid"));
				platformgame.setGameid(rs.getInt("gameid"));
				platformgame.setPlatformname(rs.getString("platformname"));
				platformsgames.addPlatformgame(platformgame);
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
	 
		return platformsgames;
	}

	private String GET_GAME_BY_ID_QUERY = "select platforms.platformname from platforms inner join platformsgames on platformsgames.platformid=platforms.platformid where gameid=?";
	 
	@GET
	@Path("/{gameid}")
	@Produces(MediaType.HOBBYLIST_API_PLATFORMGAME)
	public Platformgame getPlatformgame(@PathParam("gameid") String gameid) {
		Platformgame platformgame = new Platformgame();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {				
			stmt = conn.prepareStatement(GET_GAME_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(gameid));
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				platformgame.setPlatformid(rs.getInt("platformid"));
				platformgame.setGameid(rs.getInt("gameid"));
				platformgame.setPlatformname(rs.getString("platformname"));
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
	 
		return platformgame;
	}
	
	private String INSERT_PLATFORMGAME_QUERY = "insert into platformsgames (platformid, gameid) values (?, ?)";
	@POST
	@Consumes(MediaType.HOBBYLIST_API_PLATFORMGAME)
	@Produces(MediaType.HOBBYLIST_API_PLATFORMGAME)
	public Platformgame createPlatformgame(Platformgame platformgame) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_PLATFORMGAME_QUERY,Statement.RETURN_GENERATED_KEYS);	 
			stmt.setInt(1, platformgame.getPlatformid());
			stmt.setInt(2, platformgame.getGameid());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				int gameid = rs.getInt(1);
	 
				platformgame = getPlatformgame(Integer.toString(gameid));
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
	 
		return platformgame;
	}
	
}

