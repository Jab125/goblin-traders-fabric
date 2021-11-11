package net.hat.gt.datagen;

import com.google.common.collect.ImmutableMap;
import com.jab125.util.datagen.TradeProvider;
import com.jab125.util.tradehelper.TradeRarity;
import com.jab125.util.tradehelper.type.BasicTrade;
import net.hat.gt.init.ModEntities;
import net.hat.gt.init.ModPotions;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;

/**
 * Reworked for fabric by Jab125
 */
public class GoblinTradeProvider extends TradeProvider
{
    public GoblinTradeProvider(DataGenerator generator)
    {
        super(generator);
    }

    /**
     * Feel free to mixin into this for stuff (like for addons)
     */
    @Override
    public void registerTrades()
    {
        System.out.println("TRADES REGISTERED");
        this.registerGoblinTraderTrades();
        this.registerVeinGoblinTraderTrades();
    }

    private void registerGoblinTraderTrades()
    {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.APPLE))
                .setOfferStack(new ItemStack(Items.EMERALD))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.RAW_IRON))
                .setOfferStack(new ItemStack(Items.IRON_INGOT, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(30)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.RAW_GOLD, 2))
                .setOfferStack(new ItemStack(Items.GOLD_INGOT, 3))
                .setPriceMultiplier(0F)
                .setMaxTrades(30)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ROTTEN_FLESH, 4))
                .setOfferStack(new ItemStack(Items.COAL))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.GRAVEL))
                .setOfferStack(new ItemStack(Items.FLINT, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.GRAVEL))
                .setOfferStack(new ItemStack(Items.FLINT, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.COBBLESTONE, 32))
                .setOfferStack(new ItemStack(Items.EMERALD))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.BREAD, 3))
                .setPriceMultiplier(0F)
                .setMaxTrades(15)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());


        
        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.GUNPOWDER, 2))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.TURTLE_EGG))
                .setOfferStack(new ItemStack(Items.EMERALD, 6))
                .setPriceMultiplier(0F)
                .setMaxTrades(2)
                .setMerchantExperience(5)
                .setPlayerExperience(50)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.PUFFERFISH_BUCKET))
                .setOfferStack(new ItemStack(Items.EMERALD, 8))
                .setPriceMultiplier(0F)
                .setMaxTrades(4)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setOfferStack(new ItemStack(Items.SPONGE, 8))
                .setPriceMultiplier(0F)
                .setMaxTrades(8)
                .setMerchantExperience(3)
                .setPlayerExperience(30)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DAMAGED_ANVIL))
                .setSecondaryPaymentStack(new ItemStack(Items.IRON_INGOT, 4))
                .setOfferStack(new ItemStack(Items.CHIPPED_ANVIL))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 2))
                .setOfferStack(new ItemStack(Items.HAY_BLOCK))
                .setPriceMultiplier(0F)
                .setMaxTrades(1)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        //below is mrcrayfish
        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.EXPERIENCE_BOTTLE))
                .setPaymentStack(new ItemStack(Items.EMERALD, 3))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setExperience(50)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.NAME_TAG))
                .setPaymentStack(new ItemStack(Items.EMERALD, 24))
                .setSecondaryPaymentStack(new ItemStack(Items.PAPER, 8))
                .setPriceMultiplier(0F)
                .setMaxTrades(2)
                .setExperience(40)
                .build());

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.BLUE_ICE))
                .setPaymentStack(new ItemStack(Items.PACKED_ICE, 4))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setExperience(40)
                .build());

        ItemStack luckOfTheSeaBook = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantmentHelper.set(ImmutableMap.of(Enchantments.LUCK_OF_THE_SEA, 3), luckOfTheSeaBook);
        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.FISHING_ROD))
                .setPaymentStack(new ItemStack(Items.FISHING_ROD))
                .setSecondaryPaymentStack(luckOfTheSeaBook)
                .setPriceMultiplier(0.5F)
                .setMaxTrades(2)
                .setExperience(100)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.LUCK_OF_THE_SEA, 5))
                
                .build());

        ItemStack lureBook = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantmentHelper.set(ImmutableMap.of(Enchantments.LURE, 3), lureBook);
        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.FISHING_ROD))
                .setPaymentStack(new ItemStack(Items.FISHING_ROD))
                .setSecondaryPaymentStack(lureBook)
                .setPriceMultiplier(0.5F)
                .setMaxTrades(2)
                .setExperience(100)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.LURE, 5))
                
                .build());

        Item[] creeperMusicDiscs = new Item[]{Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL};
        for(Item disc : creeperMusicDiscs)
        {
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create().setOfferStack(new ItemStack(disc, 1)).setPaymentStack(new ItemStack(Items.EMERALD, 32)).setPriceMultiplier(0F).setMaxTrades(1).setExperience(100).build());
        }

        EnchantmentLevelEntry[] pickaxeEnchantments = new EnchantmentLevelEntry[] {new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 5), new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3), new EnchantmentLevelEntry(Enchantments.FORTUNE, 3)};
        for(EnchantmentLevelEntry pickaxeEnchant : pickaxeEnchantments)
        {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.set(ImmutableMap.of(pickaxeEnchant.enchantment, pickaxeEnchant.level), enchantedBook);
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create().setOfferStack(new ItemStack(Items.DIAMOND_PICKAXE)).setPaymentStack(new ItemStack(Items.DIAMOND_PICKAXE)).setSecondaryPaymentStack(enchantedBook).setPriceMultiplier(0F).setMaxTrades(1).setExperience(200).addEnchantment(new EnchantmentLevelEntry(pickaxeEnchant.enchantment, pickaxeEnchant.level + 1)).build());
        }

        EnchantmentLevelEntry[] axeEnchantments = new EnchantmentLevelEntry[] {new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 5), new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3)};
        for(EnchantmentLevelEntry axeEnchant : axeEnchantments)
        {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.set(ImmutableMap.of(axeEnchant.enchantment, axeEnchant.level), enchantedBook);
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create().setOfferStack(new ItemStack(Items.DIAMOND_AXE)).setPaymentStack(new ItemStack(Items.DIAMOND_AXE)).setSecondaryPaymentStack(enchantedBook).setPriceMultiplier(0F).setMaxTrades(1).setExperience(200).addEnchantment(new EnchantmentLevelEntry(axeEnchant.enchantment, axeEnchant.level + 1)).build());
        }

        EnchantmentLevelEntry[] shovelEnchantments = new EnchantmentLevelEntry[] {new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 5), new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3)};
        for(EnchantmentLevelEntry shovelEnchant : shovelEnchantments)
        {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.set(ImmutableMap.of(shovelEnchant.enchantment, shovelEnchant.level), enchantedBook);
            this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create().setOfferStack(new ItemStack(Items.DIAMOND_SHOVEL)).setPaymentStack(new ItemStack(Items.DIAMOND_SHOVEL)).setSecondaryPaymentStack(enchantedBook).setPriceMultiplier(0F).setMaxTrades(1).setExperience(200).addEnchantment(new EnchantmentLevelEntry(shovelEnchant.enchantment, shovelEnchant.level + 1)).build());
        }

        this.addTrade(ModEntities.GOBLIN_TRADER, TradeRarity.LEGENDARY, BasicTrade.Builder.create().setOfferStack(new ItemStack(Items.DIAMOND_PICKAXE)).setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 5)).setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_PICKAXE)).setPriceMultiplier(0F).setMaxTrades(1).setExperience(500).addEnchantment(new EnchantmentLevelEntry(Enchantments.FORTUNE, 5)).addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 5)).build());
    }

    private void registerVeinGoblinTraderTrades()
    {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.EMERALD))
                .setPaymentStack(new ItemStack(Items.CARROT))
                .setPriceMultiplier(0F)
                .setMaxTrades(24)
                .setExperience(20)
                .build());
        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.GLOWSTONE_DUST, 4))
                .setPaymentStack(new ItemStack(Items.GLOWSTONE))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setExperience(20)
                .build());
        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.EMERALD, 1))
                .setPaymentStack(new ItemStack(Items.NETHERRACK, 64))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setExperience(20)
                .build());
        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.NETHER_WART, 2))
                .setPaymentStack(new ItemStack(Items.EMERALD))
                .setPriceMultiplier(0F)
                .setExperience(20)
                .build());

        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.TOTEM_OF_UNDYING))
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 2))
                .setPriceMultiplier(0F)
                .setExperience(200)
                .build());
        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.NETHERITE_SCRAP, 5))
                .setPaymentStack(new ItemStack(Items.ANCIENT_DEBRIS, 4))
                .setPriceMultiplier(0F)
                .setMaxTrades(20)
                .setExperience(100)
                .build());
        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setOfferStack(new ItemStack(Items.RED_NETHER_BRICKS, 1))
                .setPaymentStack(new ItemStack(Items.NETHER_WART))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHER_BRICK))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                .setExperience(20)
                .build());

        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        EnchantmentLevelEntry[] swordEnchantments = new EnchantmentLevelEntry[] {new EnchantmentLevelEntry(Enchantments.SHARPNESS, 5), new EnchantmentLevelEntry(Enchantments.LOOTING, 3), new EnchantmentLevelEntry(Enchantments.SWEEPING, 3), new EnchantmentLevelEntry(Enchantments.FIRE_ASPECT, 2), new EnchantmentLevelEntry(Enchantments.KNOCKBACK, 2), new EnchantmentLevelEntry(Enchantments.BANE_OF_ARTHROPODS, 5), new EnchantmentLevelEntry(Enchantments.SMITE, 5)};
        for(EnchantmentLevelEntry swordEnchant : swordEnchantments)
        {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.set(ImmutableMap.of(swordEnchant.enchantment, swordEnchant.level), enchantedBook);
            this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create().setOfferStack(new ItemStack(Items.DIAMOND_SWORD)).setPaymentStack(new ItemStack(Items.DIAMOND_SWORD)).setSecondaryPaymentStack(enchantedBook).setPriceMultiplier(0F).setMaxTrades(1).setExperience(100).addEnchantment(new EnchantmentLevelEntry(swordEnchant.enchantment, swordEnchant.level + 1)).build());
        }

        Item[] diamondArmorSet = new Item[] {Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS};
        EnchantmentLevelEntry[] armorEnchantments = new EnchantmentLevelEntry[] {new EnchantmentLevelEntry(Enchantments.BLAST_PROTECTION, 4), new EnchantmentLevelEntry(Enchantments.FIRE_PROTECTION, 4), new EnchantmentLevelEntry(Enchantments.PROJECTILE_PROTECTION, 4), new EnchantmentLevelEntry(Enchantments.PROTECTION, 4), new EnchantmentLevelEntry(Enchantments.UNBREAKING, 3)};
        for(Item piece : diamondArmorSet)
        {
            for(EnchantmentLevelEntry armorEnchant : armorEnchantments)
            {
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                EnchantmentHelper.set(ImmutableMap.of(armorEnchant.enchantment, armorEnchant.level), enchantedBook);
                this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create().setOfferStack(new ItemStack(piece)).setPaymentStack(new ItemStack(piece)).setSecondaryPaymentStack(enchantedBook).setPriceMultiplier(0F).setMaxTrades(1).setExperience(100).addEnchantment(new EnchantmentLevelEntry(armorEnchant.enchantment, armorEnchant.level + 1)).build());
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
            ItemStack potionStack = new ItemStack(Items.POTION);
            PotionUtil.setPotion(potionStack, potion);
            ItemStack awkwardPotion = new ItemStack(Items.POTION);
            PotionUtil.setPotion(awkwardPotion, Potions.AWKWARD);
            this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                    .setOfferStack(potionStack)
                    .setPaymentStack(new ItemStack(Items.EMERALD, 15))
                    .setSecondaryPaymentStack(awkwardPotion)
                    .setPriceMultiplier(0.5F)
                    .setMaxTrades(8)
                    .setExperience(1000)
                    .build());
        }
        /* ************************************************************************************** *
         *                                      EPIC                                              *
         * ************************************************************************************** */

        Potion[] epicPotions = new Potion[] {
            ModPotions.HASTE,
            ModPotions.ABSORPTION,
            ModPotions.LEVITATION,
            ModPotions.LUCK,
            ModPotions.DOLPHINS_GRACE,
            ModPotions.POWERFUL_STRENGTH
        };
        for(Potion potion : epicPotions)
        {
            ItemStack potionStack = new ItemStack(Items.POTION);
            PotionUtil.setPotion(potionStack, potion);
            ItemStack awkwardPotion = new ItemStack(Items.POTION);
            PotionUtil.setPotion(awkwardPotion, Potions.AWKWARD);
            this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.EPIC, BasicTrade.Builder.create()
                    .setOfferStack(potionStack)
                    .setPaymentStack(new ItemStack(Items.EMERALD, 25))
                    .setSecondaryPaymentStack(awkwardPotion)
                    .setPriceMultiplier(0.5F)
                    .setMaxTrades(4)
                    .setExperience(1000)
                    .build());
        }

        this.addTrade(ModEntities.VEIN_GOBLIN_TRADER, TradeRarity.LEGENDARY, BasicTrade.Builder.create().setOfferStack(new ItemStack(Items.DIAMOND_SWORD)).setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 5)).setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_SWORD)).setPriceMultiplier(0F).setMaxTrades(1).setExperience(500).addEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 7)).addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 7)).build());
    }
}