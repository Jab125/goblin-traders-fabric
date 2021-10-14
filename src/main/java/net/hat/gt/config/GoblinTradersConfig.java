package net.hat.gt.config;



import net.hat.gt.util.Util;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.hat.gt.GobT;



@Config(name = GobT.MODID)
public class GoblinTradersConfig implements ConfigData {
    // Settings
    public boolean MAX_ENCHANTMENT_TEXT = true;
    public boolean GOBLIN_HIT_BACK = true;
    public boolean GOBLINS_FALL = true;
    public boolean GOBLINS_CAN_SPAWN = true;
    public boolean VEIN_GOBLINS_CAN_SPAWN = true;
    public boolean GOBLIN_NO_ATTACK_CREATIVE = false;
    public int GOBLIN_SPAWN_RATE = 20;
    public int VEIN_GOBLIN_SPAWN_RATE = 35;
    public int GOBLIN_SPAWN_RATE_D = 100;
    public int VEIN_GOBLIN_SPAWN_RATE_D = 1;
    public int GOBLIN_GROUP_SIZE = 2;
    public int VEIN_GOBLIN_GROUP_SIZE = 2;
}
