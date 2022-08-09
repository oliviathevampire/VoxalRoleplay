package uk.co.phoenixfirewingz.share.game;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BaseContent extends RegistryEntry {

	@SerializedName("name") private final Map<String, String> name;
	@SerializedName("type") private String type;
	@SerializedName("texture") private Map<String, String> textures;

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
