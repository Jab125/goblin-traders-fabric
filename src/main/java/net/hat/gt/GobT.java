package net.hat.gt;

import net.fabricmc.api.ModInitializer;
import net.hat.gt.init.ModEntities;
import net.hat.gt.init.ModItems;
import net.hat.gt.init.ModSounds;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";

    @Override
    public void onInitialize() {
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModItems.registerItems();
    }
}
