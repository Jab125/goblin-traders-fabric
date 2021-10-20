package net.hat.gt.trades;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;

public class GoblinTrades {
    /* ************************************************************************************** *
     *                                     COMMON                                             *
     * ************************************************************************************** */
    public static TradeOffers.Factory[] goblinCommonTrades() {
        return new TradeOffers.Factory[]{
                new TradeWithoutEmerald(Items.APPLE, 1, Items.EMERALD, 1, 24, 10),
                new TradeWithoutEmerald(Items.DEBUG_STICK, 1, Items.ACACIA_BOAT, 1, 1, Integer.MAX_VALUE),
                new TradeWithoutEmerald(Items.RAW_IRON, 1, Items.IRON_INGOT, 2, 30, 20),
                new TradeWithoutEmerald(Items.RAW_GOLD, 2, Items.GOLD_INGOT, 3, 30, 30),
                new TradeWithoutEmerald(Items.ROTTEN_FLESH, 4, Items.COAL, 1, 24, 15),
                new TradeWithoutEmerald(Items.GRAVEL, 1, Items.FLINT, 2, 32, 15),
                new TradeWithoutEmerald(Items.COBBLESTONE, 32, Items.EMERALD, 1, 128, 15),
                new TradeWithoutEmerald(Items.EMERALD, 1, Items.BREAD, 3, 15),
                /* ************************************************************************************** *
                 *                                     UNCOMMON                                           *
                 * ************************************************************************************** */
        };
    }
    public static TradeOffers.Factory[] goblinUncommonTrades() {
        return new TradeOffers.Factory[]{
                new TradeWithoutEmerald(Items.EMERALD, 1, Items.GUNPOWDER, 2, 32, 20),
                new TradeWithoutEmerald(Items.TURTLE_EGG, 1, Items.EMERALD, 6, 2, 50),
                new TradeWithoutEmerald(Items.PUFFERFISH_BUCKET, 1, Items.EMERALD, 8, 4, 50),
                new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                new TradeWithoutEmerald(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 4, Items.CHIPPED_ANVIL, 1, 32, 20),
                new TradeWithoutEmerald(Items.CLAY_BALL, 1, Items.TERRACOTTA, 2, 64, 20),
                new TradeWithoutEmerald(Items.LEATHER, 1, Items.PAPER, 3, Items.BOOK, 2, 32, 20),
                new TradeWithoutEmerald(Items.EMERALD, 2, Items.HAY_BLOCK, 1, 15),
                /* ************************************************************************************** *
                 *                                      RARE                                              *
                 * ************************************************************************************** */
        };
    }
    public static TradeOffers.Factory[] goblinRareTrades() {
        return new TradeOffers.Factory[]{
                new TradeWithoutEmerald(Items.EMERALD, 3, Items.EXPERIENCE_BOTTLE, 1, 64, 40),
                new TradeWithoutEmerald(Items.EMERALD, 24, Items.PAPER, 8, Items.NAME_TAG, 1, 2, 40),
                new TradeWithoutEmerald(Items.PACKED_ICE, 4, Items.BLUE_ICE, 1, 64, 40),
                new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_CAT, 1, 50),
                new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_BLOCKS, 1, 50),
                new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_CHIRP, 1, 50),
                new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_MELLOHI, 1, 50),
                new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_STAL, 1, 50),
                new SellEnchantedItem(Items.FISHING_ROD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.LUCK_OF_THE_SEA, 3, Items.FISHING_ROD, Enchantments.LUCK_OF_THE_SEA, 5, 1, 200),
                new SellEnchantedItem(Items.FISHING_ROD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.LURE, 3, Items.FISHING_ROD, Enchantments.LURE, 5, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_PICKAXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.EFFICIENCY, 5, Items.DIAMOND_PICKAXE, Enchantments.EFFICIENCY, 6, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_PICKAXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_PICKAXE, Enchantments.UNBREAKING, 4, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_PICKAXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FORTUNE, 3, Items.DIAMOND_PICKAXE, Enchantments.FORTUNE, 4, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_AXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_AXE, Enchantments.UNBREAKING, 4, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_AXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.EFFICIENCY, 5, Items.DIAMOND_AXE, Enchantments.EFFICIENCY, 6, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_SHOVEL, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_SHOVEL, Enchantments.UNBREAKING, 4, 1, 200),
                new SellEnchantedItem(Items.DIAMOND_SHOVEL, 1, Items.ENCHANTED_BOOK, 1, Enchantments.EFFICIENCY, 5, Items.DIAMOND_SHOVEL, Enchantments.EFFICIENCY, 6, 1, 200),
                /* ************************************************************************************** *
                 *                                    LEGENDARY                                           *
                 * ************************************************************************************** */

        };
    }
    public static TradeOffers.Factory[] legendaryGoblinTrades() {
        return new TradeOffers.Factory[]{
                new SellEnchantedItem(Items.DRAGON_HEAD, 5, Items.DIAMOND_PICKAXE, 1, null, 0, Items.DIAMOND_PICKAXE, Enchantments.FORTUNE, 5, Enchantments.UNBREAKING, 4, 1, 500)
        };
    }
}
