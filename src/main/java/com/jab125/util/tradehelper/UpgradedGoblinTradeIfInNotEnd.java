package com.jab125.util.tradehelper;

import net.hat.gt.trades.UpgradedTradeOffer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public record UpgradedGoblinTradeIfInNotEnd(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, int maxUses, int merchantExperience, int playerExperience, float priceMultiplier) implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        if (!entity.getEntityWorld().getDimension().equals(DimensionType.THE_END)) return null;
        return new UpgradedTradeOffer(this.paymentStack, this.secondaryPaymentStack, this.offerStack, this.maxUses, this.merchantExperience, this.priceMultiplier, this.playerExperience);
    }
}
