package net.hat.gt.init;


import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Special thanks to Globox1997 for his spawn code in the Nether, it's fantastic!
public class ModSpawns {
    public static void init() {
        for (Biome biome : BuiltinRegistries.BIOME) {
            addSpawnEntries(biome);
        }
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> ModSpawns.addSpawnEntries(biome));
    }
    private static void addSpawnEntries(Biome biome) {
        if (biome.getCategory().equals(Biome.Category.NETHER)) {
            addMobSpawnToBiome(biome, SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntities.VEIN_GOBLIN_TRADER, 1, 1, 1));
        }
    }
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
