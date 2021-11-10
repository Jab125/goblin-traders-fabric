package net.hat.gt;

import com.jab125.util.TradeLib;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.hat.gt.config.GoblinTradersConfig;
import net.hat.gt.init.*;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";
    public static GoblinTradersConfig config;
    public static final Logger LOGGER = LogManager.getLogger("goblintraders");

    @Override
    public void onInitialize() {
        AutoConfig.register(GoblinTradersConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(GoblinTradersConfig.class).getConfig();

        ModGameRules.registerGameRules();
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModStats.registerStats();
        ModPotions.registerPotions();
        ModPotions.registerPotionRecipes();
        ModSpawns.init();
        TradeLib.register();
    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
