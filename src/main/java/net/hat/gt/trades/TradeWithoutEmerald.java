package net.hat.gt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class TradeWithoutEmerald implements TradeOffers.Factory {
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
