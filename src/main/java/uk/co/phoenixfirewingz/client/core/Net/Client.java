package uk.co.phoenixfirewingz.client.core.Net;

import uk.co.phoenixfirewingz.client.core.Main;
import uk.co.phoenixfirewingz.server.core.GameServer;
import uk.co.phoenixfirewingz.share.network.NetPackage;
import uk.co.phoenixfirewingz.share.network.packages.GiveBlockRegistry;
import uk.co.phoenixfirewingz.share.network.packages.RequestBlockRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static uk.co.phoenixfirewingz.share.network.PackageType.PONG;

public class Client implements Runnable
{
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
            server_write.writeObject(new RequestBlockRegistry());

            GiveBlockRegistry registry = (GiveBlockRegistry) server_read.readObject();
            Main.setBlockRegistry(registry.blockRegistry);
        } catch (Exception e) {
            Main.getLogger().error(e.getMessage());
            return;
        }

    }
}
