package net.hat.gt.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item GOBLIN_TRADER_SPAWN_EGG = new SpawnEggItem(ModEntities.GOBLIN_TRADER, 0x4da744, 0x316f5d, new Item.Settings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("goblintraders", "goblin_trader_spawn_egg"), GOBLIN_TRADER_SPAWN_EGG);
    }
}
