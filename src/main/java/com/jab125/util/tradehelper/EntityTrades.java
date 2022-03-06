package com.jab125.util.tradehelper;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Util;
import net.minecraft.village.TradeOffers;

import java.util.*;

/**
 * Remapped by Jab125
 */
public class EntityTrades {
    private final Map<TradeRarities, List<TradeOffers.Factory>> tradeMap;

    public EntityTrades(Map<TradeRarities, List<TradeOffers.Factory>> tradeMap) {
        this.tradeMap = ImmutableMap.copyOf(tradeMap);
    }

    public Map<TradeRarities, List<TradeOffers.Factory>> getTradeMap() {
        return this.tradeMap;
    }

    public static class Builder {
        private final Map<TradeRarities, List<TradeOffers.Factory>> tradeMap = Util.make(() ->
        {
            Map<TradeRarities, List<TradeOffers.Factory>> map = new EnumMap<>(TradeRarities.class);
            Arrays.stream(TradeRarities.values()).forEach(rarity -> map.put(rarity, new ArrayList<>()));
            return map;
        });

        private Builder() {
        }

        static Builder create() {
            return new Builder();
        }

        public EntityTrades build() {
            return new EntityTrades(this.tradeMap);
        }

        void deserialize(TradeRarities rarity, JsonObject object) {
            List<TradeOffers.Factory> trades = this.tradeMap.get(rarity);

            if (JsonHelper.getBoolean(object, "replace", false)) {
                trades.clear();
            }

            JsonArray tradeArray = JsonHelper.getArray(object, "trades");
            for (JsonElement tradeElement : tradeArray) {
                JsonObject tradeObject = tradeElement.getAsJsonObject();
                boolean required = !JsonHelper.hasBoolean(tradeObject, "required") || JsonHelper.getBoolean(tradeObject, "required");
                try {
                    String rawType = JsonHelper.getString(tradeObject, "type");
                    Identifier typeKey = Identifier.tryParse(rawType);
                    if (typeKey == null) {
                        throw new JsonParseException("");
                    }
                    TradeSerializer<?> serializer = TradeManager.instance().getTypeSerializer(typeKey);
                    if (serializer == null) {
                        throw new JsonParseException(String.format("Invalid trade type: %s", typeKey));
                    }
                    trades.add(serializer.deserialize(tradeObject).createTradeOffer());
                } catch (Exception error) {
                    if (required) throw error;
                }
            }
        }
    }
}
