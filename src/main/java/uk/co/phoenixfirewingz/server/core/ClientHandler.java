package uk.co.phoenixfirewingz.server.core;

import uk.co.phoenixfirewingz.share.game.Registries;
import uk.co.phoenixfirewingz.share.network.NetPackage;
import uk.co.phoenixfirewingz.share.network.PackageSubType;
import uk.co.phoenixfirewingz.share.network.packages.GiveRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.Thread.sleep;
import static uk.co.phoenixfirewingz.share.network.PackageType.PONG;

public class ClientHandler implements Runnable{
    private GameServer server;
    private Socket client;
    private ObjectInputStream read_client;
    private ObjectOutputStream write_client;
    public ClientHandler(Socket client,GameServer server)
    {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            read_client = new ObjectInputStream(client.getInputStream());
            write_client = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            GameServer.getLOGGER().trace(e.getMessage());
            return;
        }

        while (client.isConnected()) {
            try {
                sleep(2);
                Object pack =  read_client.readObject();
                switch (((NetPackage)pack).type) {
                    case PING:
                        write_client.writeObject(new NetPackage(PONG));
                        break;
                    case GET: {
                        if (((NetPackage) pack).sub_type == PackageSubType.BLOCK) {
                            GameServer.getLOGGER().info(client.toString() + ": requested block reg");
                            write_client.writeObject(new GiveRegistry<>(Registries.BLOCK, PackageSubType.BLOCK));
                        }
                        if (((NetPackage) pack).sub_type == PackageSubType.ITEM) {
                            GameServer.getLOGGER().info(client.toString() + ": requested item reg");
                            write_client.writeObject(new GiveRegistry<>(Registries.ITEM, PackageSubType.ITEM));
                        }
                        if (((NetPackage) pack).sub_type == PackageSubType.ENTITY) {
                            GameServer.getLOGGER().info(client.toString() + ": requested entity reg");
                            write_client.writeObject(new GiveRegistry<>(Registries.ENTITY, PackageSubType.ENTITY));
                        }
                    }
                    default: break;
                }
            } catch (Exception e) {
                GameServer.getLOGGER().trace(e.getMessage());
            }
        }
    }
}
