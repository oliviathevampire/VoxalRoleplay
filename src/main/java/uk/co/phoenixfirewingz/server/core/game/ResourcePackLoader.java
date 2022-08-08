package uk.co.phoenixfirewingz.server.core.game;

import com.google.gson.Gson;
import uk.co.phoenixfirewingz.server.core.GameServer;
import uk.co.phoenixfirewingz.server.util.FileIO;
import uk.co.phoenixfirewingz.server.util.Identifier;
import uk.co.phoenixfirewingz.share.game.Block;
import uk.co.phoenixfirewingz.share.util.Tuple;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourcePackLoader
{
    private static class Entry
    {
        private String key = ".";
        private List<Tuple<String>> value = new ArrayList<>();
        public Entry()
        {
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(List<Tuple<String>> value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public List<Tuple<String>> getValue() {
            return value;
        }
    }

    public ResourcePackLoader()
    {

    }

    private static int find(String path,List<Entry> files)
    {
        int i = 0;
        for (Entry e:files)
        {
            if(e.getKey().equals(path))
                return i;
            i++;
        }
        return -1;
    }

    private static void loadBlocks(ZipFile zipFile,Entry blocks,GameServer server) throws IOException
    {
        server.clearReg();
        Gson gson = new Gson();
        for (Tuple<String> path: blocks.getValue())
            server.block_registry.addBlock(path.getOne(),gson.fromJson(new InputStreamReader(zipFile.getInputStream(zipFile.getEntry(path.getTwo()))), Block.class));
    }


    public static void load(String path,GameServer server)
    {
        String _path_;
        if(path == null)
        {
            try {
                _path_ = FileIO.getAssetPath(new Identifier("","ResourcePack", "default.zip")).getPath();
            } catch (URISyntaxException e) {
                GameServer.getLOGGER().trace(e.getMessage());
                return;
            }
        }
        else _path_ = path;
        try
        {

            ZipFile zipFile = new ZipFile(_path_);
            List<Entry> files = new ArrayList<>();
            {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                String to_remove = entries.nextElement().getName();
                Entry files_e = new Entry();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (!entry.isDirectory())
                    {
                        File file = new File(entry.getName());
                        if(!file.exists())
                        {
                            files_e.getValue().add(new Tuple<>(file.getName().replace(".json",""),entry.getName()));
                        }
                    }
                    else
                    {
                        if(!files_e.getValue().isEmpty())
                        {
                            files.add(files_e);
                            files_e = new Entry();
                        }
                        files_e.setKey(entry.getName().replace(to_remove,""));
                    }
                }
            }
            int x = 0;
            loadBlocks(zipFile,files.get(find("assets/VoxelRoleplay/block/",files)),server);
            zipFile.close();

        } catch (IOException e) {
            GameServer.getLOGGER().trace(e.getMessage());
            return;
        }
    }
}
