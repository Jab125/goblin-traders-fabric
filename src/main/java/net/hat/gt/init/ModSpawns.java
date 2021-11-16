package net.hat.gt.init;


import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.hat.gt.GobT;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@ApiStatus.ScheduledForRemoval(inVersion = "1.5.0")
@Deprecated(forRemoval = true, since = "1.4.0")
// Special thanks to Globox1997 for his spawn code in the Nether, it's fantastic!
public class ModSpawns {
    public static void init() {
        for (Biome biome : BuiltinRegistries.BIOME) {
            addSpawnEntries(biome);
        }
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> ModSpawns.addSpawnEntries(biome));
    }
    private static void addSpawnEntries(Biome biome) {
        if (biome.getCategory().equals(Biome.Category.NETHER) && GobT.config.VEIN_GOBLINS_CAN_SPAWN) {
            addMobSpawnToBiome(biome, SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.VEIN_GOBLIN_TRADER, GobT.config.VEIN_GOBLIN_SPAWN_RATE, 1, GobT.config.VEIN_GOBLIN_GROUP_SIZE));
        }
               if (!biome.getCategory().equals(Biome.Category.NETHER)
                && !biome.getCategory().equals(Biome.Category.THEEND)
                && !biome.getCategory().equals(Biome.Category.MESA)
                && !biome.getCategory().equals(Biome.Category.SAVANNA)
                && !biome.getCategory().equals(Biome.Category.DESERT)
                       && GobT.config.GOBLINS_CAN_SPAWN
        ) {
            addMobSpawnToBiome(biome, SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.GOBLIN_TRADER, GobT.config.GOBLIN_SPAWN_RATE, 1, GobT.config.GOBLIN_GROUP_SIZE));
        }
    }

    // This can be changed to be just a Creature type, however for further support in the future i've kept it as 'classification' for now.
    private static void addMobSpawnToBiome(Biome biome, SpawnGroup classification, SpawnSettings.SpawnEntry... spawners) {
        convertImmutableSpawners(biome);
        List<SpawnSettings.SpawnEntry> spawnersList = new ArrayList<>(biome.getSpawnSettings().spawners.get(classification).getEntries());
        spawnersList.addAll(Arrays.asList(spawners));
        biome.getSpawnSettings().spawners.put(classification, Pool.of(spawnersList));
    }

    private static void convertImmutableSpawners(Biome biome) {
        if (biome.getSpawnSettings().spawners instanceof ImmutableMap) {
            biome.getSpawnSettings().spawners = new HashMap<>(biome.getSpawnSettings().spawners);
        }
    }
}
