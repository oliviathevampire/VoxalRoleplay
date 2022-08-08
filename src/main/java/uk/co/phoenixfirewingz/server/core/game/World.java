package uk.co.phoenixfirewingz.server.core.game;

import com.google.gson.Gson;
import uk.co.phoenixfirewingz.server.util.FileIO;
import uk.co.phoenixfirewingz.server.util.Identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Random;

public class World extends Thread
{
    private int seed = 0;
    String name = "";
    public World(String world_name)
    {
        try {
            Gson gson = new Gson();
            File file = new File(FileIO.getAssetPath(new Identifier("", "Save", world_name)).getPath());
            World temp = gson.fromJson(new FileReader(file), World.class);
            this.seed = temp.seed;
        } catch (Exception e) {
            name = world_name;
            Random random = new Random();
            seed = random.nextInt();
        }

    }

    @Override
    public void start() {

    }
}
