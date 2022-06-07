package com.jab125.util.tradehelper;

import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffers;

import java.util.List;
import java.util.function.BiFunction;

public interface TradeRarity {
    String getKey();

    BiFunction<List<TradeOffers.Factory>, Random, Integer> getMinimum();

    BiFunction<List<TradeOffers.Factory>, Random, Integer> getMaximum();

    boolean shouldShuffle();
}
