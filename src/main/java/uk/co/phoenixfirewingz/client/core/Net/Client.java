package uk.co.phoenixfirewingz.client.core.Net;

import uk.co.phoenixfirewingz.client.core.Main;
import uk.co.phoenixfirewingz.share.game.Block;
import uk.co.phoenixfirewingz.share.game.Entity;
import uk.co.phoenixfirewingz.share.game.Item;
import uk.co.phoenixfirewingz.share.network.PackageSubType;
import uk.co.phoenixfirewingz.share.network.packages.GiveRegistry;
import uk.co.phoenixfirewingz.share.network.packages.RequestRegistry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private Socket client;
    private ObjectInputStream server_read;
    private ObjectOutputStream server_write;

    @Override
    public void run() {
        try {
            client = new Socket("localhost",25567);
            server_write = new ObjectOutputStream(client.getOutputStream());
            server_read = new ObjectInputStream(client.getInputStream());
        } catch (Exception e) {
            Main.getLogger().error(e.getMessage());
            return;
        }
        try {
            server_write.writeObject(new RequestRegistry(PackageSubType.BLOCK));
            GiveRegistry<Block> blockGiveRegistry = (GiveRegistry<Block>) server_read.readObject();
            Main.setBlockRegistry(blockGiveRegistry.registry);

            server_write.writeObject(new RequestRegistry(PackageSubType.ITEM));
            GiveRegistry<Item> itemGiveRegistry = (GiveRegistry<Item>) server_read.readObject();
            Main.setItemRegistry(itemGiveRegistry.registry);

            server_write.writeObject(new RequestRegistry(PackageSubType.ENTITY));
            GiveRegistry<Entity> entityGiveRegistry = (GiveRegistry<Entity>) server_read.readObject();
            Main.setEntityRegistry(entityGiveRegistry.registry);
        } catch (Exception e) {
            Main.getLogger().error(e.getMessage());
        }

    }
}
