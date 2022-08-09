package uk.co.phoenixfirewingz.share.game;

import java.io.Serializable;

public class RegistryEntry implements Serializable {

	private String luaIdentifier = "null";

	public String getLuaIdentifier() {
		return luaIdentifier;
	}

	public void setLuaIdentifier(String lua_identifier) {
		this.luaIdentifier = lua_identifier;
	}
}
