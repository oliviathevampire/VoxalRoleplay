package uk.co.phoenixfirewingz.share.game;

import java.io.Serializable;

public class Block implements Serializable
{
    private class Language implements Serializable
    {
        String eng_gb;
    }
    private class Textures implements Serializable
    {
        String sides;
        String top;
        String bottom;
        String front;
        String back;
        String left;
        String right;
    }
    private Language block_name;
    private String lua_identifier = "null_block";
    private String block_type;
    private Textures texture;


    public Block(Language block_name,Textures texture,String block_type)
    {
        this.block_name = block_name;
        this.texture = texture;
        this.block_type = block_type;
    }

    public Language getBlockName() {
        return block_name;
    }

    public String getLuaIdentifier() {
        return lua_identifier;
    }

    public Textures getTexture() {
        return texture;
    }
    public void setLuaIdentifier(String lua_identifier) {
        this.lua_identifier = lua_identifier;
    }
}
