package com.jab125.util.tradehelper;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jab125.limeappleboat.gobt.api.GobTEvents;
import io.netty.util.internal.UnstableApi;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.hat.gt.GobT;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Remapped by Jab125
 */
@UnstableApi
public class TradeManager implements IdentifiableResourceReloadListener {
    private static final int FILE_TYPE_LENGTH_VALUE = ".json".length();
    private static final Gson GSON = new GsonBuilder().create();
    public static TradeManager instance;
    private List<EntityType<?>> traders = new ArrayList<>();
    private Map<EntityType<?>, EntityTrades> tradeMap = new HashMap<>();

    private Map<Identifier, TradeSerializer<?>> tradeSerializer = new HashMap<>();
    /**
     * @return The unique identifier of this listener.
     */


    public static TradeManager instance()
    {
        if(instance == null)
        {
            instance = new TradeManager();
        }
        return instance;
    }

    public void registerTrader(EntityType<? extends AbstractGoblinEntity> type)
    {
        if(!this.traders.contains(type))
        {
            this.traders.add(type);
        }
    }
    @Nullable
    public EntityTrades getTrades(EntityType<? extends AbstractGoblinEntity> type)
    {
        return this.tradeMap.get(type);
    }

    public void registerTypeSerializer(TradeSerializer<?> serializer)
    {
        this.tradeSerializer.putIfAbsent(serializer.getId(), serializer);
    }

    @Nullable
    public TradeSerializer<?> getTypeSerializer(Identifier id)
    {
        return this.tradeSerializer.get(id);
    }


    @Override 
    public CompletableFuture<Void> reload(Synchronizer stage, ResourceManager manager, Profiler preparationsProfiler, Profiler reloadProfiler, Executor backgroundExecutor, Executor gameExecutor)
    {
        Map<EntityType<?>, EntityTrades> entityToResourceList = new HashMap<>();
        return CompletableFuture.allOf(CompletableFuture.runAsync(() ->
        {
            this.traders.forEach(entityType ->
            {
                String folder = String.format("trades/%s", Objects.requireNonNull(EntityType.getId(entityType)).getPath());
                List<Identifier> resources = new ArrayList<>(manager.findResources(folder, (fileName) -> fileName.endsWith(".json")));
                resources.forEach((identifier) -> System.out.println(identifier.toString()));
                resources.sort((r1, r2) -> {
                    if(r1.getNamespace().equals(r2.getNamespace())) return 0;
                    return r2.getNamespace().equals(GobT.MODID) ? 1 : -1;
                });
                Map<TradeRarities, LinkedHashSet<Identifier>> tradeResources = new EnumMap<>(TradeRarities.class);
                Arrays.stream(TradeRarities.values()).forEach(rarity -> tradeResources.put(rarity, new LinkedHashSet<>()));
                resources.forEach(resource ->
                {
                    String path = resource.getPath().substring(0, resource.getPath().length() - FILE_TYPE_LENGTH_VALUE);
                    String[] splitPath = path.split("/");
                    if(splitPath.length != 3)
                        return;
                    Arrays.stream(TradeRarities.values()).forEach(rarity ->
                    {
                        if(rarity.getKey().equals(splitPath[2]))
                        {
                            tradeResources.get(rarity).add(resource);
                        }
                    });
                });
                EntityTrades.Builder builder = EntityTrades.Builder.create();
                Arrays.stream(TradeRarities.values()).forEach(rarity -> {
                    ActionResult result = GobTEvents.TRADE_LOADED.invoker().interact(null, rarity);
                    if (result == ActionResult.FAIL) {
                        return;
                    }
                    this.deserializeTrades(manager, builder, rarity, tradeResources.get(rarity));
                });
                entityToResourceList.put(entityType, builder.build());
                this.tradeMap = ImmutableMap.copyOf(entityToResourceList);
            });
        }, backgroundExecutor)).thenCompose(stage::whenPrepared);
    }


    private void deserializeTrades(ResourceManager manager, EntityTrades.Builder builder, TradeRarities rarity, LinkedHashSet<Identifier> resources)
    {
        for(Identifier resource : resources)
        {
            try(InputStream inputstream = manager.getResource(resource).getInputStream(); Reader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8)))
            {
                JsonObject object = JsonHelper.deserialize(GSON, reader, JsonObject.class);
                builder.deserialize(rarity, object);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return The unique identifier of this listener.
     */
    @Override
    public Identifier getFabricId() {
        return GobT.id("reload_trades");
    }
}
