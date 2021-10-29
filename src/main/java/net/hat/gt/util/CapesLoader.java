package net.hat.gt.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hat.gt.GobT;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class CapesLoader {

    public static class PlayerCapeData {
        public String uuid;
        public String cape;
        public String name;
    }

    public static HashMap<String, String> UUID_CAPE_MAP = new HashMap<>();
    private static final Type PLAYER_CAPE_DATA_TYPE = new TypeToken<List<PlayerCapeData>>() {
    }.getType();

    public static void load() {
        Util.getMainWorkerExecutor().execute(() -> {
            Gson gson = new GsonBuilder().create();
            try {
                List<PlayerCapeData> players = gson.fromJson(
                        IOUtils.toString(
                                new URL("https://raw.githubusercontent.com/3e2j/goblin-traders-fabric/1.17/capes.json"),
                                StandardCharsets.UTF_8
                        ),
                        PLAYER_CAPE_DATA_TYPE
                );
                for (var player : players) {
                    UUID_CAPE_MAP.put(player.uuid, player.cape);
                }
            } catch (IOException ignored) {
            }
        });
    }
    public static NativeImage parseCape(final NativeImage nativeImageIn)
    {
        int imageWidth = 64;
        int imageHeight = 32;
        int imageSrcWidth = nativeImageIn.getWidth();
        int imageSrcHeight = nativeImageIn.getHeight();

        while (imageWidth < imageSrcWidth || imageHeight < imageSrcHeight)
        {
            imageWidth *= 2;
            imageHeight *= 2;
        }

        NativeImage nativeImage = new NativeImage(imageWidth, imageHeight, true);

        for (int x = 0; x < imageSrcWidth; x++)
        {
            for (int y = 0; y < imageSrcHeight; y++)
            {
                nativeImage.setPixelColor(x, y, nativeImageIn.getPixelColor(x, y));
            }
        }

        nativeImageIn.close();

        return nativeImage;
    }
}
