package uk.co.phoenixfirewingz.share.game;

import java.util.Map;

public class BaseContent extends RegistryEntry {

	private final Map<String, String> name;
	private final String type;
	private final Map<String, String> textures;

	public BaseContent(Map<String, String> name, String type, Map<String, String> textures) {
		this.name = name;
		this.type = type;
		this.textures = textures;
	}

	public Map<String, String> getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Map<String, String> getTextures() {
		return textures;
	}
}
