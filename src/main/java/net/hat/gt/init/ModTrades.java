package net.hat.gt.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.hat.gt.GobT;
import net.hat.gt.trades.SellEnchantedItem;
import net.hat.gt.trades.TradeWithoutEmerald;
import net.hat.gt.trades.TradeWithoutEmeraldPotions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ModTrades {
    public static Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES;
    public static Int2ObjectMap<TradeOffers.Factory[]> VEIN_GOBLIN_TRADER_TRADES;
    public static Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES_VANILLA;
    public static Int2ObjectMap<TradeOffers.Factory[]> VEIN_GOBLIN_TRADER_TRADES_VANILLA;

    public static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> trades) {
        return new Int2ObjectOpenHashMap<>(trades);
    }

    static {
        //copy all trades
        if (GobT.config.EASTER_EGGS) {
            GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
                    /**
                     * Right so if you're reading through the code and are planning on making an addon or something to add custom trades -
                     * at least before I do... this is where the trades are done. They are easily accessable here, and you can put your own
                     * in via a mixin into this. I've made my own ItemFactory called TradeWithoutEmerald, this is due to default
                     * tradeFactories all using emeralds, so I needed my own.
                     *
                     * The default format is:PaymentItem, Price, SellItem, SellCount, MaxUses, ExperienceGained
                     *
                     * There is also a second format if you wish to add a second Item -
                     * The format is: PaymentItem, Price, SecondItem, SecondPrice, SellItem, SellCount, MaxUses, ExperienceGained
                     *
                     * I've also went ahead and made everything public in here, so feel free to access it without an accesswidener :)
                     */
                    /* ************************************************************************************** *
                     *                                     COMMON                                             *
                     * ************************************************************************************** */
                    1, new TradeOffers.Factory[]{
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
                    }, 2, new TradeOffers.Factory[]{
                            new TradeWithoutEmerald(Items.EMERALD, 1, Items.GUNPOWDER, 2, 32, 20),
                            new TradeWithoutEmerald(Items.TURTLE_EGG, 1, Items.EMERALD, 6, 2, 50),
                            new TradeWithoutEmerald(Items.PUFFERFISH_BUCKET, 1, Items.EMERALD, 8, 4, 50),
                            new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                            new TradeWithoutEmerald(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 4 ,Items.CHIPPED_ANVIL, 1, 32, 20),
                            new TradeWithoutEmerald(Items.CLAY_BALL, 1, Items.TERRACOTTA, 2, 64, 20),
                            new TradeWithoutEmerald(Items.LEATHER, 1, Items.PAPER, 3, Items.BOOK, 2, 32, 20),
                            new TradeWithoutEmerald(Items.EMERALD, 2, Items.HAY_BLOCK, 1, 15),
                            /* ************************************************************************************** *
                             *                                      RARE                                              *
                             * ************************************************************************************** */
                    }, 3, new TradeOffers.Factory[]{
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

                    }, 4, new TradeOffers.Factory[]{
                            new SellEnchantedItem(Items.DRAGON_HEAD, 5, Items.DIAMOND_PICKAXE, 1, null, 0, Items.DIAMOND_PICKAXE, Enchantments.FORTUNE, 5, Enchantments.UNBREAKING, 4, 1, 500)
                    }
            ));
        } else {
            GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
                    /* ************************************************************************************** *
                     *                                     COMMON                                             *
                     * ************************************************************************************** */
                    1, new TradeOffers.Factory[]{
                            new TradeWithoutEmerald(Items.APPLE, 1, Items.EMERALD, 1, 24, 10),
                            new TradeWithoutEmerald(Items.RAW_IRON, 1, Items.IRON_INGOT, 2, 30, 20),
                            new TradeWithoutEmerald(Items.RAW_GOLD, 2, Items.GOLD_INGOT, 3, 30, 30),
                            new TradeWithoutEmerald(Items.ROTTEN_FLESH, 4, Items.COAL, 1, 24, 15),
                            new TradeWithoutEmerald(Items.GRAVEL, 1, Items.FLINT, 2, 32, 15),
                            new TradeWithoutEmerald(Items.COBBLESTONE, 32, Items.EMERALD, 1, 128, 15),
                            new TradeWithoutEmerald(Items.EMERALD, 1, Items.BREAD, 3, 15),
                            /* ************************************************************************************** *
                             *                                     UNCOMMON                                           *
                             * ************************************************************************************** */
                    }, 2, new TradeOffers.Factory[]{
                            new TradeWithoutEmerald(Items.EMERALD, 1, Items.GUNPOWDER, 2, 32, 20),
                            new TradeWithoutEmerald(Items.TURTLE_EGG, 1, Items.EMERALD, 6, 2, 50),
                            new TradeWithoutEmerald(Items.PUFFERFISH_BUCKET, 1, Items.EMERALD, 8, 4, 50),
                            new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                            new TradeWithoutEmerald(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 4 ,Items.CHIPPED_ANVIL, 1, 32, 20),
                            new TradeWithoutEmerald(Items.CLAY_BALL, 1, Items.TERRACOTTA, 2, 64, 20),
                            new TradeWithoutEmerald(Items.LEATHER, 1, Items.PAPER, 3, Items.BOOK, 2, 32, 20),
                            new TradeWithoutEmerald(Items.EMERALD, 2, Items.HAY_BLOCK, 1, 15),
                            /* ************************************************************************************** *
                             *                                      RARE                                              *
                             * ************************************************************************************** */
                    }, 3, new TradeOffers.Factory[]{
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

                    }, 4, new TradeOffers.Factory[]{
                            new SellEnchantedItem(Items.DRAGON_HEAD, 5, Items.DIAMOND_PICKAXE, 1, null, 0, Items.DIAMOND_PICKAXE, Enchantments.FORTUNE, 5, Enchantments.UNBREAKING, 4, 1, 500),
                    }
            ));
        }
        VEIN_GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
                /* ************************************************************************************** *
                 *                                     COMMON                                             *
                 * ************************************************************************************** */
                1, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.CARROT, 1, Items.EMERALD, 1, 24, 10),
                        new TradeWithoutEmerald(Items.GLOWSTONE, 1, Items.GLOWSTONE_DUST, 4, 32, 20),
                        new TradeWithoutEmerald(Items.NETHERRACK, 64, Items.EMERALD, 1, 64, 30),
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.NETHER_WART, 2, 15),
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.WARPED_FUNGUS, 5, 15),
                        new TradeWithoutEmerald(Items.EMERALD, 6, Items.GOLDEN_APPLE, 1, 15),
                        /* ************************************************************************************** *
                         *                                     UNCOMMON                                           *
                         * ************************************************************************************** */
                }, 2, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.NETHERITE_INGOT, 1, Items.LODESTONE, 2, 20),
                        new TradeWithoutEmerald(Items.ANCIENT_DEBRIS, 4, Items.NETHERITE_SCRAP, 5, 2, 50),
                        new TradeWithoutEmerald(Items.NETHER_WART, 1, Items.RED_NETHER_BRICKS, 1, 128, 20),
                        new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                        /* ************************************************************************************** *
                         *                                      RARE                                              *
                         * ************************************************************************************** */
                }, 3, new TradeOffers.Factory[]{

                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.SHARPNESS, 5, Items.DIAMOND_SWORD, Enchantments.SHARPNESS, 6, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BANE_OF_ARTHROPODS, 5, Items.DIAMOND_SWORD, Enchantments.BANE_OF_ARTHROPODS, 6, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_ASPECT, 2, Items.DIAMOND_SWORD, Enchantments.FIRE_ASPECT, 3, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.LOOTING, 3, Items.DIAMOND_SWORD, Enchantments.LOOTING, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.SWEEPING, 3, Items.DIAMOND_SWORD, Enchantments.SWEEPING, 4, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 4, Items.DIAMOND_HELMET, Enchantments.BLAST_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 4, Items.DIAMOND_HELMET, Enchantments.FIRE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 4, Items.DIAMOND_HELMET, Enchantments.PROJECTILE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 4, Items.DIAMOND_HELMET, Enchantments.PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_HELMET, Enchantments.UNBREAKING, 4, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 4, Items.DIAMOND_CHESTPLATE, Enchantments.BLAST_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 4, Items.DIAMOND_CHESTPLATE, Enchantments.FIRE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 4, Items.DIAMOND_CHESTPLATE, Enchantments.PROJECTILE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 4, Items.DIAMOND_CHESTPLATE, Enchantments.PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_CHESTPLATE, Enchantments.UNBREAKING, 4, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 4, Items.DIAMOND_LEGGINGS, Enchantments.BLAST_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 4, Items.DIAMOND_LEGGINGS, Enchantments.FIRE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 4, Items.DIAMOND_LEGGINGS, Enchantments.PROJECTILE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 4, Items.DIAMOND_LEGGINGS, Enchantments.PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_LEGGINGS, Enchantments.UNBREAKING, 4, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 4, Items.DIAMOND_BOOTS, Enchantments.BLAST_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 4, Items.DIAMOND_BOOTS, Enchantments.FIRE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 4, Items.DIAMOND_BOOTS, Enchantments.PROJECTILE_PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 4, Items.DIAMOND_BOOTS, Enchantments.PROTECTION, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 3, Items.DIAMOND_BOOTS, Enchantments.UNBREAKING, 4, 1, 100),

                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.EXTENDED_FIRE_RESISTANCE, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.EXTENDED_SLOW_FALLING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.EXTENDED_NIGHT_VISION, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.EXTENDED_WATER_BREATHING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.EXTENDED_INVISIBILITY, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.POWERFUL_REGENERATION, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.POWERFUL_SPEED, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.POWERFUL_JUMP_BOOST, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.POWERFUL_INSTANT_HEALTH, 8, 200),
                        /* ************************************************************************************** *
                         *                                         EPIC                                           *
                         * ************************************************************************************** */

                }, 4, new TradeOffers.Factory[]{
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.HASTE, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.ABSORPTION, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.LUCK, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.DOLPHINS_GRACE, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.LEVITATION, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.POWERFUL_STRENGTH, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, ModPotions.BLINDNESS, 8, 200),

                        /* ************************************************************************************** *
                         *                                    LEGENDARY                                           *
                         * ************************************************************************************** */
                }, 5, new TradeOffers.Factory[]{
                        new SellEnchantedItem(Items.DRAGON_HEAD, 5, Items.DIAMOND_SWORD, 1, null, 0, Items.DIAMOND_SWORD, Enchantments.SHARPNESS, 7, Enchantments.UNBREAKING, 4, 1, 500),
                        // Two and a half-hearts of dmg to a player not wearing armour
                }
        ));
        /**
         * Vanilla Trades
         */
        GOBLIN_TRADER_TRADES_VANILLA = copyToFastUtilMap(ImmutableMap.of(
                /* ************************************************************************************** *
                 *                                     COMMON                                             *
                 * ************************************************************************************** */
                1, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.APPLE, 1, Items.EMERALD, 1, 24, 10),
                        new TradeWithoutEmerald(Items.RAW_IRON, 1, Items.IRON_INGOT, 2, 30, 20),
                        new TradeWithoutEmerald(Items.RAW_GOLD, 2, Items.GOLD_INGOT, 3, 30, 30),
                        new TradeWithoutEmerald(Items.ROTTEN_FLESH, 4, Items.COAL, 1, 24, 15),
                        new TradeWithoutEmerald(Items.GRAVEL, 1, Items.FLINT, 2, 32, 15),
                        new TradeWithoutEmerald(Items.COBBLESTONE, 32, Items.EMERALD, 1, 128, 15),
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.BREAD, 3, 15),
                        /* ************************************************************************************** *
                         *                                     UNCOMMON                                           *
                         * ************************************************************************************** */
                }, 2, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.GUNPOWDER, 2, 32, 20),
                        new TradeWithoutEmerald(Items.TURTLE_EGG, 1, Items.EMERALD, 6, 2, 50),
                        new TradeWithoutEmerald(Items.PUFFERFISH_BUCKET, 1, Items.EMERALD, 8, 4, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                        new TradeWithoutEmerald(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 4 ,Items.CHIPPED_ANVIL, 1, 32, 20),
                        new TradeWithoutEmerald(Items.CLAY_BALL, 1, Items.TERRACOTTA, 2, 64, 20),
                        new TradeWithoutEmerald(Items.LEATHER, 1, Items.PAPER, 3, Items.BOOK, 2, 32, 20),
                        new TradeWithoutEmerald(Items.EMERALD, 2, Items.HAY_BLOCK, 1, 15),
                        /* ************************************************************************************** *
                         *                                      RARE                                              *
                         * ************************************************************************************** */
                }, 3, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.EMERALD, 3, Items.EXPERIENCE_BOTTLE, 1, 64, 40),
                        new TradeWithoutEmerald(Items.EMERALD, 24, Items.PAPER, 8, Items.NAME_TAG, 1, 2, 40),
                        new TradeWithoutEmerald(Items.PACKED_ICE, 4, Items.BLUE_ICE, 1, 64, 40),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_CAT, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_BLOCKS, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_CHIRP, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_MELLOHI, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_STAL, 1, 50),
                        new SellEnchantedItem(Items.FISHING_ROD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.LUCK_OF_THE_SEA, 2, Items.FISHING_ROD, Enchantments.LUCK_OF_THE_SEA, 3, 1, 200),
                        new SellEnchantedItem(Items.FISHING_ROD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.LURE, 2, Items.FISHING_ROD, Enchantments.LURE, 3, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_PICKAXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.EFFICIENCY, 4, Items.DIAMOND_PICKAXE, Enchantments.EFFICIENCY, 5, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_PICKAXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_PICKAXE, Enchantments.UNBREAKING, 3, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_PICKAXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FORTUNE, 2, Items.DIAMOND_PICKAXE, Enchantments.FORTUNE, 3, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_AXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_AXE, Enchantments.UNBREAKING, 3, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_AXE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.EFFICIENCY, 4, Items.DIAMOND_AXE, Enchantments.EFFICIENCY, 5, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_SHOVEL, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_SHOVEL, Enchantments.UNBREAKING, 3, 1, 200),
                        new SellEnchantedItem(Items.DIAMOND_SHOVEL, 1, Items.ENCHANTED_BOOK, 1, Enchantments.EFFICIENCY, 4, Items.DIAMOND_SHOVEL, Enchantments.EFFICIENCY, 5, 1, 200),
                        /* ************************************************************************************** *
                         *                                    LEGENDARY                                           *
                         * ************************************************************************************** */

                }, 4, new TradeOffers.Factory[]{
                        new SellEnchantedItem(Items.DRAGON_HEAD, 5, Items.DIAMOND_PICKAXE, 1, null, 0, Items.DIAMOND_PICKAXE,
                                Enchantments.FORTUNE, 3,
                                Enchantments.UNBREAKING, 3,
                                Enchantments.MENDING, 1, 1, 500),
                }
        ));

        VEIN_GOBLIN_TRADER_TRADES_VANILLA = copyToFastUtilMap(ImmutableMap.of(
                /* ************************************************************************************** *
                 *                                     COMMON                                             *
                 * ************************************************************************************** */
                1, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.CARROT, 1, Items.EMERALD, 1, 24, 10),
                        new TradeWithoutEmerald(Items.GLOWSTONE, 1, Items.GLOWSTONE_DUST, 4, 32, 20),
                        new TradeWithoutEmerald(Items.NETHERRACK, 64, Items.EMERALD, 1, 64, 30),
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.NETHER_WART, 2, 15),
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.WARPED_FUNGUS, 5, 15),
                        new TradeWithoutEmerald(Items.EMERALD, 6, Items.GOLDEN_APPLE, 1, 15),
                        /* ************************************************************************************** *
                         *                                     UNCOMMON                                           *
                         * ************************************************************************************** */
                }, 2, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.NETHERITE_INGOT, 1, Items.LODESTONE, 2, 20),
                        new TradeWithoutEmerald(Items.ANCIENT_DEBRIS, 4, Items.NETHERITE_SCRAP, 5, 2, 50),
                        new TradeWithoutEmerald(Items.NETHER_WART, 1, Items.RED_NETHER_BRICKS, 1, 128, 20),
                        new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                        /* ************************************************************************************** *
                         *                                      RARE                                              *
                         * ************************************************************************************** */
                }, 3, new TradeOffers.Factory[]{

                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.SHARPNESS, 4, Items.DIAMOND_SWORD, Enchantments.SHARPNESS, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BANE_OF_ARTHROPODS, 4, Items.DIAMOND_SWORD, Enchantments.BANE_OF_ARTHROPODS, 5, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_ASPECT, 1, Items.DIAMOND_SWORD, Enchantments.FIRE_ASPECT, 2, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.LOOTING, 2, Items.DIAMOND_SWORD, Enchantments.LOOTING, 3, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_BOOK, 1, Enchantments.SWEEPING, 2, Items.DIAMOND_SWORD, Enchantments.SWEEPING, 3, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 3, Items.DIAMOND_HELMET, Enchantments.BLAST_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 3, Items.DIAMOND_HELMET, Enchantments.FIRE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 3, Items.DIAMOND_HELMET, Enchantments.PROJECTILE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 3, Items.DIAMOND_HELMET, Enchantments.PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_HELMET, Enchantments.UNBREAKING, 3, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 3, Items.DIAMOND_CHESTPLATE, Enchantments.BLAST_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 3, Items.DIAMOND_CHESTPLATE, Enchantments.FIRE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 3, Items.DIAMOND_CHESTPLATE, Enchantments.PROJECTILE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 3, Items.DIAMOND_CHESTPLATE, Enchantments.PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_CHESTPLATE, Enchantments.UNBREAKING, 3, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 3, Items.DIAMOND_LEGGINGS, Enchantments.BLAST_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 3, Items.DIAMOND_LEGGINGS, Enchantments.FIRE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 3, Items.DIAMOND_LEGGINGS, Enchantments.PROJECTILE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 3, Items.DIAMOND_LEGGINGS, Enchantments.PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_LEGGINGS, Enchantments.UNBREAKING, 3, 1, 100),

                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.BLAST_PROTECTION, 3, Items.DIAMOND_BOOTS, Enchantments.BLAST_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.FIRE_PROTECTION, 3, Items.DIAMOND_BOOTS, Enchantments.FIRE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROJECTILE_PROTECTION, 3, Items.DIAMOND_BOOTS, Enchantments.PROJECTILE_PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.PROTECTION, 3, Items.DIAMOND_BOOTS, Enchantments.PROTECTION, 4, 1, 100),
                        new SellEnchantedItem(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_BOOK, 1, Enchantments.UNBREAKING, 2, Items.DIAMOND_BOOTS, Enchantments.UNBREAKING, 3, 1, 100),

                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.FIRE_RESISTANCE, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.SLOW_FALLING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.NIGHT_VISION, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.WATER_BREATHING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.INVISIBILITY, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.REGENERATION, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.SWIFTNESS, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.LEAPING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.HEALING, 8, 200),
                        /* ************************************************************************************** *
                         *                                         EPIC                                           *
                         * ************************************************************************************** */

                }, 4, new TradeOffers.Factory[]{
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.LONG_FIRE_RESISTANCE, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.STRONG_HEALING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.STRONG_POISON, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.STRONG_HARMING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.STRONG_STRENGTH, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.STRONG_LEAPING, 8, 200),
                        new TradeWithoutEmeraldPotions(Items.EMERALD, 15, Potions.TURTLE_MASTER, 8, 200),

                        /* ************************************************************************************** *
                         *                                    LEGENDARY                                           *
                         * ************************************************************************************** */
                }, 5, new TradeOffers.Factory[]{
                        new SellEnchantedItem(Items.DRAGON_HEAD, 5, Items.DIAMOND_SWORD, 1, null, 0, Items.DIAMOND_SWORD, Enchantments.SHARPNESS, 5, Enchantments.UNBREAKING, 3, Enchantments.MENDING, 1, 1, 500),
                }
        ));
    }
}



