package com.jab125.util.datagen;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.jab125.util.tradehelper.TradeRarities;
import com.jab125.util.tradehelper.type.ITradeType;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.hat.gt.GobT;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Reworked for fabric by Jab125
 */
public abstract class TradeProvider implements DataProvider
{
    private static final Logger LOGGER = GobT.LOGGER;
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    private final DataGenerator generator;
    private Map<EntityType<?>, EnumMap<TradeRarities, List<ITradeType<?>>>> trades = new HashMap<>();

    protected TradeProvider(DataGenerator generator)
    {
        this.generator = generator;
    }

    protected abstract void registerTrades();

    @ApiStatus.ScheduledForRemoval
    @Deprecated
    protected void registerVanillaTrades() {};

    /**
     * @deprecated use {@link TradeProvider#addTrade(EntityType, TradeRarities, ITradeType)} instead.
     */
    // Use an access-widener to access this
    @ApiStatus.ScheduledForRemoval
    @Deprecated
    protected final void addTrade(EntityType<?> type, TradeRarities rarity, boolean isVanilla, ITradeType<?> trade)
    {
        if (isVanilla) return;
        this.addTrade(type, rarity, trade);
    }

    protected final void addTrade(EntityType<?> type, TradeRarities rarity, ITradeType<?> trade)
    {
        this.trades.putIfAbsent(type, new EnumMap<>(TradeRarities.class));
        this.trades.get(type).putIfAbsent(rarity, new ArrayList<>());
        this.trades.get(type).get(rarity).add(trade);
    }


    @Override
    public void run(DataWriter writer)
    {
        System.out.println("STARING");
        try {
            this.trades.clear();
            this.registerTrades();
            this.trades.forEach((entityType, tradeRarityListEnumMap) ->
            {
                tradeRarityListEnumMap.forEach((tradeRarity, tradeList) ->
                {
                    JsonObject object = new JsonObject();
                    object.addProperty("replace", false);
                    JsonArray tradeArray = new JsonArray();
                    tradeList.forEach(trade -> tradeArray.add(trade.serialize()));
                    object.add("trades", tradeArray);
                    Identifier id = Objects.requireNonNull(Registry.ENTITY_TYPE.getId(entityType));
                    Path path = this.generator.getOutput().resolve("data/" + id.getNamespace() + "/trades/" + id.getPath() + "/" + tradeRarity.getKey() + ".json");
                    try {
                        writeToPath(writer, object, path);
                    } catch (IOException e) {
                        LOGGER.error("Couldn't save trades to {}", path, e);
                    }
                });
            });
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    static void writeToPath(DataWriter writer, JsonElement json, Path path) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HashingOutputStream hashingOutputStream = new HashingOutputStream(Hashing.sha1(), byteArrayOutputStream);
        Writer writer2 = new OutputStreamWriter(hashingOutputStream, StandardCharsets.UTF_8);
        JsonWriter jsonWriter = new JsonWriter(writer2);
        jsonWriter.setSerializeNulls(false);
        jsonWriter.setIndent("  ");
        JsonHelper.writeSorted(jsonWriter, json, null);
        jsonWriter.close();
        writer.write(path, byteArrayOutputStream.toByteArray(), hashingOutputStream.hash());
    }

    @Override
    public String getName()
    {
        return "Trades: " + GobT.MODID;
    }
}
