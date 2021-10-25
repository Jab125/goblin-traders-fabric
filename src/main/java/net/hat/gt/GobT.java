package net.hat.gt;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.hat.gt.config.GoblinTradersConfig;
import net.hat.gt.init.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class GobT implements ModInitializer {

    public static final String MODID = "goblintraders";
    public static GoblinTradersConfig config;
    public static final GameRules.Key<GameRules.BooleanRule> GOBLIN_TRADERS_PICK_UP_APPLES =
            GameRuleRegistry.register("goblinTradersPickUpApples", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {
        AutoConfig.register(GoblinTradersConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(GoblinTradersConfig.class).getConfig();


        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModStats.registerStats();
        ModPotions.registerPotions();
        ModPotions.registerPotionRecipes();
        ModSpawns.init();
    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
