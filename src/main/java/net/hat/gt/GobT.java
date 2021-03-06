package net.hat.gt;


import com.jab125.limeappleboat.gobt.api.GobTEvents;
import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.impl.CapeCommand;
import com.jab125.util.tradehelper.TradeManager;
import com.jab125.util.tradehelper.type.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.hat.gt.commands.PurgeGoblinData;
import net.hat.gt.config.GoblinTradersConfig;
import net.hat.gt.init.*;
import net.hat.gt.spawning.SpawnHandler;
import net.minecraft.block.Blocks;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BuiltinBiomes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";
    private static final Set<String> random_background = new HashSet<>();
    public static GoblinTradersConfig config;
    public static final Logger LOGGER = LogManager.getLogger("goblintraders");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated, e) -> {
            PurgeGoblinData.register(dispatcher);
        }));
        if (isModInstalled("requiem-api")) {
        }
        AutoConfig.register(GoblinTradersConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(GoblinTradersConfig.class).getConfig();
        TradeManager manager = TradeManager.instance();
        manager.registerTrader(ModEntities.GOBLIN_TRADER);
        manager.registerTrader(ModEntities.VEIN_GOBLIN_TRADER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(manager);

//        ServerWorldEvents.LOAD.register(new SpawnHandler.WorldLoad());
//        ServerLifecycleEvents.SERVER_STOPPED.register(new SpawnHandler.ServerStopped());
//        ServerTickEvents.END_WORLD_TICK.register(new SpawnHandler.OnWorldTick());
        EventTaxi.registerEventTaxiSubscriber(SpawnHandler.class);
        manager.registerTypeSerializer(BasicTrade.SERIALIZER);
        manager.registerTypeSerializer(BundleTrade.SERIALIZER);
        manager.registerTypeSerializer(PotionTrade.SERIALIZER);
        manager.registerTypeSerializer(ContainerTrade.SERIALIZER);
        manager.registerTypeSerializer(EnchantedItemTrade.SERIALIZER);
        manager.registerTypeSerializer(UpgradedBasicTrade.SERIALIZER);
        manager.registerTypeSerializer(UpgradedBundleTrade.SERIALIZER);
        manager.registerTypeSerializer(UpgradedPotionTrade.SERIALIZER);
        manager.registerTypeSerializer(UpgradedContainerTrade.SERIALIZER);
        manager.registerTypeSerializer(UpgradedEnchantedItemTrade.SERIALIZER);
        manager.registerTypeSerializer(UpgradedOnlyInEndBasicTrade.SERIALIZER);

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
        //ModPotions.registerPotions();
        ModPotions.registerPotionRecipes();
        ModStatusEffects.registerStatusEffects();
        ModSensorTypes.registerSensorTypes();


        // Restrict spawning on bedrock and the deep dark
        GobTEvents.ON_ATTEMPT_SPAWN.register(((goblinTraderType, world, safestPos) -> {
            if (((world.getBlockState(safestPos.down()).getBlock().equals(Blocks.BEDROCK) || world.getBiome(safestPos).getKey().isPresent()) && world.getBiome(safestPos).getKey().get().equals(BiomeKeys.DEEP_DARK)) && (goblinTraderType.equals(ModEntities.GOBLIN_TRADER) || goblinTraderType.equals(ModEntities.VEIN_GOBLIN_TRADER))) {
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        }));
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
