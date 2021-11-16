package net.hat.gt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.hat.gt.GobT;


@Config(name = GobT.MODID)
public class GoblinTradersConfig implements ConfigData {
    // Settings
    public boolean MAX_ENCHANTMENT_TEXT = true;


    @ConfigEntry.Gui.Excluded
    public int GOBLIN_SPAWN_RATE_D = 100;
    @ConfigEntry.Gui.Excluded
    public int VEIN_GOBLIN_SPAWN_RATE_D = 1;
    public boolean EASTER_EGGS = false;

    @ConfigEntry.Category("all_goblin_traders_config")
    @ConfigEntry.Gui.TransitiveObject
    public AllGoblinTraders ALL_GOBLIN_TRADERS_CONFIG = new AllGoblinTraders();
    @ConfigEntry.Category("goblin_trader_config")
    @ConfigEntry.Gui.TransitiveObject
    public GoblinTrader GOBLIN_TRADER_CONFIG = new GoblinTrader();
    @ConfigEntry.Category("vein_goblin_trader_config")
    @ConfigEntry.Gui.TransitiveObject
    public VeinGoblinTrader VEIN_GOBLIN_TRADER_CONFIG = new VeinGoblinTrader();

    public static class AllGoblinTraders {
        public boolean GOBLINS_FALL = true;
        public boolean GOBLIN_NO_ATTACK_CREATIVE = true;
        public boolean CAN_GET_KNOCKED_OUT = false;
    }

    public static class GoblinTrader {
        public boolean GOBLIN_HIT_BACK = true;
        public int GOBLIN_TRADER_SPAWN_CHANCE = 25;
        public int GOBLIN_TRADER_SPAWN_DELAY = 24000;
        public int GOBLIN_TRADER_MAX_SPAWN_HEIGHT = 63;
        public int GOBLIN_TRADER_MIN_SPAWN_HEIGHT_1_17 = 0;
        @ConfigEntry.Gui.Excluded
        public int GOBLIN_TRADER_MIN_SPAWN_HEIGHT = -64;
    }

    public static class VeinGoblinTrader {
        public boolean VEIN_GOBLIN_HIT_BACK = true;
        public int VEIN_GOBLIN_TRADER_SPAWN_CHANCE = 25;
        public int VEIN_GOBLIN_TRADER_SPAWN_DELAY = 24000;
        public int VEIN_GOBLIN_TRADER_MAX_SPAWN_HEIGHT = 127;
        public int VEIN_GOBLIN_TRADER_MIN_SPAWN_HEIGHT = 0;
        public boolean VEIN_GOBLINS_DIE_IN_WATER = false;
    }
}
