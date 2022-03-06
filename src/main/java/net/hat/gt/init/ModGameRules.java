package net.hat.gt.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGameRules {

    public static final GameRules.Key<GameRules.BooleanRule> GOBLIN_TRADERS_PICK_UP_FOODS =
            GameRuleRegistry.register("goblintraders:goblinTradersPickUpFoods", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

    public static void registerGameRules() {

    }
}
