package uk.co.phoenixfirewingz.server.core;

import static uk.co.phoenixfirewingz.share.util.Linker.link;

public class Main
{
    static GameServer server;
    public static void main(String[] args)
    {
        server = new GameServer(args);
        server.run();
        server.close();
    }
}
