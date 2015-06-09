package edu.upc.eetac.dsa.rubenpg.hobbylist.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.Invite;
import edu.upc.eetac.dsa.rubenpg.hobbylist.api.model.InviteCollection;

@Path("/invites")
public class InviteResource {
private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_INVITES_QUERY = "select * from invites where stateid = 3 order by creationTimestamp";

	@GET
	@Produces(MediaType.HOBBYLIST_API_INVITE_COLLECTION)
	public InviteCollection getInvites(@QueryParam("inviteid") String inviteid) {
		InviteCollection invites = new InviteCollection();
	 
		Connection conn = null;	
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;
		try {				
			stmt = conn.prepareStatement(GET_INVITES_QUERY);
						
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				Invite invite = new Invite();
				invite.setInvid(rs.getInt("invid"));
				invite.setSender(rs.getString("sender"));
				invite.setReceiver(rs.getString("receiver"));
				invite.setGameid(rs.getInt("gameid"));
				invite.setStateid(rs.getInt("stateid"));
				invite.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());								
				invites.addInvite(invite);
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
	 
		return invites;
	}

	private String GET_INVITE_BY_ID_QUERY = "select * from invites where invid=?";
	 
	@GET
	@Path("/{invid}")
	@Produces(MediaType.HOBBYLIST_API_INVITE)
	public Invite getInvite(@PathParam("invid") String invid) {
		Invite invite = new Invite();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		
		PreparedStatement stmt = null;				
		try {				
			stmt = conn.prepareStatement(GET_INVITE_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(invid));
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				invite.setInvid(rs.getInt("invid"));
				invite.setSender(rs.getString("sender"));
				invite.setReceiver(rs.getString("receiver"));
				invite.setGameid(rs.getInt("gameid"));
				invite.setStateid(rs.getInt("stateid"));
				invite.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());					
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
	 
		return invite;
	}
	
	private String INSERT_INVITE_QUERY = "insert into invites (sender, receiver, gameid, stateid) values (?, ?, ?, 3)";
	 
	@POST
	@Consumes(MediaType.HOBBYLIST_API_INVITE)
	@Produces(MediaType.HOBBYLIST_API_INVITE)
	public Invite createInvite(Invite invite) {
		validateInvite(invite);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_INVITE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
	 
			stmt.setString(1, invite.getSender());
			stmt.setString(2, invite.getReceiver());
			stmt.setInt(3, invite.getGameid());			
			//stmt.setString(6, security.getUserPrincipal().getName());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int invid = rs.getInt(1);
	 
				invite = getInviteFromDatabase(Integer.toString(invid));
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
	 
		return invite;
	}
	
	private void validateInvite(Invite invite) {
		if (invite.getSender() == null)
			throw new BadRequestException("Sender can't be null.");
		if (invite.getReceiver() == null)
			throw new BadRequestException("Receiver can't be null.");
		if (invite.getSender().length() > 20)
			throw new BadRequestException("Sender can't be greater than 20 characters.");
		if (invite.getReceiver().length() > 20)
			throw new BadRequestException("Receiver can't be greater than 20 characters.");	
	}
	
	private Invite getInviteFromDatabase(String invid) {
		Invite invite = new Invite();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_INVITE_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(invid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				invite.setSender(rs.getString("sender"));
				invite.setReceiver(rs.getString("receiver"));
				invite.setGameid(rs.getInt("gameid"));
				invite.setStateid(rs.getInt("stateid"));								
				invite.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			} else {
				throw new NotFoundException("There's no invite with invid="+ invid);
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
	 
		return invite;
	}
}
