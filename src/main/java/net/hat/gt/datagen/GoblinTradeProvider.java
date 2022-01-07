package net.hat.gt.datagen;

import com.jab125.util.datagen.TradeProvider;
import com.jab125.util.tradehelper.TradeRarity;
import com.jab125.util.tradehelper.type.UpgradedBasicTrade;
import com.jab125.util.tradehelper.type.UpgradedEnchantedItemTrade;
import com.jab125.util.tradehelper.type.UpgradedOnlyInEndBasicTrade;
import com.jab125.util.tradehelper.type.UpgradedPotionTrade;
import net.hat.gt.init.ModEntities;
import net.hat.gt.init.ModItems;
import net.hat.gt.init.ModPotions;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;

import java.util.HashMap;

/**
 * Reworked for fabric by Jab125
 */
public class GoblinTradeProvider extends TradeProvider {
    public GoblinTradeProvider(DataGenerator generator)
    {
        super(generator);
    }
    @Override
    public void registerTrades() {
        this.registerGoblinTraderTrades();
        this.registerVeinGoblinTraderTrades();
    }

    private void registerGoblinTraderTrades() {
        registerCommonGoblinTrades();
        registerUncommonGoblinTrades();
        registerRareGoblinTrades();
        registerEpicGoblinTrades();
        registerLegendaryGoblinTrades();
    }

    private void registerVeinGoblinTraderTrades() {
        registerCommonVeinGoblinTrades();
        registerUncommonVeinGoblinTrades();
        registerRareVeinGoblinTrades();
        registerEpicVeinGoblinTrades();
        registerLegendaryVeinGoblinTrades();
    }

    public void registerCommonGoblinTrades() {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.APPLE))
                .setOfferStack(new ItemStack(Items.EMERALD))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.BLAZE_ROD))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.ENDER_PEARL))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.RAW_IRON))
                .setOfferStack(new ItemStack(Items.IRON_INGOT, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(30)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.RAW_GOLD, 2))
                .setOfferStack(new ItemStack(Items.GOLD_INGOT, 3))
                .setPriceMultiplier(0F)
                .setMaxTrades(30)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.RAW_COPPER, 3))
                .setOfferStack(new ItemStack(Items.COPPER_INGOT, 4))
                .setPriceMultiplier(0F)
                .setMaxTrades(30)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ROTTEN_FLESH, 4))
                .setOfferStack(new ItemStack(Items.COAL))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.GRAVEL))
                .setOfferStack(new ItemStack(Items.FLINT, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.COBBLESTONE, 32))
                .setOfferStack(new ItemStack(Items.EMERALD))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.BREAD, 3))
                .setPriceMultiplier(0F)
                .setMaxTrades(15)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());
    }
    public void registerUncommonGoblinTrades() {
        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.GUNPOWDER, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.TURTLE_EGG))
                .setOfferStack(new ItemStack(Items.EMERALD, 6))
                .setPriceMultiplier(0F)
                .setMaxTrades(2)
                .setMerchantExperience(5)
                .setPlayerExperience(50)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.PUFFERFISH_BUCKET))
                .setOfferStack(new ItemStack(Items.EMERALD, 8))
                .setPriceMultiplier(0F)
                .setMaxTrades(4)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.SPONGE, 8))
                .setPriceMultiplier(0F)
                .setMaxTrades(8)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DAMAGED_ANVIL))
                .setSecondaryPaymentStack(new ItemStack(Items.IRON_INGOT, 4))
                .setOfferStack(new ItemStack(Items.CHIPPED_ANVIL))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 2))
                .setOfferStack(new ItemStack(Items.HAY_BLOCK))
                .setPriceMultiplier(0F)
                .setMaxTrades(1)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());
    }
    public void registerRareGoblinTrades() {
        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 3))
                .setOfferStack(new ItemStack(Items.EXPERIENCE_BOTTLE))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setMerchantExperience(4)
                .setPlayerExperience(40)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 24))
                .setSecondaryPaymentStack(new ItemStack(Items.PAPER, 8))
                .setOfferStack(new ItemStack(Items.NAME_TAG))
                .setPriceMultiplier(0F)
                .setMaxTrades(2)
                .setMerchantExperience(4)
                .setPlayerExperience(40)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.PACKED_ICE, 4))
                .setOfferStack(new ItemStack(Items.BLUE_ICE))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setMerchantExperience(4)
                .setPlayerExperience(40)
                .build());

        Item[] MUSIC_DISCS = new Item[]{Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL};

        for (Item music_disc : MUSIC_DISCS) {
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedBasicTrade.Builder.create()
                    .setPaymentStack(new ItemStack(Items.EMERALD, 32))
                    .setOfferStack(new ItemStack(music_disc))
                    .setPriceMultiplier(0F)
                    .setMaxTrades(1)
                    .setMerchantExperience(5)
                    .setPlayerExperience(50)
                    .build());
        }

        EnchantmentLevelEntry[] FISHING_ROD_ENCHANTS = new EnchantmentLevelEntry[]{new EnchantmentLevelEntry(Enchantments.LUCK_OF_THE_SEA, 3), new EnchantmentLevelEntry(Enchantments.LURE, 3)};

        for (EnchantmentLevelEntry fishing_enchant : FISHING_ROD_ENCHANTS) {
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                    .setStack(new ItemStack(Items.FISHING_ROD))
                    .setPriceMultiplier(0F)
                    .setMaxTrades(1)
                    .setMerchantExperience(7)
                    .setPlayerExperience(100)
                    .setEnchantment(fishing_enchant.enchantment)
                    .setOfferLevel(5)
                    .setPaymentLevel(fishing_enchant.level)
                    .build());
        }

        EnchantmentLevelEntry[] PICKAXE_ENCHANTS = new EnchantmentLevelEntry[]{new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 5), new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3), new EnchantmentLevelEntry(Enchantments.FORTUNE, 3)};

        for (EnchantmentLevelEntry pickaxe_enchant : PICKAXE_ENCHANTS) {
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                    .setStack(new ItemStack(Items.DIAMOND_PICKAXE))
                    .setPriceMultiplier(0F)
                    .setMaxTrades(1)
                    .setMerchantExperience(7)
                    .setPlayerExperience(100)
                    .setEnchantment(pickaxe_enchant.enchantment)
                    .setPaymentLevel(pickaxe_enchant.level)
                    .setOfferLevel(pickaxe_enchant.enchantment.getMaxLevel() + 1)
                    .build());
        }

        EnchantmentLevelEntry[] AXE_AND_SHOVEL_ENCHANTS = new EnchantmentLevelEntry[]{new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 5), new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3)};

        for (EnchantmentLevelEntry axe_enchant : AXE_AND_SHOVEL_ENCHANTS) {
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                    .setStack(new ItemStack(Items.DIAMOND_AXE))
                    .setPriceMultiplier(0F)
                    .setMaxTrades(1)
                    .setMerchantExperience(7)
                    .setPlayerExperience(100)
                    .setEnchantment(axe_enchant.enchantment)
                    .setPaymentLevel(axe_enchant.level)
                    .setOfferLevel(axe_enchant.enchantment.getMaxLevel() + 1)
                    .build());
        }

        for (EnchantmentLevelEntry shovel_enchant : AXE_AND_SHOVEL_ENCHANTS) {
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                    .setStack(new ItemStack(Items.DIAMOND_SHOVEL))
                    .setPriceMultiplier(0F)
                    .setMaxTrades(1)
                    .setMerchantExperience(7)
                    .setPlayerExperience(100)
                    .setEnchantment(shovel_enchant.enchantment)
                    .setOfferLevel(shovel_enchant.enchantment.getMaxLevel() + 1)
                    .setPaymentLevel(shovel_enchant.level)
                    .build());
        }

    }


    public void registerEpicGoblinTrades() {

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.EPIC, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.GOLDEN_APPLE, 3))
                .setSecondaryPaymentStack(new ItemStack(Items.GREEN_BANNER))
                .setOfferStack(new ItemStack(ModItems.APPLE_CAPE))
                .setPriceMultiplier(0F)
                .setMaxTrades(120)
                .setMerchantExperience(15)
                .setPlayerExperience(200)
                .build());
    }
    public void registerLegendaryGoblinTrades() {
        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.LEGENDARY, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 5))
                .setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setOfferStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setPriceMultiplier(0F)
                .setMaxTrades(1)
                .setPlayerExperience(500)
                .setMerchantExperience(12)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.FORTUNE, 5))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 4))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1), false)
                .build());
    }
    public void registerCommonVeinGoblinTrades() {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.CARROT))
                .setOfferStack(new ItemStack(Items.EMERALD))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.GLOWSTONE))
                .setOfferStack(new ItemStack(Items.GLOWSTONE_DUST, 4))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERRACK, 64))
                .setOfferStack(new ItemStack(Items.EMERALD, 1))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.NETHER_WART, 2))
                .setPriceMultiplier(0F)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.WARPED_FUNGUS, 5))
                .setPriceMultiplier(0F)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 6))
                .setOfferStack(new ItemStack(Items.GOLDEN_APPLE))
                .setPriceMultiplier(0F)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());
    }
    public void registerUncommonVeinGoblinTrades() {
        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT))
                .setOfferStack(new ItemStack(Items.LODESTONE, 2))
                .setPriceMultiplier(0F)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ANCIENT_DEBRIS, 4))
                .setOfferStack(new ItemStack(Items.NETHERITE_SCRAP, 5))
                .setPriceMultiplier(0F)
                .setMaxTrades(2)
                .setMerchantExperience(5)
                .setPlayerExperience(50)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHER_WART))
                .setOfferStack(new ItemStack(Items.RED_NETHER_BRICKS, 1))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHER_BRICK))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 8))
                .setOfferStack(new ItemStack(Items.SPONGE, 8))
                .setSecondaryPaymentStack(new ItemStack(Items.SPONGE))
                .setPriceMultiplier(0F)
                .setMaxTrades(4)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());
    }
    public void registerRareVeinGoblinTrades() {
        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        EnchantmentLevelEntry[] swordEnchantments = new EnchantmentLevelEntry[] {
                new EnchantmentLevelEntry(Enchantments.SHARPNESS, 5),
                new EnchantmentLevelEntry(Enchantments.LOOTING, 3),
                new EnchantmentLevelEntry(Enchantments.SWEEPING, 3),
                new EnchantmentLevelEntry(Enchantments.FIRE_ASPECT, 2),
                //new EnchantmentLevelEntry(Enchantments.KNOCKBACK, 2),
                new EnchantmentLevelEntry(Enchantments.BANE_OF_ARTHROPODS, 5),
                //new EnchantmentLevelEntry(Enchantments.SMITE, 5)
        };
        for(EnchantmentLevelEntry swordEnchant : swordEnchantments)
        {
            this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                    .setStack(new ItemStack(Items.DIAMOND_SWORD))
                    .setPriceMultiplier(0F)
                    .setMaxTrades(1)
                    .setMerchantExperience(7)
                    .setPlayerExperience(100)
                    .setEnchantment(swordEnchant.enchantment)
                    .setOfferLevel(swordEnchant.level + 1)
                    .setPaymentLevel(swordEnchant.level)
                    .build());
        }

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                .setStack(new ItemStack(Items.BOW))
                .setPriceMultiplier(0F)
                .setMaxTrades(1)
                .setMerchantExperience(7)
                .setPlayerExperience(100)
                .setEnchantment(Enchantments.POWER)
                .setOfferLevel(6)
                .setPaymentLevel(5)
                .build());

        Item[] diamondArmorSet = new Item[] {Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS};
        EnchantmentLevelEntry[] armorEnchantments = new EnchantmentLevelEntry[] {
                new EnchantmentLevelEntry(Enchantments.BLAST_PROTECTION, 4),
                new EnchantmentLevelEntry(Enchantments.FIRE_PROTECTION, 4),
                new EnchantmentLevelEntry(Enchantments.PROJECTILE_PROTECTION, 4),
                new EnchantmentLevelEntry(Enchantments.PROTECTION, 4),
                new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3)
        };
        for(Item piece : diamondArmorSet)
        {
            for(EnchantmentLevelEntry armorEnchant : armorEnchantments)
            {
                this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, UpgradedEnchantedItemTrade.Builder.create()
                        .setStack(new ItemStack(piece))
                        .setPriceMultiplier(0F)
                        .setMaxTrades(1)
                        .setMerchantExperience(7)
                        .setPlayerExperience(100)
                        .setEnchantment(armorEnchant.enchantment)
                        .setOfferLevel(armorEnchant.level + 1)
                        .setPaymentLevel(armorEnchant.level)
                        .build());
            }
        }

        Potion[] rarePotions = new Potion[] {
                ModPotions.EXTENDED_NIGHT_VISION,
                ModPotions.EXTENDED_INVISIBILITY,
                ModPotions.POWERFUL_JUMP_BOOST,
                ModPotions.EXTENDED_FIRE_RESISTANCE,
                ModPotions.POWERFUL_SPEED,
                ModPotions.EXTENDED_WATER_BREATHING,
                ModPotions.POWERFUL_INSTANT_HEALTH,
                ModPotions.POWERFUL_REGENERATION,
                ModPotions.EXTENDED_SLOW_FALLING
        };
        for(Potion potion : rarePotions)
        {
            ItemStack awkwardPotion = new ItemStack(Items.POTION);
            PotionUtil.setPotion(awkwardPotion, Potions.AWKWARD);
            this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, UpgradedPotionTrade.Builder.create()
                    .setOfferStack(new ItemStack(Items.POTION))
                    .setPaymentStack(new ItemStack(Items.EMERALD, 15))
                    .setSecondaryPaymentStack(awkwardPotion)
                    .setPriceMultiplier(0.5F)
                    .setMaxTrades(8)
                    .setPlayerExperience(200)
                    .setMerchantExperience(9)
                    .setPotion(potion)
                    .build());
        }
    }
    public void registerEpicVeinGoblinTrades() {
        /* ************************************************************************************** *
         *                                      EPIC                                              *
         * ************************************************************************************** */

        Potion[] epicPotions = new Potion[] {
                ModPotions.HASTE,
                ModPotions.ABSORPTION,
                ModPotions.LUCK,
                ModPotions.DOLPHINS_GRACE,
                ModPotions.LEVITATION,
                ModPotions.POWERFUL_STRENGTH,
                ModPotions.BLINDNESS
        };
        for(Potion potion : epicPotions)
        {
            ItemStack awkwardPotion = new ItemStack(Items.POTION);
            PotionUtil.setPotion(awkwardPotion, Potions.AWKWARD);
            this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.EPIC, UpgradedPotionTrade.Builder.create()
                    .setOfferStack(new ItemStack(Items.POTION))
                    .setPotion(potion)
                    .setPaymentStack(new ItemStack(Items.EMERALD, 25))
                    .setSecondaryPaymentStack(awkwardPotion)
                    .setPriceMultiplier(0.5F)
                    .setMaxTrades(8)
                    .setMerchantExperience(9)
                    .setPlayerExperience(200)
                    .build());
        }
    }
    public void registerLegendaryVeinGoblinTrades() {
        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.LEGENDARY, UpgradedBasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.DIAMOND_SWORD))
                .setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 5))
                .setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_SWORD))
                .setPriceMultiplier(0F)
                .setMaxTrades(1)
                .setMerchantExperience(12)
                .setPlayerExperience(500)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 4))
                .build());

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.LEGENDARY, UpgradedOnlyInEndBasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.MUSIC_DISC_OTHERSIDE))
                .setPaymentStack(new ItemStack(Items.ENDER_EYE, 13))
                .setSecondaryPaymentStack(new ItemStack(Items.WITHER_SKELETON_SKULL, 3))
                .setMerchantExperience(15)
                .setPlayerExperience(66)
                .build());
    }





    public static HashMap<Enchantment, Integer> toHashMap(EnchantmentLevelEntry enchantmentLevelEntry) {
        HashMap<Enchantment, Integer> hashMap = new HashMap<>();
        hashMap.put(enchantmentLevelEntry.enchantment, enchantmentLevelEntry.level);
        return hashMap;
    }

    @SuppressWarnings("unused")
    public static HashMap<Enchantment, Integer> toHashMap(EnchantmentLevelEntry enchantmentLevelEntry, int level) {
        HashMap<Enchantment, Integer> hashMap = new HashMap<>();
        hashMap.put(enchantmentLevelEntry.enchantment, level);
        return hashMap;
    }
}
