package uk.co.phoenixfirewingz.server.core;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.phoenixfirewingz.server.core.game.ResourcePackLoader;
import uk.co.phoenixfirewingz.server.core.game.World;
import uk.co.phoenixfirewingz.share.game.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static uk.co.phoenixfirewingz.share.util.Linker.link;

public class GameServer {
    ServerSocket socket;
    static Logger LOGGER = LoggerFactory.getLogger("VoxelRoleplayServer");
    public Registry<Block> blockRegistry = Registries.BLOCK;
    public Registry<Item> itemRegistry = Registries.ITEM;
    public Registry<Entity> entityRegistry = Registries.ENTITY;
    public static Thread world;

    //TODO: make server controllable from args
    GameServer(String[] args) {
        link();
        ArgumentParser parser = ArgumentParsers.newFor("VoxelRoleplayGameServer").build().defaultHelp(true).description("Game Server Args");
        parser.addArgument("-a", "--AssetsPack").setDefault((Object) null).help("Location of Game Assets").required(true);
        BasicConfigurator.configure();
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            LOGGER.trace(e.getMessage());
            System.exit(-1);
        }
        ResourcePackLoader.load(ns.getString("AssetsPack"),this);

        try {
            world = new World("newWorld");
            socket = new ServerSocket(25567);
        } catch (Exception e) {
            LOGGER.trace(e.getMessage());
            System.gc();
            System.exit(-1);
        }
    }

    public boolean isStopped() {
        return socket.isClosed();
    }

    public void run() {
        world.start();
        while (!isStopped()) {
            Socket client_socket = null;
            try {
                client_socket = socket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    LOGGER.info("Server Stopped.");
                    return;
                }
                LOGGER.info("Error accepting client connection", e);
            }
            new Thread(new ClientHandler(client_socket,this)).start();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            LOGGER.trace(e.getMessage());
        }
    }

    public void clearReg() {
        blockRegistry.clear();
        itemRegistry.clear();
        entityRegistry.clear();
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }
}
