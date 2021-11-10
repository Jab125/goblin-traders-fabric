package com.jab125.util.type;

import com.google.gson.JsonObject;
import net.minecraft.village.TradeOffers;

/**
 * Author: MrCrayfish
 */
public interface ITradeType<T extends TradeOffers.Factory>
{
    JsonObject serialize();

    T createTradeOffer();
}
