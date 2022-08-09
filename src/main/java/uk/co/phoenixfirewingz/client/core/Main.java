package uk.co.phoenixfirewingz.client.core;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.phoenixfirewingz.client.core.Net.Client;
import uk.co.phoenixfirewingz.client.core.Renderer.GameRenderer;
import uk.co.phoenixfirewingz.share.game.Block;
import uk.co.phoenixfirewingz.share.game.Entity;
import uk.co.phoenixfirewingz.share.game.Item;
import uk.co.phoenixfirewingz.share.game.Registry;

import static uk.co.phoenixfirewingz.share.util.Linker.link;

public class Main {
    static GameRenderer render_thread;
    static Registry<Block> blockRegistry;
    static Registry<Item> itemRegistry;
    static Registry<Entity> entityRegistry;

    static Logger LOGGER = LoggerFactory.getLogger("VoxelRoleplayClient");

    public static void main(String[] args) {
        link();
        BasicConfigurator.configure();
        render_thread = new GameRenderer();
        render_thread.start();
        new Thread(new Client()).start();
        while (!getRenderer().getWindow().shouldClose()) {}
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static void setBlockRegistry(Registry<Block> registry) {
        blockRegistry = registry;
    }

    public static void setItemRegistry(Registry<Item> registry) {
        itemRegistry = registry;
    }

    public static void setEntityRegistry(Registry<Entity> entityRegistry) {
        Main.entityRegistry = entityRegistry;
    }

    public static GameRenderer getRenderer() {
        return render_thread;
    }
}
