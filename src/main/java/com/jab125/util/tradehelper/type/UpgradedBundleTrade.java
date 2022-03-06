package com.jab125.util.tradehelper.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jab125.util.forgehelper.CraftingHelper;
import com.jab125.util.tradehelper.TradeSerializer;
import com.jab125.util.tradehelper.UpgradedGoblinTrade;
import net.hat.gt.GobT;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * {@link UpgradedBundleTrade} is a trade type to easily add trades with {@link Items} in {@link Items#BUNDLE}{@code S}
 * <p>
 * If you try to overfill a bundle, it will trim the last {@link net.minecraft.item.Item} added off the {@link Items#BUNDLE}
 * <p>
 * @see BundleItem#addToBundle(ItemStack, ItemStack)
 * @see ITradeType
 * @see BasicTrade
 * @see UpgradedBundleTrade#createTradeOffer()
 */
public class UpgradedBundleTrade implements ITradeType<UpgradedGoblinTrade> {

    public static final Serializer SERIALIZER = new Serializer();

    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int merchantExperience;
    private final int playerExperience;
    private final ItemStack[] bundleItems;
    private final boolean isRequired;

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public UpgradedBundleTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, float priceMultiplier, int maxTrades, int merchantExperience, int playerExperience, ItemStack[] bundleItems, boolean isRequired)
    {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.merchantExperience = merchantExperience;
        this.playerExperience = playerExperience;
        this.bundleItems = bundleItems;
        this.isRequired = isRequired;
    }

    @Override
    public JsonObject serialize() {
        return SERIALIZER.serialize(this);
    }

    @Override
    public UpgradedGoblinTrade createTradeOffer() {
        ItemStack bundle = offerStack.copy();
        for (ItemStack itemInBundle : bundleItems) {
            BundleItem.addToBundle(bundle, itemInBundle);
        }

        return new UpgradedGoblinTrade(bundle, this.paymentStack.copy(), this.secondaryPaymentStack.copy(), this.maxTrades, this.merchantExperience, this.playerExperience, this.priceMultiplier);
    }

    public static class Serializer extends TradeSerializer<UpgradedBundleTrade> {
        Serializer() {
            super(new Identifier(GobT.MODID, "upgraded_bundle"));
        }

        @Override
        public JsonObject serialize(UpgradedBundleTrade trade)
        {
            JsonObject object = super.serialize(trade);
            object.add("offer_item", this.serializeItemStack(trade.offerStack));
            object.add("payment_item", this.serializeItemStack(trade.paymentStack));
            if(!trade.secondaryPaymentStack.isEmpty())
            {
                object.add("secondary_payment_item", this.serializeItemStack(trade.secondaryPaymentStack));
            }
            if(trade.priceMultiplier != 0.05F)
            {
                object.addProperty("price_multiplier", trade.priceMultiplier);
            }
            if(trade.maxTrades != 12)
            {
                object.addProperty("max_trades", trade.maxTrades);
            }
            if(trade.merchantExperience != 0)
            {
                object.addProperty("merchant_experience", trade.merchantExperience);
            }
            if(trade.playerExperience != 0)
            {
                object.addProperty("player_experience", trade.playerExperience);
            }
            if(trade.bundleItems.length > 0)
            {
                JsonArray bundleArray = new JsonArray();
                for(ItemStack bundleItem : trade.bundleItems)
                {
                    bundleArray.add(this.serializeItemStack(bundleItem));
                }
                object.add("bundle_items", bundleArray);
            }
            return object;
        }
        
        @Override
        public UpgradedBundleTrade deserialize(JsonObject object)
        {
            UpgradedBundleTrade.Builder builder = UpgradedBundleTrade.Builder.create();
            builder.setOfferStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "offer_item"), true));
            builder.setPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "payment_item"), true));
            if(JsonHelper.hasElement(object, "secondary_payment_item"))
            {
                builder.setSecondaryPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "secondary_payment_item"), true));
            }
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setMerchantExperience(JsonHelper.getInt(object, "player_experience", 0));
            builder.setMerchantExperience(JsonHelper.getInt(object, "merchant_experience", 0));
            if(JsonHelper.hasElement(object, "bundle_items"))
            {
                Collection<ItemStack> bundleItems = this.getBundleItems(JsonHelper.getArray(object, "bundle_items"));
                for(ItemStack bundleItem : bundleItems)
                {
                    builder.addToBundle(bundleItem);
                }
            }
            return builder.build();
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

        private Collection<ItemStack> getBundleItems(JsonArray bundleArray)
        {
            List<ItemStack> bundleItems = new ArrayList<>();
            for(JsonElement bundleElement : bundleArray)
            {
                JsonObject bundleObject = bundleElement.getAsJsonObject();
                ItemStack itemStack = CraftingHelper.getItemStack(bundleObject, true);
                bundleItems.add(itemStack);
            }
            return bundleItems;
        }
    }

    public static class Builder {
        private ItemStack offerStack;
        private ItemStack paymentStack;
        private ItemStack secondaryPaymentStack = ItemStack.EMPTY;
        private ArrayList<ItemStack> bundleItems = new ArrayList<>();
        private float priceMultiplier = 0.0F;
        private int maxTrades = 12;
        private int merchantExperience = 10;
        private int playerExperience = 10;
        private boolean required = true;


        private Builder() {
        }

        public static UpgradedBundleTrade.Builder create() {
            return new UpgradedBundleTrade.Builder();
        }

        public UpgradedBundleTrade build() {
            return new UpgradedBundleTrade(this.offerStack, this.paymentStack, this.secondaryPaymentStack, this.priceMultiplier, this.maxTrades, this.merchantExperience, this.playerExperience, this.bundleItems.toArray(ItemStack[]::new), required);
        }

        public UpgradedBundleTrade.Builder setOfferStack(ItemStack offerStack) {
            if (!offerStack.isOf(Items.BUNDLE)) {
                throw new RuntimeException("ItemStack need to be of minecraft:bundle!");
            }
            this.offerStack = offerStack;
            return this;
        }

        public UpgradedBundleTrade.Builder setPaymentStack(ItemStack paymentStack) {
            this.paymentStack = paymentStack;
            return this;
        }

        public UpgradedBundleTrade.Builder setSecondaryPaymentStack(ItemStack secondaryPaymentStack) {
            this.secondaryPaymentStack = secondaryPaymentStack;
            return this;
        }

        public UpgradedBundleTrade.Builder setPriceMultiplier(float priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
            return this;
        }

        public UpgradedBundleTrade.Builder setMaxTrades(int maxTrades) {
            this.maxTrades = maxTrades;
            return this;
        }

        @Deprecated
        public UpgradedBundleTrade.Builder setExperience(int merchantExperience) {
            return this.setMerchantExperience(merchantExperience);
        }

        public UpgradedBundleTrade.Builder setMerchantExperience(int merchantExperience) {
            this.merchantExperience = merchantExperience;
            return this;
        }

        public UpgradedBundleTrade.Builder setPlayerExperience(int playerExperience) {
            this.playerExperience = playerExperience;
            return this;
        }
        public UpgradedBundleTrade.Builder addToBundle(ItemStack itemStack) {
            bundleItems.add(itemStack);
            return this;
        }

        public Builder setRequired(boolean required) {
            this.required = required;
            return this;
        }
    }
}
