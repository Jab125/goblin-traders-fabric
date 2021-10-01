package net.hat.gt.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;

public class ModTrades {
    public static Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES;

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> trades) {
        return new Int2ObjectOpenHashMap<>(trades);
    }

    static {
        GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{
                new TradeOffers.ProcessItemFactory(Items.EMERALD, 0, 1, Items.APPLE, 1, 24, 10)},
                2, new TradeOffers.Factory[]{
                        new TradeOffers.ProcessItemFactory(Items.EMERALD, 1, 1, Items.APPLE, 1, 24, 10)
                }));
    }
}

