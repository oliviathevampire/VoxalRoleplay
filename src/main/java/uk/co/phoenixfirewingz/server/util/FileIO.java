package uk.co.phoenixfirewingz.server.util;

import uk.co.phoenixfirewingz.server.core.Main;
import java.net.URI;
import java.net.URISyntaxException;


public class FileIO
{
    public static URI getAssetPath(Identifier id) throws URISyntaxException {
        return Main.class.getClassLoader().getResource(id.getPath()).toURI();
    }
}
