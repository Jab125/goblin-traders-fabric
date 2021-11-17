package net.hat.gt;


import com.jab125.util.datagen.DataGeneraton;
import com.jab125.util.tradehelper.TradeManager;
import com.jab125.util.tradehelper.type.BasicTrade;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.hat.gt.config.GoblinTradersConfig;
import net.hat.gt.init.*;
import net.hat.gt.spawning.SpawnHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";
    private static final Set<String> random_background = new HashSet<>();
    // Mental Note: don't remove this
    private static final boolean doDataGen = false;
    public static GoblinTradersConfig config;
    public static final Logger LOGGER = LogManager.getLogger("goblintraders");

    @Override
    public void onInitialize() {
        AutoConfig.register(GoblinTradersConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(GoblinTradersConfig.class).getConfig();

        TradeManager manager = TradeManager.instance();
        manager.registerTrader(ModEntities.GOBLIN_TRADER);
        manager.registerTrader(ModEntities.VEIN_GOBLIN_TRADER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(manager);

        ServerWorldEvents.LOAD.register(new SpawnHandler.WorldLoad());
        ServerLifecycleEvents.SERVER_STOPPED.register(new SpawnHandler.ServerStopped());
        ServerTickEvents.END_WORLD_TICK.register(new SpawnHandler.OnWorldTick());
        manager.registerTypeSerializer(BasicTrade.SERIALIZER);

        if (isModInstalled("endgoblintraders")) {
            GobT.addToRandomBackground("textures/config/rack_stone.png");
            GobT.addToRandomBackground("textures/config/stone_end.png");
            GobT.addToRandomBackground("textures/config/cheese.png");
        }

        GobT.addToRandomBackground("textures/config/stone_grey.png");
        GobT.addToRandomBackground("textures/config/netherstone.png");
        GobT.addToRandomBackground("textures/config/stoneyrack.png");
        ModGameRules.registerGameRules();
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModStats.registerStats();
        ModPotions.registerPotions();
        ModPotions.registerPotionRecipes();
        //ModSpawns.init();
        // Mental Note: don't remove this
        if (doDataGen) {
            DataGenerator dataGenerator = new DataGenerator(new File("../src/main/generated/resources").toPath(), null);
            DataGeneraton.registerCommonProviders(dataGenerator);
        }
        boolean a;
            a = ResourceManagerHelper.registerBuiltinResourcePack(id("gobtvanillaish"), Objects.requireNonNull(FabricLoader.getInstance().getModContainer(MODID)).get(), ResourcePackActivationType.NORMAL);
    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    private static void addToRandomBackground(String identifier) {
        random_background.add(identifier);
        new Identifier(identifier);
    }

    public static String getRandomBackground() {
        return (String) random_background.toArray()[(int) (Math.random() * random_background.toArray().length)];
    }
}
