package com.jab125.util.tradehelper.type;

import com.google.gson.JsonObject;
import com.jab125.util.forgehelper.CraftingHelper;
import com.jab125.util.tradehelper.GoblinTrade;
import com.jab125.util.tradehelper.TradeSerializer;
import io.netty.util.internal.UnstableApi;
import net.hat.gt.GobT;
import net.hat.gt.datagen.GoblinTradeProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

/**
 * Use for simple enchantment trades
 */
@UnstableApi
public class EnchantedItemTrade implements ITradeType<GoblinTrade> {
    public static final Serializer SERIALIZER = new Serializer();

    private final ItemStack itemStack;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int experience;
    private final Enchantment enchantment;
    private final int payLevel;
    private final int sellLevel;
    private final boolean isRequired;

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public EnchantedItemTrade(ItemStack itemStack, float priceMultiplier, int maxTrades, int experience, Enchantment enchantment, int payLevel, int sellLevel, boolean isRequired) {
        this.payLevel = payLevel;
        this.sellLevel = sellLevel;
        this.itemStack = itemStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.experience = experience;
        this.enchantment = enchantment;
        this.isRequired = isRequired;
    }
    @Override
    public JsonObject serialize() {
        return SERIALIZER.serialize(this);
    }

    @Override
    public GoblinTrade createTradeOffer() {
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
        ItemStack sellItem = this.itemStack.copy();
        EnchantmentHelper.set(GoblinTradeProvider.toHashMap(new EnchantmentLevelEntry(this.enchantment, this.payLevel)), enchantedBook);
        EnchantmentHelper.set(GoblinTradeProvider.toHashMap(new EnchantmentLevelEntry(this.enchantment, this.sellLevel)), sellItem);

        return new GoblinTrade(sellItem, this.itemStack.copy(), enchantedBook, this.maxTrades, this.experience, this.priceMultiplier);
    }

    public static class Serializer extends TradeSerializer<EnchantedItemTrade> {

        Serializer() {
            super(new Identifier(GobT.MODID, "enchanted_item"));
        }

        @Override
        public EnchantedItemTrade deserialize(JsonObject object) {
            Builder builder = Builder.create();
            builder.setStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "item"), true));
            builder.setEnchantment(Registry.ENCHANTMENT.get(new Identifier(JsonHelper.getString(object, "enchantment", Registry.ENCHANTMENT.getId(Enchantments.UNBREAKING).toString()))));
            builder.setOfferLevel(JsonHelper.getInt(object, "offer_level", 1));
            builder.setPaymentLevel(JsonHelper.getInt(object, "payment_level", 1));
            //builder.setPotion(Registry.POTION.get(new Identifier(JsonHelper.getString(object, "potion", Registry.POTION.getId(Potions.AWKWARD).toString()))));
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setExperience(JsonHelper.getInt(object, "experience", 0));

            return builder.build();
        }

        @Override
        public JsonObject serialize(EnchantedItemTrade trade) {
            JsonObject object = super.serialize(trade);
            object.add("item", this.serializeItemStack(trade.itemStack));
            if(trade.priceMultiplier != 0.05F) {
                object.addProperty("price_multiplier", trade.priceMultiplier);
            }
            if(trade.maxTrades != 12) {
                object.addProperty("max_trades", trade.maxTrades);
            }
            if(trade.experience != 0) {
                object.addProperty("experience", trade.experience);
            }
            object.addProperty("enchantment", Registry.ENCHANTMENT.getId(trade.enchantment).toString());
            object.addProperty("offer_level", trade.sellLevel);
            object.addProperty("payment_level", trade.payLevel);
            return object;
        }

        private JsonObject serializeItemStack(ItemStack stack) {
            JsonObject object = new JsonObject();
            object.addProperty("item", Objects.requireNonNull(Registry.ITEM.getId(stack.getItem())).toString());
            object.addProperty("count", stack.getCount());
            if(stack.hasNbt())
            {
                object.addProperty("nbt", Objects.requireNonNull(stack.getNbt()).toString());
            }
            return object;
        }
    }
    public static class Builder {
        private ItemStack offerStack;
        private Enchantment enchantment;
        private int offerLevel;
        private int paymentLevel;
        private float priceMultiplier = 0.0F;
        private int maxTrades = 12;
        private int experience = 10;
        private boolean required = true;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public EnchantedItemTrade build() {
            return new EnchantedItemTrade(this.offerStack, this.priceMultiplier, this.maxTrades, this.experience, this.enchantment, this.paymentLevel, this.offerLevel, required);
        }

        public Builder setStack(ItemStack offerStack) {
            this.offerStack = offerStack;
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

        @Deprecated
        public Builder setPlayerExperience(int playerExperience) {
            return this;
        }
        public Builder setOfferLevel(int offerLevel) {
            this.offerLevel = offerLevel;
            return this;
        }

        public Builder setPaymentLevel(int paymentLevel) {
            this.paymentLevel = paymentLevel;
            return this;
        }
        public Builder setEnchantment(Enchantment enchantment) {
            this.enchantment = enchantment;
            return this;
        }

        public Builder setRequired(boolean required) {
            this.required = required;
            return this;
        }
    }
}
