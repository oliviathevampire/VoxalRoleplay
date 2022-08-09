package uk.co.phoenixfirewingz.share.util;

import java.io.File;

public class Linker {
    public static void link()
    {
        String link_path = System.getProperty("java.library.path");
        link_path = link_path + new File(new File(Linker.class.getClassLoader().getResource("libs/here.find").getPath()).getParent()).getPath().replace("file:","");
        System.setProperty("java.library.path",link_path);
    }
}
