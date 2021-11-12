package net.hat.gt.OLD;

//import com.google.common.collect.ImmutableMap;
//import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
//import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
//import net.hat.gt.OLD.GoblinTrades;
//import net.minecraft.village.TradeOffers;

public class ModTrades {
//    public static Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES;
//    public static Int2ObjectMap<TradeOffers.Factory[]> VEIN_GOBLIN_TRADER_TRADES;
//    public static Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES_VANILLA;
//    public static Int2ObjectMap<TradeOffers.Factory[]> VEIN_GOBLIN_TRADER_TRADES_VANILLA;
//
//    public static Int2ObjectMap<TradeOffers.Factory[]> EASTER_EGG_TRADES;
//
//    public static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> trades) {
//        return new Int2ObjectOpenHashMap<>(trades);
//    }
//
//    /**
//     * Right so if you're reading through the code and are planning on making an addon or something to add custom trades -
//     * at least before I do... this is where the trades are done. They are easily accessable here, and you can put your own
//     * in via a mixin into this. I've made my own ItemFactory called TradeWithoutEmerald, this is due to default
//     * tradeFactories all using emeralds, so I needed my own.
//     *
//     * The default format is:PaymentItem, Price, SellItem, SellCount, MaxUses, ExperienceGained
//     *
//     * There is also a second format if you wish to add a second Item -
//     * The format is: PaymentItem, Price, SecondItem, SecondPrice, SellItem, SellCount, MaxUses, ExperienceGained
//     *
//     * I've also went ahead and made everything public in here, so feel free to access it without an accesswidener :)
//     */
//    static {
//
//        //copy all trades
//        GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
//                1, GoblinTrades.goblinCommonTrades(), 2, GoblinTrades.goblinUncommonTrades(), 3, GoblinTrades.goblinRareTrades(), 4, GoblinTrades.legendaryGoblinTrades()));
//        VEIN_GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
//                1, GoblinTrades.commonVeinGoblinTrades(), 2, GoblinTrades.uncommonVeinGoblinTrades(), 3, GoblinTrades.rareVeinGoblinTrades(), 4, GoblinTrades.epicVeinGoblinTrades(), 5, GoblinTrades.legendaryVeinGoblinTrades()));
//        /**
//         * Vanilla Trades
//         */
//        GOBLIN_TRADER_TRADES_VANILLA = copyToFastUtilMap(ImmutableMap.of(
//                1, GoblinTrades.vanillaCommonGoblinTrades(), 2, GoblinTrades.vanillaUncommonGoblinTrades(), 3, GoblinTrades.vanillaRareGoblinTrades(), 4, GoblinTrades.vanillaLegendaryGoblinTrades()));
//
//        VEIN_GOBLIN_TRADER_TRADES_VANILLA = copyToFastUtilMap(ImmutableMap.of(
//                1, GoblinTrades.vanillaCommonVeinGoblinTrades(), 2, GoblinTrades.vanillaUncommonVeinGoblinTrades(), 3, GoblinTrades.vanillaRareVeinGoblinTrades(), 4, GoblinTrades.vanillaEpicVeinGoblinTrades(), 5, GoblinTrades.vanillaLegendaryVeinGoblinTrades()));
//
//        EASTER_EGG_TRADES = copyToFastUtilMap(ImmutableMap.of(1, GoblinTrades.easterEggTrades()));
//    }
}



