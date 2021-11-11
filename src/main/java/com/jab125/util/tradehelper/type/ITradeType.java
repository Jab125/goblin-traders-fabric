package com.jab125.util.tradehelper.type;

import com.google.gson.JsonObject;
import net.minecraft.village.TradeOffers;

/**
 * Remapped by Jab125
 */
public interface ITradeType<T extends TradeOffers.Factory>
{
    JsonObject serialize();

    T createTradeOffer();
}
