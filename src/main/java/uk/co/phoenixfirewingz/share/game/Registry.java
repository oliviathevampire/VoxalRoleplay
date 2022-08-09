package uk.co.phoenixfirewingz.share.game;

import java.util.Vector;

public class Registry<T extends RegistryEntry> extends RegistryEntry {

    Vector<T> entries = new Vector<>();

    public Registry() {}

    public T register(String lua_identifier, T entry) {
        entry.setLuaIdentifier(lua_identifier);
        entries.add(entry);
        return entry;
    }

    public Vector<T> getEntries() {
        return entries;
    }

    public void clear() {
        entries.clear();
    }
}
