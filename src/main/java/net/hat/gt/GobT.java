package net.hat.gt;

import net.fabricmc.api.ModInitializer;
import net.hat.gt.init.*;
import net.minecraft.util.Identifier;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";

    @Override
    public void onInitialize() {
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModStats.registerStats();
    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
