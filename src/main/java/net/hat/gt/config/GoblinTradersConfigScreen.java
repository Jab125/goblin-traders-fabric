package net.hat.gt.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.hat.gt.GobT;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class GoblinTradersConfigScreen {
    private static final ConfigManager<GoblinTradersConfig> manager = (ConfigManager<GoblinTradersConfig>) AutoConfig.getConfigHolder(GoblinTradersConfig.class);
    public GoblinTradersConfigScreen() {}

    public static ConfigBuilder getConfigBuilder() {
        GoblinTradersConfig config = manager.getConfig();
        GobT.config = config;
        GoblinTradersConfig defaults = manager.getSerializer().createDefault();

        ConfigBuilder builder = ConfigBuilder.create().setTitle(new TranslatableText("text.autoconfig.goblintraders.title"));
        builder.setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/dirt.png"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory goblinTrader = builder.getOrCreateCategory(new TranslatableText("category.goblintraders.goblin_trader"));
        goblinTrader.addEntry(entryBuilder.startIntField(new LiteralText("Minimum Spawn Height"), getConfig().GOBLIN_TRADER_MIN_SPAWN_HEIGHT).setDefaultValue(getDefaultConfig().GOBLIN_TRADER_MIN_SPAWN_HEIGHT).setSaveConsumer(newValue -> getConfig().GOBLIN_TRADER_MIN_SPAWN_HEIGHT = newValue).build());
        goblinTrader.addEntry(entryBuilder.startIntField(new LiteralText("Maximum Spawn Height"), getConfig().GOBLIN_TRADER_MAX_SPAWN_HEIGHT).setDefaultValue(getDefaultConfig().GOBLIN_TRADER_MAX_SPAWN_HEIGHT).setSaveConsumer(newValue -> getConfig().GOBLIN_TRADER_MAX_SPAWN_HEIGHT = newValue).build());

        ConfigCategory veinGoblinTrader = builder.getOrCreateCategory(new TranslatableText("category.goblintraders.vein_goblin_trader"));
        veinGoblinTrader.addEntry(entryBuilder.startIntField(new LiteralText("Minimum Spawn Height"), getConfig().VEIN_GOBLIN_TRADER_MIN_SPAWN_HEIGHT).setDefaultValue(getDefaultConfig().VEIN_GOBLIN_TRADER_MIN_SPAWN_HEIGHT).setSaveConsumer(newValue -> getConfig().VEIN_GOBLIN_TRADER_MIN_SPAWN_HEIGHT = newValue).build());
        veinGoblinTrader.addEntry(entryBuilder.startIntField(new LiteralText("Maximum Spawn Height"), getConfig().VEIN_GOBLIN_TRADER_MAX_SPAWN_HEIGHT).setDefaultValue(getDefaultConfig().VEIN_GOBLIN_TRADER_MAX_SPAWN_HEIGHT).setSaveConsumer(newValue -> getConfig().VEIN_GOBLIN_TRADER_MAX_SPAWN_HEIGHT = newValue).build());
        goblinTrader.addEntry(entryBuilder.startBooleanToggle(new LiteralText("Vein Goblin Traders take damage in water"), getConfig().VEIN_GOBLINS_DIE_IN_WATER).setDefaultValue(getDefaultConfig().VEIN_GOBLINS_DIE_IN_WATER).setSaveConsumer(newValue -> getConfig().VEIN_GOBLINS_DIE_IN_WATER = newValue).build());

        builder.setSavingRunnable(manager::save);
        return builder;
    }
    private static GoblinTradersConfig getConfig(){
        return GobT.config;
    }

    private static GoblinTradersConfig getDefaultConfig(){
        return new GoblinTradersConfig();
    }
}
