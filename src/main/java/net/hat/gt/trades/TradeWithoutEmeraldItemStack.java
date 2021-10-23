package net.hat.gt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("unused")
public class TradeWithoutEmeraldItemStack implements TradeOffers.Factory {
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
    public TradeWithoutEmeraldItemStack(ItemStack item, int $, ItemStack sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = ItemStack.EMPTY;
        this.secondPrice = 0;
        this.sell = sellItem;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    // This one is just for an undefined amount of uses
    public TradeWithoutEmeraldItemStack(ItemStack item, int $, ItemStack sellItem, int sellCount, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item;
        this.secondPrice = 0;
        this.sell = sellItem;
        this.sellCount = sellCount;
        this.maxUses = 1;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    // This one is if you want to have a second Item required for the trade.
    public TradeWithoutEmeraldItemStack(ItemStack item, int $, ItemStack item2, int sC, ItemStack sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.sell = sellItem;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }


    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        this.firstBuy.setCount(this.price);
        this.secondBuy.setCount(this.secondPrice);
        this.sell.setCount(this.sellCount);
        return new TradeOffer(this.firstBuy, this.secondBuy, this.sell, this.maxUses, this.experience, this.multiplier);
    }
}
