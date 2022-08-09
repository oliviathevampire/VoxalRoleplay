package uk.co.phoenixfirewingz.share.game;

import java.io.Serializable;
import java.util.Vector;

public class Registry<T extends BaseContent> implements Serializable {

    Vector<T> entries = new Vector<>();

    public Registry() {}

    public void add(String lua_identifier, T entry) {
        entry.setLuaIdentifier(lua_identifier);
        entries.add(entry);
    }

    public void clear() {
        entries.clear();
    }
}
