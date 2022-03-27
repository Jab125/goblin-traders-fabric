package com.jab125.util.tradehelper.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jab125.util.forgehelper.CraftingHelper;
import com.jab125.util.tradehelper.TradeSerializer;
import com.jab125.util.tradehelper.UpgradedGoblinTrade;
import net.hat.gt.GobT;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UpgradedContainerTrade implements ITradeType<UpgradedGoblinTrade> {
    public static final Serializer SERIALIZER = new Serializer();
    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final ContainerTrade.SlotItem[] slotItems;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int merchantExperience;
    private final int playerExperience;
    private final boolean isRequired;

    public UpgradedContainerTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, float priceMultiplier, int maxTrades, int merchantExperience, int playerExperience, ContainerTrade.SlotItem[] slotItems, boolean isRequired) {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.merchantExperience = merchantExperience;
        this.playerExperience = playerExperience;
        this.slotItems = slotItems;
        this.isRequired = isRequired;
    }

    @Override
    public JsonObject serialize() {
        return SERIALIZER.serialize(this);
    }

    @Override
    public UpgradedGoblinTrade createTradeOffer() {
        ItemStack container = offerStack.copy();
        NbtList itemList = new NbtList();
        for (ContainerTrade.SlotItem slotItem : this.slotItems) {
            NbtCompound nbtCompound = container.getOrCreateNbt();
            NbtCompound itemCompound = new NbtCompound();
            writeNbt(itemCompound, slotItem);
            itemList.add(itemCompound);
            if (!nbtCompound.contains("BlockEntityTag")) {
                nbtCompound.put("BlockEntityTag", new NbtCompound());
            }
            NbtCompound blockEntityTag =  nbtCompound.getCompound("BlockEntityTag");
            if (!blockEntityTag.contains("Items")) {
                blockEntityTag.put("Items", itemList);
            }
        }

        return new UpgradedGoblinTrade(container, this.paymentStack.copy(), this.secondaryPaymentStack.copy(), this.maxTrades, this.merchantExperience, this.playerExperience, this.priceMultiplier);
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public NbtCompound writeNbt(NbtCompound nbt, ContainerTrade.SlotItem item) {
        Identifier identifier = Registry.ITEM.getId(item.getItemStack().getItem());
        nbt.putString("id", identifier == null ? "minecraft:air" : identifier.toString());
        nbt.putByte("Slot", (byte) item.getSlot());
        nbt.putByte("Count", (byte) item.getItemStack().getCount());
        if (item.getItemStack().getNbt() != null) {
            nbt.put("tag", item.getItemStack().getNbt().copy());
        }

        return nbt;
    }
    public static class Serializer extends TradeSerializer<UpgradedContainerTrade> {
        Serializer() {
            super(new Identifier(GobT.MODID, "upgraded_container"));
        }

        @Override
        public UpgradedContainerTrade deserialize(JsonObject object) {
            Builder builder = Builder.create();
            builder.setOfferStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "offer_item"), true));
            builder.setPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "payment_item"), true));
            if(JsonHelper.hasElement(object, "secondary_payment_item"))
            {
                builder.setSecondaryPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "secondary_payment_item"), true));
            }
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setMerchantExperience(JsonHelper.getInt(object, "merchant_experience", 0));
            builder.setPlayerExperience(JsonHelper.getInt(object, "player_experience", 0));
            if(JsonHelper.hasElement(object, "container_items"))
            {
                Collection<ContainerTrade.SlotItem> slotItems = this.getSlotItems(JsonHelper.getArray(object, "container_items"));
                for(ContainerTrade.SlotItem item : slotItems)
                {
                    builder.setContainerItem(item.getItemStack(), item.getSlot());
                }
            }
            return builder.build();
        }

        public Collection<ContainerTrade.SlotItem> getSlotItems(JsonArray slotArray) {
            List<ContainerTrade.SlotItem> slotItems = new ArrayList<>();
            for(JsonElement slotElement : slotArray)
            {
                JsonObject bundleObject = slotElement.getAsJsonObject();
                ContainerTrade.SlotItem itemStack = CraftingHelper.getSlotItem(bundleObject, true);
                slotItems.add(itemStack);
            }
            return slotItems;
        }

        @Override
        public JsonObject serialize(UpgradedContainerTrade trade)
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
            if(trade.merchantExperience != 0) {
                object.addProperty("merchant_experience", trade.merchantExperience);
            }
            if(trade.playerExperience != 0) {
                object.addProperty("player_experience", trade.playerExperience);
            }
            if(trade.slotItems.length > 0) {
                JsonArray itemsArray = new JsonArray();
                for (ContainerTrade.SlotItem slotItem : trade.slotItems) {
                    itemsArray.add(this.serializeSlotItem(slotItem));
                }
                object.add("container_items", itemsArray);
            }
//            if(trade.bundleItems.length > 0) {
//                JsonArray bundleArray = new JsonArray();
//                for(ItemStack bundleItem : trade.bundleItems)
//                {
//                    bundleArray.add(this.serializeItemStack(bundleItem));
//                }
//                object.add("bundle_items", bundleArray);
//            }
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

        private JsonObject serializeSlotItem(ContainerTrade.SlotItem stack) {
            JsonObject object = new JsonObject();
            object.addProperty("slot", stack.getSlot());
            object.addProperty("item", Objects.requireNonNull(Registry.ITEM.getId(stack.getItemStack().getItem())).toString());
            object.addProperty("count", stack.getItemStack().getCount());
            if(stack.getItemStack().hasNbt())
            {
                object.addProperty("nbt", Objects.requireNonNull(stack.getItemStack().getNbt()).toString());
            }
            return object;
        }
    }


    public static class Builder {
        private ItemStack offerStack;
        private ItemStack paymentStack;
        private ArrayList<ContainerTrade.SlotItem> slotItems = new ArrayList<>();
        private ItemStack secondaryPaymentStack = ItemStack.EMPTY;
        private float priceMultiplier = 0.0F;
        private int maxTrades = 12;
        private int merchantExperience = 10;
        private int playerExperience = 10;
        private boolean required = true;


        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public UpgradedContainerTrade build() {
            return new UpgradedContainerTrade(this.offerStack, this.paymentStack, this.secondaryPaymentStack, this.priceMultiplier, this.maxTrades, this.merchantExperience, this.playerExperience, this.slotItems.toArray(new ContainerTrade.SlotItem[0]), required);
        }

        public Builder setOfferStack(ItemStack offerStack) {
            if (offerStack.getItem() instanceof BlockItem) {
                if (((BlockItem) offerStack.getItem()).getBlock() instanceof BlockWithEntity) {
                    this.offerStack = offerStack;
                } else {
                    throw new RuntimeException("ItemStack need to be of minecraft:block_with_entity!");
                }
            } else {
                throw new RuntimeException("ItemStack need to be of minecraft:block_with_entity!");
            }
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
            this.merchantExperience = merchantExperience;
            return this;
        }

        public Builder setPlayerExperience(int playerExperience) {
            this.playerExperience = playerExperience;
            return this;
        }
        public Builder setContainerItem(ItemStack itemStack, int slot) {
            for (var slotItem : slotItems) {
                if (slot == slotItem.getSlot()) {
                    throw new RuntimeException("Tried to override existing slot with another item.");
                }
            }
            slotItems.add(new ContainerTrade.SlotItem(slot, itemStack));
            return this;
        }

        public Builder setRequired(boolean required) {
            this.required = required;
            return this;
        }
    }
}
