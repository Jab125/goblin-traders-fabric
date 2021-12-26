package net.hat.gt.init;

import com.jab125.thonkutil.api.CapeItem;
import com.jab125.thonkutil.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item GOBLIN_TRADER_SPAWN_EGG = new SpawnEggItem(ModEntities.GOBLIN_TRADER, 0x4da744, 0x316f5d, new Item.Settings().group(ItemGroup.MISC));
    public static final Item VEIN_GOBLIN_TRADER_SPAWN_EGG = new SpawnEggItem(ModEntities.VEIN_GOBLIN_TRADER, 0xf3982e, 0xf45b1f, new Item.Settings().group(ItemGroup.MISC));
    public static final Item APPLE_CAPE = Util.quickRegisterItem(new Identifier("goblintraders:apple_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("goblintraders", "goblin_trader_spawn_egg"), GOBLIN_TRADER_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier("goblintraders", "vein_goblin_trader_spawn_egg"), VEIN_GOBLIN_TRADER_SPAWN_EGG);
    }
}
