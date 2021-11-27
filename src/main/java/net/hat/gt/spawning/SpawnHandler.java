package net.hat.gt.spawning;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.hat.gt.GobT;
import net.hat.gt.init.ModEntities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.*;

/**
 * Reworked for fabric
 */
public class SpawnHandler {
    private static Map<Identifier, ArrayList<GoblinTraderSpawner>> spawners = new HashMap<>();
    public SpawnHandler(){}

    public static class WorldLoad implements ServerWorldEvents.Load {
        boolean loaded = false;
        public WorldLoad(){}

        @Override
        public void onWorldLoad(MinecraftServer server, ServerWorld world) {
            if (loaded) return;
            System.out.println("WORLD LOAD");
            addToSpawners(DimensionType.OVERWORLD_REGISTRY_KEY.getValue(), new GoblinTraderSpawner(server, "GoblinTrader", ModEntities.GOBLIN_TRADER, Objects.requireNonNull(ModEntities.GOBLIN_TRADER.create(world))));
            addToSpawners(DimensionType.THE_NETHER_REGISTRY_KEY.getValue(), new GoblinTraderSpawner(server, "VeinGoblinTrader", ModEntities.VEIN_GOBLIN_TRADER, Objects.requireNonNull(ModEntities.GOBLIN_TRADER.create(world))));
            loaded = true;
        }
    }
    public static class ServerStopped implements ServerLifecycleEvents.ServerStopped {
        public ServerStopped(){}
        @Override
        public void onServerStopped(MinecraftServer server)
        {
            spawners.clear();
        }
    }

    public static class OnWorldTick implements ServerTickEvents.EndWorldTick {

        @Override
        public void onEndTick(ServerWorld world) {
            if (world.isClient()) {
                return;
            }
            ArrayList<GoblinTraderSpawner> spawnerList = spawners.get(world.getRegistryKey().getValue());
            if (spawnerList != null) {
                for (var spawner : spawnerList) {
                    if (spawner != null) {
                        spawner.tick(world);
                        System.out.println("TICK");
                    }
                }
                System.out.println("NOT-NULL");
            } else System.out.println("NULL");
        }
    }

    /* Since i can do this, i am gonna do this */
    public static Map<Identifier, ArrayList<GoblinTraderSpawner>> getSpawners() {
        return spawners;
    }
    public static void addToSpawners(Identifier world, GoblinTraderSpawner spawner) {
        if (!spawners.containsKey(world)) {
            spawners.put(world, new ArrayList<>());
        }
        spawners.get(world).add(spawner);
        System.out.println(Arrays.toString(spawners.get(world).toArray()));
    }
}
