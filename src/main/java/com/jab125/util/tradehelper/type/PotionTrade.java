package com.jab125.util.tradehelper.type;

import com.google.gson.JsonObject;
import com.jab125.util.forgehelper.CraftingHelper;
import com.jab125.util.tradehelper.GoblinTrade;
import com.jab125.util.tradehelper.TradeSerializer;
import net.hat.gt.GobT;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.potion.Potions;
import net.minecraft.server.Main;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class PotionTrade implements ITradeType<GoblinTrade> {
    public static final Serializer SERIALIZER = new Serializer();

    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int experience;
    private final Potion potionType;

    public PotionTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, float priceMultiplier, int maxTrades, int experience, Potion potionType) {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.experience = experience;
        this.potionType = potionType;
    }
    @Override
    public JsonObject serialize() {
        return SERIALIZER.serialize(this);
    }

    @Override
    public GoblinTrade createTradeOffer() {
        ItemStack potion = offerStack.copy();
        PotionUtil.setPotion(potion, potionType);
        return new GoblinTrade(potion.copy(), this.paymentStack.copy(), this.secondaryPaymentStack.copy(), this.maxTrades, this.experience, this.priceMultiplier);
    }

    public static class Serializer extends TradeSerializer<PotionTrade> {

        Serializer() {
            super(new Identifier(GobT.MODID, "potion"));
        }

        @Override
        public PotionTrade deserialize(JsonObject object) {
            Builder builder = Builder.create();
            builder.setOfferStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "offer_item"), true));
            builder.setPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "payment_item"), true));
            if(JsonHelper.hasElement(object, "secondary_payment_item")) {
                builder.setSecondaryPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "secondary_payment_item"), true));
            }
            builder.setPotion(Registry.POTION.get(new Identifier(JsonHelper.getString(object, "potion", Registry.POTION.getId(Potions.AWKWARD).toString()))));
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setExperience(JsonHelper.getInt(object, "experience", 0));

            return builder.build();
        }

        @Override
        public JsonObject serialize(PotionTrade trade) {
            JsonObject object = super.serialize(trade);
            object.add("offer_item", this.serializeItemStack(trade.offerStack));
            object.add("payment_item", this.serializeItemStack(trade.paymentStack));
            if(!trade.secondaryPaymentStack.isEmpty()) {
                object.add("secondary_payment_item", this.serializeItemStack(trade.secondaryPaymentStack));
            }
            if(trade.priceMultiplier != 0.05F) {
                object.addProperty("price_multiplier", trade.priceMultiplier);
            }
            if(trade.maxTrades != 12) {
                object.addProperty("max_trades", trade.maxTrades);
            }
            if(trade.experience != 0) {
                object.addProperty("experience", trade.experience);
            }
            if(trade.potionType != null) {
                object.addProperty("potion", serializePotion(trade.potionType));
            }
            return object;
        }

        private JsonObject serializeItemStack(ItemStack stack) {
            JsonObject object = new JsonObject();
            object.addProperty("item", Objects.requireNonNull(Registry.ITEM.getId(stack.getItem())).toString());
            object.addProperty("count", stack.getCount());
            if(stack.hasTag())
            {
                object.addProperty("nbt", Objects.requireNonNull(stack.getTag()).toString());
            }
            return object;
        }

        private String serializePotion(Potion potion) {
            return Registry.POTION.getId(potion).toString();
        }
    }
    public static class Builder {
        private ItemStack offerStack;
        private ItemStack paymentStack;
        private ItemStack secondaryPaymentStack = ItemStack.EMPTY;
        private Potion potion;
        private float priceMultiplier = 0.0F;
        private int maxTrades = 12;
        private int experience = 10;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public PotionTrade build() {
            return new PotionTrade(this.offerStack, this.paymentStack, this.secondaryPaymentStack, this.priceMultiplier, this.maxTrades, this.experience, this.potion);
        }

        public Builder setOfferStack(ItemStack offerStack) {
            if (!(offerStack.getItem() instanceof PotionItem)) {
                throw new RuntimeException("ItemStack need to be of minecraft:potion!");
            }
            this.offerStack = offerStack;
            return this;
        }

        public Builder setPaymentStack(ItemStack paymentStack) {
            this.paymentStack = paymentStack;
            return this;
        }

        public Builder setSecondaryPaymentStack(ItemStack secondaryPaymentStack) {
            this.secondaryPaymentStack = secondaryPaymentStack;
            return this;
        }

        public Builder setPriceMultiplier(float priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
            return this;
        }

        public Builder setMaxTrades(int maxTrades) {
            this.maxTrades = maxTrades;
            return this;
        }

        @Deprecated
        public Builder setExperience(int experience) {
            return this.setMerchantExperience(experience);
        }

        public Builder setMerchantExperience(int merchantExperience) {
            this.experience = merchantExperience;
            return this;
        }

        public Builder setPlayerExperience(int playerExperience) {
            return this;
        }

        public Builder setPotion(Potion potion) {
            this.potion = potion;
            return this;
        }
    }
}
