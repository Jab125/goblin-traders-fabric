package net.hat.gt.spawning;

import com.jab125.thonkutil.api.Tick;
import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.server.ServerStartEvent;
import com.jab125.thonkutil.api.events.server.ServerStopEvent;
import com.jab125.thonkutil.api.events.server.world.ServerWorldLoadEvent;
import com.jab125.thonkutil.api.events.world.WorldTickEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.hat.gt.init.ModEntities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Reworked for fabric
 * <p>
 * So basically, there is no {@code SubscribeEvent} in fabric, so we rely on Fabric API's events.
 */
public class SpawnHandler {
    private static final Map<Identifier, ArrayList<GoblinTraderSpawner>> spawners = new HashMap<>();
    public SpawnHandler(){}

    static boolean loaded = false;
    @SubscribeEvent
    public static void onServerStart(ServerStartEvent event) {

        //System.out.println("WORLD LOAD");
        addToSpawners(DimensionType.OVERWORLD_REGISTRY_KEY.getValue(), new GoblinTraderSpawner(event.server(), "GoblinTrader", ModEntities.GOBLIN_TRADER, Objects.requireNonNull(ModEntities.GOBLIN_TRADER.create(event.server().getOverworld()))));
        addToSpawners(DimensionType.THE_NETHER_REGISTRY_KEY.getValue(), new GoblinTraderSpawner(event.server(), "VeinGoblinTrader", ModEntities.VEIN_GOBLIN_TRADER, Objects.requireNonNull(ModEntities.GOBLIN_TRADER.create(event.server().getOverworld()))));
        loaded = true;
    }

    @SubscribeEvent
    public static void onServerStop(ServerStopEvent event) {
        spawners.clear();
    }

    @SubscribeEvent
    public static void onWorldTick(WorldTickEvent event) {
        //System.out.println("WORLD RICK ASTLEY");
        if(!event.phase().equals(Tick.Phase.END)) return;
        if (event.isClient()) {
            return;
        }
        ArrayList<GoblinTraderSpawner> spawnerList = spawners.get(event.world().getRegistryKey().getValue());
        if (spawnerList != null) {
            for (var spawner : spawnerList) {
                if (spawner != null) {
                    spawner.tick(event.world());
                    //System.out.println("TICK");
                }
            }
        } else;
    }

    @Deprecated
    public static Map<Identifier, ArrayList<GoblinTraderSpawner>> getSpawners() {
        return spawners;
    }
    public static void addToSpawners(Identifier world, GoblinTraderSpawner spawner) {
        if (!spawners.containsKey(world)) {
            spawners.put(world, new ArrayList<>());
        }
        spawners.get(world).add(spawner);
        //System.out.println(Arrays.toString(spawners.get(world).toArray()));
    }
}
