package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

import java.util.ArrayList;
import java.util.List;

public class PlatformCollection {
	
	private List<Platform> platforms;

	public PlatformCollection() {
		super();
		platforms = new ArrayList<>();
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}	
	
	public void addPlatform(Platform platform) {
		platforms.add(platform);
	}
}
