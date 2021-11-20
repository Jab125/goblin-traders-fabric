package com.jab125.util.tradehelper.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jab125.util.forgehelper.CraftingHelper;
import com.jab125.util.tradehelper.GoblinTrade;
import com.jab125.util.tradehelper.TradeSerializer;
import net.hat.gt.GobT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
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
 * {@link com.jab125.util.tradehelper.type.BundleTrade} is a trade type to easily add trades with {@link net.minecraft.item.Items} in {@link net.minecraft.item.Items#BUNDLE}{@code S}
 * <p>
 * If you try to overfill a bundle, it will trim the last {@link net.minecraft.item.Items} added off the {@link net.minecraft.item.Items#BUNDLE}
 * <p>
 * @see net.minecraft.item.BundleItem#addToBundle(ItemStack, ItemStack)
 * @see com.jab125.util.tradehelper.type.ITradeType
 * @see com.jab125.util.tradehelper.type.BasicTrade
 * @see com.jab125.util.tradehelper.type.BundleTrade#createTradeOffer()
 */
public class BundleTrade implements ITradeType<GoblinTrade> {

    public static final Serializer SERIALIZER = new Serializer();

    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int experience;
    private final ItemStack[] bundleItems;


    public BundleTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, float priceMultiplier, int maxTrades, int experience, ItemStack[] bundleItems)
    {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.experience = experience;
        this.bundleItems = bundleItems;
    }

    @Override
    public JsonObject serialize() {
        return SERIALIZER.serialize(this);
    }

    @Override
    public GoblinTrade createTradeOffer() {
        ItemStack bundle = offerStack.copy();
        for (ItemStack itemInBundle : bundleItems) {
            BundleItem.addToBundle(bundle, itemInBundle);
        }

        return new GoblinTrade(bundle, this.paymentStack.copy(), this.secondaryPaymentStack.copy(), this.maxTrades, this.experience, this.priceMultiplier);
    }

    public static class Serializer extends TradeSerializer<BundleTrade> {
        Serializer() {
            super(new Identifier(GobT.MODID, "bundle"));
        }

        @Override
        public JsonObject serialize(BundleTrade trade)
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
            if(trade.experience != 0)
            {
                object.addProperty("experience", trade.experience);
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
        public BundleTrade deserialize(JsonObject object)
        {
            BundleTrade.Builder builder = BundleTrade.Builder.create();
            builder.setOfferStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "offer_item"), true));
            builder.setPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "payment_item"), true));
            if(JsonHelper.hasElement(object, "secondary_payment_item"))
            {
                builder.setSecondaryPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "secondary_payment_item"), true));
            }
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setExperience(JsonHelper.getInt(object, "experience", 0));
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
        private int experience = 10;


        private Builder() {
        }

        public static BundleTrade.Builder create() {
            return new BundleTrade.Builder();
        }

        public BundleTrade build() {
            return new BundleTrade(this.offerStack, this.paymentStack, this.secondaryPaymentStack, this.priceMultiplier, this.maxTrades, this.experience, this.bundleItems.toArray(ItemStack[]::new));
        }

        public BundleTrade.Builder setOfferStack(ItemStack offerStack) {
            if (!offerStack.isOf(Items.BUNDLE)) {
                throw new RuntimeException("ItemStack need to be of minecraft:bundle!");
            }
            this.offerStack = offerStack;
            return this;
        }

        public BundleTrade.Builder setPaymentStack(ItemStack paymentStack) {
            this.paymentStack = paymentStack;
            return this;
        }

        public BundleTrade.Builder setSecondaryPaymentStack(ItemStack secondaryPaymentStack) {
            this.secondaryPaymentStack = secondaryPaymentStack;
            return this;
        }

        public BundleTrade.Builder setPriceMultiplier(float priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
            return this;
        }

        public BundleTrade.Builder setMaxTrades(int maxTrades) {
            this.maxTrades = maxTrades;
            return this;
        }

        @Deprecated
        public BundleTrade.Builder setExperience(int experience) {
            return this.setMerchantExperience(experience);
        }

        public BundleTrade.Builder setMerchantExperience(int merchantExperience) {
            this.experience = merchantExperience;
            return this;
        }

        public BundleTrade.Builder setPlayerExperience(int playerExperience) {
            return this;
        }
        public BundleTrade.Builder addToBundle(ItemStack itemStack) {
            bundleItems.add(itemStack);
            return this;
        }
    }
}
