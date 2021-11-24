package com.jab125.util.tradehelper.type;

import com.google.gson.JsonObject;
import com.jab125.util.forgehelper.CraftingHelper;
import com.jab125.util.tradehelper.TradeSerializer;
import com.jab125.util.tradehelper.UpgradedGoblinTrade;
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
public class UpgradedEnchantedItemTrade implements ITradeType<UpgradedGoblinTrade> {
    public static final Serializer SERIALIZER = new Serializer();

    private final ItemStack itemStack;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int merchantExperience;
    private final int playerExperience;
    private final Enchantment enchantment;
    private final int payLevel;
    private final int sellLevel;

    public UpgradedEnchantedItemTrade(ItemStack itemStack, float priceMultiplier, int maxTrades, int merchantExperience, int playerExperience, Enchantment enchantment, int payLevel, int sellLevel) {
        this.payLevel = payLevel;
        this.sellLevel = sellLevel;
        this.itemStack = itemStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.merchantExperience = merchantExperience;
        this.playerExperience = playerExperience;
        this.enchantment = enchantment;
    }
    @Override
    public JsonObject serialize() {
        return SERIALIZER.serialize(this);
    }

    @Override
    public UpgradedGoblinTrade createTradeOffer() {
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
        ItemStack sellItem = this.itemStack.copy();
        EnchantmentHelper.set(GoblinTradeProvider.toHashMap(new EnchantmentLevelEntry(this.enchantment, this.payLevel)), enchantedBook);
        EnchantmentHelper.set(GoblinTradeProvider.toHashMap(new EnchantmentLevelEntry(this.enchantment, this.sellLevel)), sellItem);

        return new UpgradedGoblinTrade(sellItem, this.itemStack.copy(), enchantedBook, this.maxTrades, this.merchantExperience, this.playerExperience, this.priceMultiplier);
    }

    public static class Serializer extends TradeSerializer<UpgradedEnchantedItemTrade> {

        Serializer() {
            super(new Identifier(GobT.MODID, "enchanted_item"));
        }

        @Override
        public UpgradedEnchantedItemTrade deserialize(JsonObject object) {
            Builder builder = Builder.create();
            builder.setStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "item"), true));
            builder.setEnchantment(Registry.ENCHANTMENT.get(new Identifier(JsonHelper.getString(object, "enchantment", Registry.ENCHANTMENT.getId(Enchantments.UNBREAKING).toString()))));
            builder.setOfferLevel(JsonHelper.getInt(object, "offer_level", 1));
            builder.setPaymentLevel(JsonHelper.getInt(object, "payment_level", 1));
            //builder.setPotion(Registry.POTION.get(new Identifier(JsonHelper.getString(object, "potion", Registry.POTION.getId(Potions.AWKWARD).toString()))));
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setMerchantExperience(JsonHelper.getInt(object, "merchant_experience", 0));
            builder.setPlayerExperience(JsonHelper.getInt(object, "player_experience", 0));

            return builder.build();
        }

        @Override
        public JsonObject serialize(UpgradedEnchantedItemTrade trade) {
            JsonObject object = super.serialize(trade);
            object.add("item", this.serializeItemStack(trade.itemStack));
            if(trade.priceMultiplier != 0.05F) {
                object.addProperty("price_multiplier", trade.priceMultiplier);
            }
            if(trade.maxTrades != 12) {
                object.addProperty("max_trades", trade.maxTrades);
            }
            if(trade.merchantExperience != 0) {
                object.addProperty("merchant_experience", trade.merchantExperience);
            }
            if(trade.playerExperience != 0) {
                object.addProperty("player_experience", trade.playerExperience);
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
        private int merchantExperience = 10;
        private int playerExperience = 10;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public UpgradedEnchantedItemTrade build() {
            return new UpgradedEnchantedItemTrade(this.offerStack, this.priceMultiplier, this.maxTrades, this.merchantExperience, this.playerExperience, this.enchantment, this.paymentLevel, this.offerLevel);
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
        public Builder setExperience(int merchantExperience) {
            return this.setMerchantExperience(merchantExperience);
        }

        public Builder setMerchantExperience(int merchantExperience) {
            this.merchantExperience = merchantExperience;
            return this;
        }

        public Builder setPlayerExperience(int playerExperience) {
            this.playerExperience = playerExperience;
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
    }
}
