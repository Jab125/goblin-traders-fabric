package net.hat.gt.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hat.gt.GobT;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CapesLoader {
    public static Map PLAYERS;

    public static void load() {
        Util.getMainWorkerExecutor().execute(() -> {
            long startLoad = System.currentTimeMillis();
            Gson gson = new GsonBuilder().create();
            GobT.LOGGER.info("Loading capes data...");
            try {
                PLAYERS = gson.fromJson(
                        IOUtils.toString(
                                new URL("https://raw.githubusercontent.com/3e2j/goblin-traders-fabric/1.17/capes.json"),
                                StandardCharsets.UTF_8
                        ),
                        Map.class
                );
            } catch (IOException e) {
                GobT.LOGGER.warn("Failed to load capes.", e);
            }
            GobT.LOGGER.info("Loaded capes for {} players. (Took {}ms)", PLAYERS.size(), System.currentTimeMillis()-startLoad);
        });
    }
}
