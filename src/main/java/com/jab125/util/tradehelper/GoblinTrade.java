package com.jab125.util.tradehelper;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

/**
 * Remapped by Jab125
 */
public final class GoblinTrade implements TradeOffers.Factory {
    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final int maxUses;
    private final int experience;
    private final float priceMultiplier;

    public GoblinTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, int maxUses, int experience, float priceMultiplier) {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.maxUses = maxUses;
        this.experience = experience;
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

    public int experience() {
        return experience;
    }

    public float priceMultiplier() {
        return priceMultiplier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        GoblinTrade that = (GoblinTrade) obj;
        return Objects.equals(this.offerStack, that.offerStack) &&
                Objects.equals(this.paymentStack, that.paymentStack) &&
                Objects.equals(this.secondaryPaymentStack, that.secondaryPaymentStack) &&
                this.maxUses == that.maxUses &&
                this.experience == that.experience &&
                Float.floatToIntBits(this.priceMultiplier) == Float.floatToIntBits(that.priceMultiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerStack, paymentStack, secondaryPaymentStack, maxUses, experience, priceMultiplier);
    }

    @Override
    public String toString() {
        return "GoblinTrade[" +
                "offerStack=" + offerStack + ", " +
                "paymentStack=" + paymentStack + ", " +
                "secondaryPaymentStack=" + secondaryPaymentStack + ", " +
                "maxUses=" + maxUses + ", " +
                "experience=" + experience + ", " +
                "priceMultiplier=" + priceMultiplier + ']';
    }

    @Nullable
    @Override
    public TradeOffer create(Entity trader, Random rand) {
        return new TradeOffer(this.paymentStack, this.secondaryPaymentStack, this.offerStack, this.maxUses, this.experience, this.priceMultiplier);
    }
}
