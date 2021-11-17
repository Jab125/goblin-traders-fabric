package net.hat.gt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.hat.gt.GobT;


@Config(name = GobT.MODID)
public class GoblinTradersConfig implements ConfigData {
    // Settings
    public boolean MAX_ENCHANTMENT_TEXT = true;

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
        public boolean FALL = true;
        public boolean NO_ATTACK_CREATIVE = true;
        public boolean CAN_GET_KNOCKED_OUT = false;
    }

    public static class GoblinTrader {
        public boolean CAN_SPAWN = true;
        public boolean HIT_BACK = true;
        public int SPAWN_CHANCE = 25;
        public int SPAWN_DELAY = 24000;
        public int MAX_SPAWN_HEIGHT = 63;
        public int MIN_SPAWN_HEIGHT = -64;
    }

    public static class VeinGoblinTrader {
        public boolean CAN_SPAWN = true;
        public boolean HIT_BACK = true;
        public int SPAWN_CHANCE = 25;
        public int SPAWN_DELAY = 24000;
        public int MAX_SPAWN_HEIGHT = 127;
        public int MIN_SPAWN_HEIGHT = 0;
        public boolean DAMAGED_IN_WATER = false;
    }
}
