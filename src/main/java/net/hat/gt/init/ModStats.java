package net.hat.gt.init;

import net.hat.gt.GobT;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStats {

    public static final Identifier TALK_WITH_GOBLIN = new Identifier(GobT.MODID,"talk_with_goblin");
    public static final Identifier TRADE_WITH_GOBLIN = new Identifier(GobT.MODID,"trade_with_goblin");

    public static void registerStats()
    {
        Registry.register(Registry.CUSTOM_STAT, "trade_with_goblin", TRADE_WITH_GOBLIN);Stats.CUSTOM.getOrCreateStat(TRADE_WITH_GOBLIN, StatFormatter.DEFAULT);
        Registry.register(Registry.CUSTOM_STAT, "talk_with_goblin", TALK_WITH_GOBLIN);Stats.CUSTOM.getOrCreateStat(TALK_WITH_GOBLIN, StatFormatter.DEFAULT);

    }

}
