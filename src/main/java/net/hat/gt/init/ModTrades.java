package net.hat.gt.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.hat.gt.GobT;
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
class TradeWithoutEmerald implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final ItemStack secondBuy;
    private final int secondPrice;
    private final int price;
    private final ItemStack sell;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;
            // This one is default. One Item Input -> One Output
    public TradeWithoutEmerald(ItemConvertible item, int $, Item sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item);
        this.secondPrice = 0;
        this.sell = new ItemStack(sellItem);
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }
            // This one is just for an undefined amount of uses
    public TradeWithoutEmerald(ItemConvertible item, int $, Item sellItem, int sellCount, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item);
        this.secondPrice = 0;
        this.sell = new ItemStack(sellItem);
        this.sellCount = sellCount;
        this.maxUses = 1;
        this.experience = experience;
        this.multiplier = 0.05F;
    }
            // This one is if you want to have a second Item required for the trade.
    public TradeWithoutEmerald(ItemConvertible item, int $, ItemConvertible item2, int sC, Item sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.sell = new ItemStack(sellItem);
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }


    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        return new TradeOffer(new ItemStack(this.firstBuy.getItem(), this.price), new ItemStack(this.secondBuy.getItem(), this.secondPrice), new ItemStack(this.sell.getItem(), this.sellCount), this.maxUses, this.experience, this.multiplier);
    }
}



/**
 * For the {@code Potion-ID's} refer to {@link ModPotions}.
 * <p>Add Potions and duration into ModPotions.
 * Use accordingly, also change {@code LANG} file
 */
class TradeWithoutEmeraldPotions implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final ItemStack secondBuy;
    private final int secondPrice;
    private final int price;
    private final ItemStack sell;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;
    private final Potion potionType;

    public TradeWithoutEmeraldPotions(ItemConvertible item, int $, Potion potionType, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.potionType = potionType;
        this.secondBuy = new ItemStack(Items.POTION, 1);
        this.secondPrice = 1;
        this.sell = new ItemStack(Items.POTION, 1);
        this.sellCount = 1;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        ItemStack itemStack2 = PotionUtil.setPotion(new ItemStack(this.sell.getItem(), this.sellCount), this.potionType);
        ItemStack awkwardPotion = PotionUtil.setPotion(new ItemStack(this.secondBuy.getItem(), this.secondPrice), Potions.AWKWARD);
        return new TradeOffer(new ItemStack(this.firstBuy.getItem(), this.price), awkwardPotion, itemStack2, this.maxUses, this.experience, this.multiplier);
    }
}


@SuppressWarnings("unused")
class SellEnchantedItem implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final ItemStack secondBuy;
    private final int secondPrice;
    private final int price;
    private final ItemStack tool;
    private final int maxUses;
    private final int experience;
    private final Enchantment bookEnchant;
    private final Enchantment enchantment1;
    private final Enchantment enchantment2;
    private final Enchantment enchantment3;
    private final Enchantment enchantment4;
    private final Enchantment enchantment5;
    private final Enchantment enchantment6;
    private final int levelBook;
    private final int level1;
    private final int level2;
    private final int level3;
    private final int level4;
    private final int level5;
    private final int level6;

    public SellEnchantedItem(ItemConvertible item, int $, ItemConvertible item2, int sC, Enchantment bookEnchant, int levelBook, ItemConvertible itemSell, 
                             Enchantment enchantment, 
                             int enchLevel, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment;
        this.enchantment2 = null;
        this.enchantment3 = null;
        this.enchantment4 = null;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = new ItemStack(itemSell);
        this.maxUses = maxUses;
        this.experience = experience;
        this.levelBook = levelBook;
        this.level1 = enchLevel;
        this.level2 = 0;
        this.level3 = 0;
        this.level4 = 0;
        this.level5 = 0;
        this.level6 = 0;
    }

    public SellEnchantedItem(ItemConvertible item, int $, ItemConvertible item2, int sC, Enchantment bookEnchant, int levelBook, ItemConvertible itemSell,
                                    Enchantment enchantment1, int enchLevel1,
                                    Enchantment enchantment2, int enchLevel2,
                                    int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = null;
        this.enchantment4 = null;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = new ItemStack(itemSell);
        this.maxUses = maxUses;
        this.experience = experience;
        this.levelBook = 0;
        this.level1 = enchLevel1;
        this.level2 = enchLevel2;
        this.level3 = 0;
        this.level4 = 0;
        this.level5 = 0;
        this.level6 = 0;
    }

    public SellEnchantedItem(ItemConvertible item, int $, ItemConvertible item2, int sC, Enchantment bookEnchant, int levelBook, ItemConvertible itemSell,
                                    Enchantment enchantment1, int enchLevel1,
                                    Enchantment enchantment2, int enchLevel2,
                                    Enchantment enchantment3, int enchLevel3,
                                    int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = null;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = new ItemStack(itemSell);
        this.maxUses = maxUses;
        this.experience = experience;
        this.levelBook = 0;
        this.level1 = enchLevel1;
        this.level2 = enchLevel2;
        this.level3 = enchLevel3;
        this.level4 = 0;
        this.level5 = 0;
        this.level6 = 0;
    }

    public SellEnchantedItem(ItemConvertible item, int $, ItemConvertible item2, int sC, Enchantment bookEnchant, int levelBook, ItemConvertible itemSell,
                                    Enchantment enchantment1, int enchLevel1,
                                    Enchantment enchantment2, int enchLevel2,
                                    Enchantment enchantment3, int enchLevel3,
                                    Enchantment enchantment4, int enchLevel4,
                                    int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = enchantment4;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = new ItemStack(itemSell);
        this.maxUses = maxUses;
        this.experience = experience;
        this.levelBook = 0;
        this.level1 = enchLevel1;
        this.level2 = enchLevel2;
        this.level3 = enchLevel3;
        this.level4 = enchLevel4;
        this.level5 = 0;
        this.level6 = 0;
    }

    public SellEnchantedItem(ItemConvertible item, int $, ItemConvertible item2, int sC, Enchantment bookEnchant, int levelBook, ItemConvertible itemSell,
                                    Enchantment enchantment1, int enchLevel1,
                                    Enchantment enchantment2, int enchLevel2,
                                    Enchantment enchantment3, int enchLevel3,
                                    Enchantment enchantment4, int enchLevel4,
                                    Enchantment enchantment5, int enchLevel5,
                                    int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = enchantment4;
        this.enchantment5 = enchantment5;
        this.enchantment6 = null;
        this.tool = new ItemStack(itemSell);
        this.maxUses = maxUses;
        this.experience = experience;
        this.levelBook = 0;
        this.level1 = enchLevel1;
        this.level2 = enchLevel2;
        this.level3 = enchLevel3;
        this.level4 = enchLevel4;
        this.level5 = enchLevel5;
        this.level6 = 0;
    }
    public SellEnchantedItem(ItemConvertible item, int $, ItemConvertible item2, int sC, Enchantment bookEnchant, int levelBook, ItemConvertible itemSell,
                                    Enchantment enchantment1, int enchLevel1,
                                    Enchantment enchantment2, int enchLevel2,
                                    Enchantment enchantment3, int enchLevel3,
                                    Enchantment enchantment4, int enchLevel4,
                                    Enchantment enchantment5, int enchLevel5,
                                    Enchantment enchantment6, int enchLevel6,
                                    int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = enchantment4;
        this.enchantment5 = enchantment5;
        this.enchantment6 = enchantment6;
        this.tool = new ItemStack(itemSell);
        this.maxUses = maxUses;
        this.experience = experience;
        this.levelBook = 0;
        this.level1 = enchLevel1;
        this.level2 = enchLevel2;
        this.level3 = enchLevel3;
        this.level4 = enchLevel4;
        this.level5 = enchLevel5;
        this.level6 = enchLevel6;
    }

    public TradeOffer create(Entity entity, Random random) {
        ItemStack itemStack = new ItemStack(this.tool.getItem(), 1);
        ItemStack itemStack2 = new ItemStack(this.secondBuy.getItem(), this.secondPrice);
        if (this.bookEnchant != null) {
            EnchantedBookItem.addEnchantment(itemStack2, new EnchantmentLevelEntry(this.bookEnchant, this.levelBook));}
        itemStack.addEnchantment(this.enchantment1, this.level1);
        if (this.enchantment2 != null) {itemStack.addEnchantment(this.enchantment2, this.level2);}
        if (this.enchantment3 != null) {itemStack.addEnchantment(this.enchantment3, this.level3);}
        if (this.enchantment4 != null) {itemStack.addEnchantment(this.enchantment4, this.level4);}
        if (this.enchantment5 != null) {itemStack.addEnchantment(this.enchantment5, this.level5);}
        if (this.enchantment6 != null) {itemStack.addEnchantment(this.enchantment6, this.level6);}
        return new TradeOffer(new ItemStack(this.firstBuy.getItem(), this.price), itemStack2, itemStack, this.maxUses, this.experience, 0F);
    }
}
