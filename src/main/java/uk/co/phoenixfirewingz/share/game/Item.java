package uk.co.phoenixfirewingz.share.game;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Item extends BaseContent {

    //this is unique to items used for block items
    @SerializedName("block") private String block;
    public Item(Map<String, String> name, String type, Map<String, String> textures, String block) {
        super(name, type, textures);
        this.block = block;
    }
    public Item(Map<String, String> name, String type, Map<String, String> textures) {
        super(name, type, textures);
    }
}
