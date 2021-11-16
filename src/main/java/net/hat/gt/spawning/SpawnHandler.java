package net.hat.gt.spawning;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.hat.gt.entities.GoblinTraderEntity;
import net.hat.gt.init.ModEntities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: MrCrayfish
 */
public class SpawnHandler {
    private static Map<Identifier, GoblinTraderSpawner> spawners = new HashMap<>();
    public SpawnHandler(){}

    public static class WorldLoad implements ServerWorldEvents.Load {
        public WorldLoad(){}

        @Override
        public void onWorldLoad(MinecraftServer server, ServerWorld world) {
            System.out.println("WORLD LOAD");
            spawners.put(DimensionType.OVERWORLD_REGISTRY_KEY.getValue(), new GoblinTraderSpawner(server, "GoblinTrader", ModEntities.GOBLIN_TRADER, Objects.requireNonNull(ModEntities.GOBLIN_TRADER.create(world))));
            spawners.put(DimensionType.THE_NETHER_REGISTRY_KEY.getValue(), new GoblinTraderSpawner(server, "VeinGoblinTrader", ModEntities.VEIN_GOBLIN_TRADER, Objects.requireNonNull(ModEntities.GOBLIN_TRADER.create(world))));
        }
    }
    public static class ServerStopped implements ServerLifecycleEvents.ServerStopped {
        public ServerStopped(){}
        @Override
        public void onServerStopped(MinecraftServer server)
        {
            System.out.println("SERVER STARTED");
            spawners.clear();
        }
    }

    public static class OnWorldTick implements ServerTickEvents.EndWorldTick {

        @Override
        public void onEndTick(ServerWorld world) {
            if (world.isClient()) {
                System.out.println("CLIENT END TICK");
                return;
            }
            System.out.println("SERVER END TICK");
            GoblinTraderSpawner spawner = spawners.get(world.getRegistryKey().getValue());
            if(spawner != null)
            {
                spawner.tick(world);
            } else {
                System.out.println("ITS NULL");
            }
        }
    }
}
