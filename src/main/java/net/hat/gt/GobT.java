package net.hat.gt;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.hat.gt.config.GoblinTradersConfig;
import net.hat.gt.init.*;
import net.hat.gt.util.Util;
import net.minecraft.util.Identifier;

import java.io.FileNotFoundException;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";
    public static GoblinTradersConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(GoblinTradersConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(GoblinTradersConfig.class).getConfig();

        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModStats.registerStats();
        ModSpawns.init();
    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
