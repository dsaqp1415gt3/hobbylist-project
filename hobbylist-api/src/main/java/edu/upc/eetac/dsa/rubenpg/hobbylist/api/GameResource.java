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

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.Game;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.GameCollection;

 
@Path("/games")
public class GameResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_GAMES_QUERY = "select games.gameid, games.username, games.title, games.synopsis, games.company, games.year, games.imageurl, games.creation_timestamp, genre.genrename from games inner join genre on games.genreid=genre.genreid";
	@GET
	@Produces(MediaType.HOBBYLIST_API_GAME_COLLECTION)
	public GameCollection getGames() {
		GameCollection games = new GameCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {	
			stmt = conn.prepareStatement(GET_GAMES_QUERY);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setGameid(rs.getInt("gameid"));
				game.setUsername(rs.getString("username"));
				game.setTitle(rs.getString("title"));
				game.setSynopsis(rs.getString("synopsis"));
				game.setGenrename(rs.getString("genrename"));
				game.setCompany(rs.getString("company"));
				game.setYear(rs.getString("year"));
				game.setImageurl(rs.getString("imageurl"));
				game.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());	
				games.addGame(game);
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
	 
		return games;
	}

	private String GET_GAME_BY_ID_QUERY = "select games.gameid, games.username, games.title, games.synopsis, games.company, games.year, games.imageurl, games.creation_timestamp, genre.genrename from games inner join genre on games.genreid=genre.genreid where games.gameid=?";
	 
	@GET
	@Path("/{gameid}")
	@Produces(MediaType.HOBBYLIST_API_GAME)
	public Game getGame(@PathParam("gameid") String gameid) {
		Game game = new Game();
		
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
				game.setGameid(rs.getInt("gameid"));
				game.setUsername(rs.getString("username"));
				game.setTitle(rs.getString("title"));
				game.setSynopsis(rs.getString("synopsis"));
				game.setGenrename(rs.getString("genrename"));
				game.setCompany(rs.getString("company"));
				game.setYear(rs.getString("year"));
				game.setImageurl(rs.getString("imageurl"));
				game.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
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
	 
		return game;
	}
	
	private String GET_GAME_BY_TITLE_QUERY = "select games.gameid, games.username, games.title, games.synopsis, games.company, games.year, games.imageurl, games.creation_timestamp, genre.genrename from games inner join genre on games.genreid=genre.genreid where title like ?";
	 
	@GET
	@Path("/title/{title}")
	@Produces(MediaType.HOBBYLIST_API_GAME_COLLECTION)
	public GameCollection getGamebyTitle(@PathParam("title") String title) {
		GameCollection games = new GameCollection();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {				
			stmt = conn.prepareStatement(GET_GAME_BY_TITLE_QUERY);
			stmt.setString(1, "%" + title + "%");
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				Game game = new Game();
				game.setGameid(rs.getInt("gameid"));
				game.setUsername(rs.getString("username"));
				game.setTitle(rs.getString("title"));
				game.setSynopsis(rs.getString("synopsis"));
				game.setGenrename(rs.getString("genrename"));
				game.setCompany(rs.getString("company"));
				game.setYear(rs.getString("year"));
				game.setImageurl(rs.getString("imageurl"));
				game.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				games.addGame(game);
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
	 
		return games;
	}
	
	private String GET_GAME_BY_GENRE_QUERY = "select games.gameid, games.username, games.title, games.synopsis, games.company, games.year, games.imageurl, games.creation_timestamp, genre.genrename from games inner join genre on games.genreid=genre.genreid where genrename like ?";
	
	@GET
	@Path("/genre/{genrename}")
	@Produces(MediaType.HOBBYLIST_API_GAME_COLLECTION)
	public GameCollection getGamesbyGenre(@PathParam("genrename") String genrename) {
		GameCollection games = new GameCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {	
			stmt = conn.prepareStatement(GET_GAME_BY_GENRE_QUERY);
			stmt.setString(1, "%" + genrename + "%");
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setGameid(rs.getInt("gameid"));
				game.setUsername(rs.getString("username"));
				game.setTitle(rs.getString("title"));
				game.setSynopsis(rs.getString("synopsis"));
				game.setGenrename(rs.getString("genrename"));
				game.setCompany(rs.getString("company"));
				game.setYear(rs.getString("year"));
				game.setImageurl(rs.getString("imageurl"));			;
				game.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				games.addGame(game);
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
	 
		return games;
	}
	
	
	@GET
	@Path("/platform/{platformname}")
	@Produces(MediaType.HOBBYLIST_API_GAME_COLLECTION)
	public GameCollection getGamesByPlatform(@PathParam("platformname") String platformname) {
		GameCollection games = new GameCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("select games.gameid, games.username, games.title, games.synopsis, games.company, games.year, games.imageurl, games.creation_timestamp, genre.genrename from games inner join genre on games.genreid=genre.genreid where gameid IN " 
					+ "(select gameid from platformsgames where platformid = "
					+ "(select platformid from platforms where platformname = ?)) order by creation_timestamp");
			stmt.setString(1, platformname);
			stmt.executeQuery();

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setGameid(rs.getInt("gameid"));
				game.setUsername(rs.getString("username"));
				game.setTitle(rs.getString("title"));
				game.setSynopsis(rs.getString("synopsis"));
				game.setGenrename(rs.getString("genrename"));
				game.setCompany(rs.getString("company"));
				game.setYear(rs.getString("year"));
				game.setImageurl(rs.getString("imageurl"));			;
				game.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				games.addGame(game);
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
		return games;
	}
	
	private String INSERT_GAME_QUERY = "insert into games (username, title, synopsis, genreid, company, year, imageurl) values (?, ?, ?, ?, ?, ?, ?)";
	@POST
	@Consumes(MediaType.HOBBYLIST_API_GAME)
	@Produces(MediaType.HOBBYLIST_API_GAME)
	public Game createGame(Game game) {
		validateGame(game);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_GAME_QUERY,Statement.RETURN_GENERATED_KEYS);	 
			stmt.setString(1, security.getUserPrincipal().getName());
			stmt.setString(2, game.getTitle());
			stmt.setString(3, game.getSynopsis());
			stmt.setInt(4, game.getGenreid());
			stmt.setString(5, game.getCompany());
			stmt.setString(6, game.getYear());
			stmt.setString(7, game.getImageurl());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				int gameid = rs.getInt(1);
	 
				game = getGameFromDatabase(Integer.toString(gameid));
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
	 
		return game;
	}
	
	private String DELETE_GAME_QUERY = "delete from games where gameid=?";
	 
	@DELETE
	@Path("/{gameid}")
	public void deleteGame(@PathParam("gameid") String gameid) {
		validateUser(gameid);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_GAME_QUERY);
			stmt.setInt(1, Integer.valueOf(gameid));
	 
			int rows = stmt.executeUpdate();
			if (rows == 0)
				throw new NotFoundException("There's no game with gameid="
						+ gameid);
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
	
	private String UPDATE_GAME_QUERY = "update games set username =ifnull(?, username), title=ifnull(?, title), synopsis=ifnull(?, synopsis), genreid=ifnull(?, genreid), company=ifnull(?, company), year=ifnull(?, year), imageurl=ifnull(?, imageurl) where gameid=?";
	 
	@PUT
	@Path("/{gameid}")
	@Consumes(MediaType.HOBBYLIST_API_GAME)
	@Produces(MediaType.HOBBYLIST_API_GAME)
	public Game updateGame(@PathParam("gameid") String gameid, Game game) {
		validateUpdateGame(game);
		validateUser(gameid);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_GAME_QUERY);
			stmt.setString(1, security.getUserPrincipal().getName());
			stmt.setString(2, game.getTitle());
			stmt.setString(3, game.getSynopsis());
			stmt.setInt(4, game.getGenreid());
			stmt.setString(5, game.getCompany());
			stmt.setString(6, game.getYear());
			stmt.setString(7, game.getImageurl());
			stmt.setInt(8, Integer.valueOf(gameid));
	 
			int rows = stmt.executeUpdate();
			if (rows == 1)
				game = getGameFromDatabase(gameid);
			else {
				throw new NotFoundException("There's no game with gameid=" + gameid);
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
	 
		return game;
	}
	
	private void validateGame(Game game) {
		if (game.getUsername() == null)
			throw new BadRequestException("Username can't be null.");
		if (game.getTitle() == null)
			throw new BadRequestException("Title can't be null.");
		if (game.getSynopsis() == null)
			throw new BadRequestException("Synopsis can't be null.");
		if (game.getCompany() == null)
			throw new BadRequestException("Company can't be null.");
		if (game.getYear() == null)
			throw new BadRequestException("Year can't be null.");
		if (game.getUsername().length() > 20)
			throw new BadRequestException("Username can't be greater than 20 characters.");
		if (game.getTitle().length() > 100)
			throw new BadRequestException("Title can't be greater than 100 characters.");
		if (game.getSynopsis().length() > 500)
			throw new BadRequestException("Synopsis can't be greater than 500 characters.");
		if (game.getCompany().length() > 100)
			throw new BadRequestException("Company can't be greater than 100 characters.");
		if (game.getYear().length() > 20)
			throw new BadRequestException("Year can't be greater than 20 characters.");
		if (game.getImageurl().length() > 200)
			throw new BadRequestException("Imageurl can't be greater than 200 characters.");
	}
	
	private void validateUpdateGame(Game game) {
		if (game.getTitle() != null && game.getTitle().length() > 100)
			throw new BadRequestException(
					"Title can't be greater than 100 characters.");
		if (game.getSynopsis() != null && game.getSynopsis().length() > 500)
			throw new BadRequestException(
					"Synopsis can't be greater than 500 characters.");
		if (game.getCompany() != null && game.getCompany().length() > 100)
			throw new BadRequestException(
					"Company can't be greater than 100 characters.");
		if (game.getYear() != null && game.getYear().length() > 20)
			throw new BadRequestException(
					"Year can't be greater than 20 characters.");
		if (game.getImageurl() != null && game.getImageurl().length() > 200)
			throw new BadRequestException(
					"Year can't be greater than 200 characters.");
	}
	
	private Game getGameFromDatabase(String gameid) {
		Game game = new Game();
	 
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
			if (rs.next()) {
				game.setGameid(rs.getInt("gameid"));
				game.setUsername(rs.getString("username"));
				game.setTitle(rs.getString("title"));
				game.setSynopsis(rs.getString("synopsis"));
				game.setGenrename(rs.getString("genrename"));
				game.setCompany(rs.getString("company"));
				game.setYear(rs.getString("year"));
				game.setImageurl(rs.getString("imageurl"));
				game.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			} else {
				throw new NotFoundException("There's no game with gameid="+ gameid);
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
	 
		return game;
	}
	
	private void validateUser(String gameid) {
	    Game game = getGameFromDatabase(gameid);
	    String username = game.getUsername();
		if (!security.getUserPrincipal().getName().equals(username))
			throw new ForbiddenException(
					"You are not allowed to modify this game.");
	}
	
	@Context
	private SecurityContext security;

}
