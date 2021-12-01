package com.jab125.util.tradehelper;

import net.hat.gt.trades.UpgradedTradeOffer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public final class UpgradedGoblinTrade implements TradeOffers.Factory {
    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final int maxUses;
    private final int merchantExperience;
    private final int playerExperience;
    private final float priceMultiplier;

    public UpgradedGoblinTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, int maxUses, int merchantExperience, int playerExperience, float priceMultiplier) {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.maxUses = maxUses;
        this.merchantExperience = merchantExperience;
        this.playerExperience = playerExperience;
        this.priceMultiplier = priceMultiplier;
    }

    public ItemStack offerStack() {
        return offerStack;
    }

    public ItemStack paymentStack() {
        return paymentStack;
    }

    public ItemStack secondaryPaymentStack() {
        return secondaryPaymentStack;
    }

    public int maxUses() {
        return maxUses;
    }

    public int merchantExperience() {
        return merchantExperience;
    }

    public int playerExperience() {
        return playerExperience;
    }

    public float priceMultiplier() {
        return priceMultiplier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        UpgradedGoblinTrade that = (UpgradedGoblinTrade) obj;
        return Objects.equals(this.offerStack, that.offerStack) &&
                Objects.equals(this.paymentStack, that.paymentStack) &&
                Objects.equals(this.secondaryPaymentStack, that.secondaryPaymentStack) &&
                this.maxUses == that.maxUses &&
                this.merchantExperience == that.merchantExperience &&
                this.playerExperience == that.playerExperience &&
                Float.floatToIntBits(this.priceMultiplier) == Float.floatToIntBits(that.priceMultiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerStack, paymentStack, secondaryPaymentStack, maxUses, merchantExperience, playerExperience, priceMultiplier);
    }

    @Override
    public String toString() {
        return "UpgradedGoblinTrade[" +
                "offerStack=" + offerStack + ", " +
                "paymentStack=" + paymentStack + ", " +
                "secondaryPaymentStack=" + secondaryPaymentStack + ", " +
                "maxUses=" + maxUses + ", " +
                "merchantExperience=" + merchantExperience + ", " +
                "playerExperience=" + playerExperience + ", " +
                "priceMultiplier=" + priceMultiplier + ']';
    }

    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        return new UpgradedTradeOffer(this.paymentStack, this.secondaryPaymentStack, this.offerStack, this.maxUses, this.merchantExperience, this.priceMultiplier, this.playerExperience);
    }
}
