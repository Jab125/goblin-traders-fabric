package com.jab125.util.tradehelper;

import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffers;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Remapped by Jab125
 */
public enum TradeRarities implements TradeRarity
{
    COMMON("common", (trades, random) -> trades.size(), (trades, random) -> trades.size(), true),
    UNCOMMON("uncommon", (trades, random) -> 3, (trades, random) -> random.nextInt(5) + 1, true),
    RARE("rare", (trades, random) -> 1, (trades, random) -> random.nextInt(3) + 1, true),
    EPIC("epic", (trades, random) -> 0, (trades, random) -> random.nextInt(2), true),
    LEGENDARY("legendary", (trades, random) -> 0, (trades, random) -> random.nextInt(25) == 0 ? 1 : 0, true);

    private final String key;
    private final BiFunction<List<TradeOffers.Factory>, Random, Integer> minimum;
    private final BiFunction<List<TradeOffers.Factory>, Random, Integer> maximum;
    private final boolean shuffle;

    TradeRarities(String key, BiFunction<List<TradeOffers.Factory>, Random, Integer> minimum, BiFunction<List<TradeOffers.Factory>, Random, Integer> maximum, boolean shuffle)
    {
        this.key = key;
        this.minimum = minimum;
        this.maximum = maximum;
        this.shuffle = shuffle;
    }

    @Override
    public String getKey()
    {
        return this.key;
    }

    @Override
    public BiFunction<List<TradeOffers.Factory>, Random, Integer> getMinimum()
    {
        return this.minimum;
    }

    @Override
    public BiFunction<List<TradeOffers.Factory>, Random, Integer> getMaximum()
    {
        return this.maximum;
    }

    @Override
    public boolean shouldShuffle()
    {
        return this.shuffle;
    }}
