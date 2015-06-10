package edu.upc.eetac.dsa.rubenpg.hobbylist.api;

public interface MediaType {
	public final static String HOBBYLIST_API_USER = "application/vnd.hobbylist.api.user+json";
	public final static String HOBBYLIST_API_USER_COLLECTION = "application/vnd.hobbylist.api.user.collection+json";
	public final static String HOBBYLIST_API_GAME = "application/vnd.hobbylist.api.game+json";
	public final static String HOBBYLIST_API_GAME_COLLECTION = "application/vnd.hobbylist.api.game.collection+json";
	public final static String HOBBYLIST_API_PLATFORMGAME = "application/vnd.hobbylist.api.platformgame+json";
	public final static String HOBBYLIST_API_PLATFORMGAME_COLLECTION = "application/vnd.hobbylist.api.platformgame.collection+json";
	public final static String HOBBYLIST_API_FAVS = "application/vnd.hobbylist.api.favs+json";
	public final static String HOBBYLIST_API_FAVS_COLLECTION = "application/vnd.hobbylist.api.favs.collection+json";
	public final static String HOBBYLIST_API_INVITE = "application/vnd.hobbylist.api.invite+json";
	public final static String HOBBYLIST_API_INVITE_COLLECTION = "application/vnd.hobbylist.api.invite.collection+json";
	public final static String HOBBYLIST_API_ERROR = "application/vnd.dsa.hobbylist.error+json";
}