package net.hat.gt.trades;

import net.hat.gt.init.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * For the {@code Potion-ID's} refer to {@link ModPotions}.
 * <p>Add Potions and duration into ModPotions.
 * Use accordingly, also change {@code LANG} file
 */
public class TradeWithoutEmeraldPotions implements TradeOffers.Factory {
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
