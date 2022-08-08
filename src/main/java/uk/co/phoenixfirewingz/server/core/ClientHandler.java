package uk.co.phoenixfirewingz.server.core;

import uk.co.phoenixfirewingz.share.network.NetPackage;
import uk.co.phoenixfirewingz.share.network.packages.GiveBlockRegistry;

import java.io.*;
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

        while (client.isConnected())
        {
            try {
                sleep(2);
                Object pack =  read_client.readObject();
                switch (((NetPackage)pack).type)
                {
                    case PING:
                        write_client.writeObject(new NetPackage(PONG));
                        break;
                    case GET:
                    {
                        switch (((NetPackage)pack).sub_type)
                        {
                            case BLOCK:{
                                GameServer.getLOGGER().info(client.toString() + ": requested block reg");
                                write_client.writeObject(new GiveBlockRegistry(server.block_registry));
                                break;
                            }
                        }
                    }
                    default: {
                        break;
                    }
                }
            } catch (Exception e) {
                GameServer.getLOGGER().trace(e.getMessage());
            }
        }
    }
}
