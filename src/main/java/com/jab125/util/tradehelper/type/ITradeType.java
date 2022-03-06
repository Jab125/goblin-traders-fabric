package com.jab125.util.tradehelper.type;

import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.village.TradeOffers;

/**
 * Remapped by Jab125
 */
public interface ITradeType<T extends TradeOffers.Factory>
{
    JsonObject serialize();

    T createTradeOffer();

    default boolean isRequired() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) throw new RuntimeException(this.getClass().toString() + "should override isRequired()!");
        return true;
    }
}
