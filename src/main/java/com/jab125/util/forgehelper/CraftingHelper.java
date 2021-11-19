package com.jab125.util.forgehelper;

import com.google.gson.*;
import com.jab125.util.tradehelper.type.ContainerTrade;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

/**
 * Author: Jab125
 * Got it from forge's sources
 * Remapped by Jab125
 */
public class CraftingHelper {
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static ItemStack getItemStack(JsonObject json, boolean readNBT) {
        String itemName = JsonHelper.getString(json, "item");

        Item item = Registry.ITEM.get(new Identifier(itemName));

        if (item == null)
            throw new JsonSyntaxException("Unknown item '" + itemName + "'");

        if (readNBT && json.has("nbt"))
        {
            // Lets hope this works? Needs test
            try
            {
                JsonElement element = json.get("nbt");
                NbtCompound nbt;
                if(element.isJsonObject())
                    nbt = StringNbtReader.parse(GSON.toJson(element));
                else
                    nbt = StringNbtReader.parse(JsonHelper.asString(element, "nbt"));

                NbtCompound tmp = new NbtCompound();
                if (nbt.contains("ForgeCaps"))
                {
                    tmp.put("ForgeCaps", nbt.get("ForgeCaps"));
                    nbt.remove("ForgeCaps");
                }

                tmp.put("tag", nbt);
                tmp.putString("id", itemName);
                tmp.putInt("Count", JsonHelper.getInt(json, "count", 1));

                return ItemStack.fromNbt(tmp);
            }
            catch (CommandSyntaxException e)
            {
                throw new JsonSyntaxException("Invalid NBT Entry: " + e.toString());
            }
        }

        return new ItemStack(item, JsonHelper.getInt(json, "count", 1));
    }

    public static ContainerTrade.SlotItem getSlotItem(JsonObject json, boolean readNBT) {
        String itemName = JsonHelper.getString(json, "item");

        Item item = Registry.ITEM.get(new Identifier(itemName));
        int slot = JsonHelper.getInt(json, "slot");

        if (item == null)
            throw new JsonSyntaxException("Unknown item '" + itemName + "'");

        if (readNBT && json.has("nbt"))
        {
            // Lets hope this works? Needs test
            try
            {
                JsonElement element = json.get("nbt");
                NbtCompound nbt;
                if(element.isJsonObject())
                    nbt = StringNbtReader.parse(GSON.toJson(element));
                else
                    nbt = StringNbtReader.parse(JsonHelper.asString(element, "nbt"));

                NbtCompound tmp = new NbtCompound();
                if (nbt.contains("ForgeCaps"))
                {
                    tmp.put("ForgeCaps", nbt.get("ForgeCaps"));
                    nbt.remove("ForgeCaps");
                }

                tmp.put("tag", nbt);
                tmp.putString("id", itemName);
                tmp.putInt("Count", JsonHelper.getInt(json, "count", 1));

                return new ContainerTrade.SlotItem(slot, ItemStack.fromNbt(tmp));
            }
            catch (CommandSyntaxException e)
            {
                throw new JsonSyntaxException("Invalid NBT Entry: " + e.toString());
            }
        }

        return new ContainerTrade.SlotItem(slot, new ItemStack(item, JsonHelper.getInt(json, "count", 1)));
    }
}
