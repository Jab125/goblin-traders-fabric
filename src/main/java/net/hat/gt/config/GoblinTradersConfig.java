package net.hat.gt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.hat.gt.GobT;

@Config(name = GobT.MODID)
public class GoblinTradersConfig implements ConfigData{
    // Settings
    public boolean GOBLINS_CAN_SPAWN = true;
    public boolean VEIN_GOBLINS_CAN_SPAWN = true;
    public boolean GOBLIN_HIT_BACK = true;
    public int GOBLIN_SPAWN_RATE = 1;
    public int VEIN_GOBLIN_SPAWN_RATE = 1;
}