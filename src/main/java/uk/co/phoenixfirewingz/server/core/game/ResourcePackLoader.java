package uk.co.phoenixfirewingz.server.core.game;

import com.google.gson.Gson;
import uk.co.phoenixfirewingz.server.core.GameServer;
import uk.co.phoenixfirewingz.server.util.FileIO;
import uk.co.phoenixfirewingz.server.util.Identifier;
import uk.co.phoenixfirewingz.share.game.*;
import uk.co.phoenixfirewingz.share.util.Tuple;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourcePackLoader {

    private static class Entry {
        private String key = ".";
        private List<Tuple<String>> value = new ArrayList<>();

        public Entry() {}

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

    public ResourcePackLoader() {}

    private static int find(String path, List<Entry> files) {
        int i = 0;
        for (Entry e : files) {
            if(e.getKey().equals(path)) return i;
            i++;
        }
        return -1;
    }

    private static void loadEntries(ZipFile zipFile, List<LoadEntry<?>> entries, GameServer server) {
        server.clearReg();
        Gson gson = new Gson();
        entries.forEach(loadEntry -> {
            Entry entry = loadEntry.entry();
            Class<?> aClass = loadEntry.aClass();
            Registry<?> registry = loadEntry.registry();
            for (Tuple<String> path : entry.getValue()) {
                try {
                    InputStreamReader stream = new InputStreamReader(zipFile.getInputStream(zipFile.getEntry(path.getTwo())));
                    registry.register(path.getOne(), gson.fromJson(stream, (Type) aClass));
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public static void load(String path, GameServer server) {
        String _path_;
        if(path == null) {
            try {
                _path_ = FileIO.getAssetPath(new Identifier("","ResourcePack", "default.zip")).getPath();
            } catch (URISyntaxException e) {
                GameServer.getLOGGER().trace(e.getMessage());
                return;
            }
        } else _path_ = path;

        try {
            ZipFile zipFile = new ZipFile(_path_);
            List<Entry> files = new ArrayList<>();
            {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                String to_remove = entries.nextElement().getName();
                Entry files_e = new Entry();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (!entry.isDirectory()) {
                        File file = new File(entry.getName());
                        if(!file.exists())
                            files_e.getValue().add(new Tuple<>(file.getName().replace(".json",""), entry.getName()));
                    } else {
                        if(!files_e.getValue().isEmpty()) {
                            files.add(files_e);
                            files_e = new Entry();
                        }
                        files_e.setKey(entry.getName().replace(to_remove,""));
                    }
                }
            }
            loadEntries(
                zipFile,
                List.of(
                        new LoadEntry<>(files.get(find("assets/VoxelRoleplay/block/", files)), Block.class, Registries.BLOCK),
                        new LoadEntry<>(files.get(find("assets/VoxelRoleplay/item/", files)), Item.class, Registries.ITEM),
                        new LoadEntry<>(files.get(find("assets/VoxelRoleplay/entity/", files)), Entity.class, Registries.ENTITY)
                ),
                server
            );
            zipFile.close();
        } catch (IOException e) {
            GameServer.getLOGGER().trace(e.getMessage());
        }
    }

    private record LoadEntry<T extends BaseContent>(Entry entry, Class<T> aClass, Registry<T> registry) {}
}
