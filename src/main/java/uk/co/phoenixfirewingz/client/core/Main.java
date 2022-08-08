package uk.co.phoenixfirewingz.client.core;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.phoenixfirewingz.client.core.Net.Client;
import uk.co.phoenixfirewingz.client.core.Renderer.GameRenderer;
import uk.co.phoenixfirewingz.server.core.GameServer;
import uk.co.phoenixfirewingz.share.game.BlockRegistry;
import static uk.co.phoenixfirewingz.share.util.Linker.link;

public class Main {
    static GameRenderer render_thread;
    static BlockRegistry block_registry;

    static Logger LOGGER = LoggerFactory.getLogger("VoxelRoleplayClient");

    public static void main(String[] args) {
        link();
        BasicConfigurator.configure();
        render_thread = new GameRenderer();
        render_thread.start();
        new Thread(new Client()).start();
        while (!getRenderer().getWindow().shouldClose())
        {
            int x = 0;
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static void setBlockRegistry(BlockRegistry registry) {
        block_registry = registry;
    }

    public static GameRenderer getRenderer() {
        return render_thread;
    }
}
