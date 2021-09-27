package net.hat.gt;

import net.fabricmc.api.ModInitializer;
import net.hat.gt.register.*;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";

    @Override
    public void onInitialize() {
        ModSounds.registerSounds();
    }
}
