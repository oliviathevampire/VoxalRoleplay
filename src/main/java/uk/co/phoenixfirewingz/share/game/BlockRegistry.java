package uk.co.phoenixfirewingz.share.game;

import java.io.Serializable;
import java.util.Vector;

public class BlockRegistry implements Serializable
{
    Vector<Block> blocks = new Vector<>();
    public BlockRegistry()
    {

    }

    public void addBlock(String lua_identifier,Block block)
    {
        block.setLuaIdentifier(lua_identifier);
        blocks.add(block);
    }

    public void clear()
    {
        blocks.clear();
    }
}
