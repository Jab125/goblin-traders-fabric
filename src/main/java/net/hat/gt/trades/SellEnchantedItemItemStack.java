package net.hat.gt.trades;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.Random;


@SuppressWarnings("unused")
public class SellEnchantedItemItemStack implements TradeOffers.Factory {
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

    public SellEnchantedItemItemStack(ItemStack item, int $, ItemStack item2, int sC, Enchantment bookEnchant, int levelBook, ItemStack itemSell,
                                      Enchantment enchantment,
                                      int enchLevel, int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment;
        this.enchantment2 = null;
        this.enchantment3 = null;
        this.enchantment4 = null;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = itemSell;
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

    public SellEnchantedItemItemStack(ItemStack item, int $, ItemStack item2, int sC, Enchantment bookEnchant, int levelBook, ItemStack itemSell,
                                      Enchantment enchantment1, int enchLevel1,
                                      Enchantment enchantment2, int enchLevel2,
                                      int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = null;
        this.enchantment4 = null;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = itemSell;
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

    public SellEnchantedItemItemStack(ItemStack item, int $, ItemStack item2, int sC, Enchantment bookEnchant, int levelBook, ItemStack itemSell,
                                      Enchantment enchantment1, int enchLevel1,
                                      Enchantment enchantment2, int enchLevel2,
                                      Enchantment enchantment3, int enchLevel3,
                                      int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = null;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = itemSell;
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

    public SellEnchantedItemItemStack(ItemStack item, int $, ItemStack item2, int sC, Enchantment bookEnchant, int levelBook, ItemStack itemSell,
                                      Enchantment enchantment1, int enchLevel1,
                                      Enchantment enchantment2, int enchLevel2,
                                      Enchantment enchantment3, int enchLevel3,
                                      Enchantment enchantment4, int enchLevel4,
                                      int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = enchantment4;
        this.enchantment5 = null;
        this.enchantment6 = null;
        this.tool = itemSell;
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

    public SellEnchantedItemItemStack(ItemStack item, int $, ItemStack item2, int sC, Enchantment bookEnchant, int levelBook, ItemStack itemSell,
                                      Enchantment enchantment1, int enchLevel1,
                                      Enchantment enchantment2, int enchLevel2,
                                      Enchantment enchantment3, int enchLevel3,
                                      Enchantment enchantment4, int enchLevel4,
                                      Enchantment enchantment5, int enchLevel5,
                                      int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = enchantment4;
        this.enchantment5 = enchantment5;
        this.enchantment6 = null;
        this.tool = itemSell;
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
    public SellEnchantedItemItemStack(ItemStack item, int $, ItemStack item2, int sC, Enchantment bookEnchant, int levelBook, ItemStack itemSell,
                                      Enchantment enchantment1, int enchLevel1,
                                      Enchantment enchantment2, int enchLevel2,
                                      Enchantment enchantment3, int enchLevel3,
                                      Enchantment enchantment4, int enchLevel4,
                                      Enchantment enchantment5, int enchLevel5,
                                      Enchantment enchantment6, int enchLevel6,
                                      int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.bookEnchant = bookEnchant;
        this.enchantment1 = enchantment1;
        this.enchantment2 = enchantment2;
        this.enchantment3 = enchantment3;
        this.enchantment4 = enchantment4;
        this.enchantment5 = enchantment5;
        this.enchantment6 = enchantment6;
        this.tool = itemSell;
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
        ItemStack itemStack = this.tool;
        itemStack.setCount(price);
        ItemStack itemStack2 = this.secondBuy;
        itemStack2.setCount(secondPrice);
        if (this.bookEnchant != null) {
            EnchantedBookItem.addEnchantment(itemStack2, new EnchantmentLevelEntry(this.bookEnchant, this.levelBook));}
        itemStack.addEnchantment(this.enchantment1, this.level1);
        if (this.enchantment2 != null) {itemStack.addEnchantment(this.enchantment2, this.level2);}
        if (this.enchantment3 != null) {itemStack.addEnchantment(this.enchantment3, this.level3);}
        if (this.enchantment4 != null) {itemStack.addEnchantment(this.enchantment4, this.level4);}
        if (this.enchantment5 != null) {itemStack.addEnchantment(this.enchantment5, this.level5);}
        if (this.enchantment6 != null) {itemStack.addEnchantment(this.enchantment6, this.level6);}
        return new TradeOffer(this.firstBuy, itemStack2, itemStack, this.maxUses, this.experience, 0F);
    }
}
