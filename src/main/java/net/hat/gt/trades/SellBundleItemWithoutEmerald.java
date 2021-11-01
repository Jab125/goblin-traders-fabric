package net.hat.gt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static net.minecraft.item.BundleItem.*;

public class SellBundleItemWithoutEmerald implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final ItemStack secondBuy;
    private final int secondPrice;
    private final int price;
    private final ItemStack sell;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;
    private final ItemStack[] itemsInBundle;

    public SellBundleItemWithoutEmerald(ItemStack item, int price, ItemStack[] itemsInBundle, int maxUses, int experience) {
        this.firstBuy = item;
        this.price = price;
        this.secondBuy = ItemStack.EMPTY;
        this.secondPrice = 0;
        this.sell = new ItemStack(Items.BUNDLE);
        this.itemsInBundle = itemsInBundle;
        this.sellCount = 1;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        this.firstBuy.setCount(price);
        this.secondBuy.setCount(secondPrice);
        this.sell.setCount(1);
        ItemStack newBundle = this.sell.copy();
        for (ItemStack item : itemsInBundle) {
            addToBundle(newBundle, item);
        }
        return new TradeOffer(this.firstBuy, this.secondBuy, newBundle, this.maxUses, this.experience, this.multiplier);
    }
}
