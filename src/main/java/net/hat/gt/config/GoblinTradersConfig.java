package net.hat.gt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.hat.gt.GobT;
import net.minecraft.util.Pair;


@Config(name = GobT.MODID)
public class GoblinTradersConfig implements ConfigData {
    // Settings
    public boolean MAX_ENCHANTMENT_TEXT = true;
    public boolean GOBLIN_HIT_BACK = true;
    public boolean GOBLINS_FALL = true;
    public boolean GOBLINS_CAN_SPAWN = true;
    public boolean VEIN_GOBLINS_CAN_SPAWN = true;
    public boolean GOBLIN_NO_ATTACK_CREATIVE = true;
    public boolean CAN_GET_KNOCKED_OUT = false;
    public int GOBLIN_SPAWN_RATE = 20;
    public int VEIN_GOBLIN_SPAWN_RATE = 35;
    public int GOBLIN_SPAWN_RATE_D = 100;
    public int VEIN_GOBLIN_SPAWN_RATE_D = 1;
    public int GOBLIN_GROUP_SIZE = 2;
    public int VEIN_GOBLIN_GROUP_SIZE = 2;
    public boolean VEIN_GOBLINS_DIE_IN_WATER = false;
    public boolean EASTER_EGGS = false;
    public int GOBLIN_TRADER_SPAWN_CHANCE = 25;
    public int GOBLIN_TRADER_SPAWN_DELAY = 24000;
    public int GOBLIN_TRADER_MAX_SPAWN_HEIGHT = 63;
    public int GOBLIN_TRADER_MIN_SPAWN_HEIGHT_1_17 = 0;
    public int GOBLIN_TRADER_MIN_SPAWN_HEIGHT_1_18 = -64;
    public int VEIN_GOBLIN_TRADER_SPAWN_CHANCE = 25;
    public int VEIN_GOBLIN_TRADER_SPAWN_DELAY = 24000;
    public int VEIN_GOBLIN_TRADER_MAX_SPAWN_HEIGHT = 127;
    public int VEIN_GOBLIN_TRADER_MIN_SPAWN_HEIGHT = 0;
}
