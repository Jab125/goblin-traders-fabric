package com.jab125.util.datagen;


import com.jab125.thonkutil.api.datagen.ThonkUtilLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.hat.gt.init.ModEntities;
import net.hat.gt.init.ModGameRules;
import net.hat.gt.init.ModItems;
import net.hat.gt.init.ModPotions;
import net.minecraft.item.Items;

public class LanguageProvider extends ThonkUtilLanguageProvider {

    public LanguageProvider(FabricDataGenerator fabricDataGenerator, String locale) {
        super(fabricDataGenerator, locale);
    }

    @Override
    protected void addTranslations() {

        this.add(Items.ACACIA_BOAT.getTranslationKey() + ".desc", "Hmm yes, boat.");
        this.add("item.goblintraders.knockback_stick", "Knockback Stick");
        this.add("item.goblintraders.beg_paper", "please don't kill me after you've finished trading");

        this.add(ModEntities.GOBLIN_TRADER, "Goblin Trader");
        this.add(ModItems.GOBLIN_TRADER_SPAWN_EGG, "Goblin Trader Spawn Egg");
        this.add(ModEntities.VEIN_GOBLIN_TRADER, "Vein Goblin Trader");
        this.add(ModItems.VEIN_GOBLIN_TRADER_SPAWN_EGG, "Vein Goblin Trader Spawn Egg");
        this.add("stat.goblintraders.trade_with_goblin", "Traded with Goblins");
        this.add("stat.goblintraders.talk_with_goblin", "Talked with Goblins");

        this.addAutoConfigTitle("Goblin Traders Config");

        this.addAutoConfigCategory("default", "Misc");
        this.addAutoConfigCategory("all_goblin_traders_config", "All Goblin Traders");
        this.addAutoConfigCategory("goblin_trader_config", "Goblin Trader");
        this.addAutoConfigCategory("vein_goblin_trader_config", "Vein Goblin Trader");

        this.addAutoConfigOption("MAX_ENCHANTMENT_TEXT.@PrefixText", "<RR> = Requires Game Restart");
        this.addAutoConfigOption("MAX_ENCHANTMENT_TEXT", "Over Max Enchants have purple text");
        this.addAutoConfigOption("EASTER_EGGS", "Enable Easter Eggs");

        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.FIND_COOLDOWN.@Tooltip[0]", "The amount of ticks a goblin");
        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.FIND_COOLDOWN.@Tooltip[1]", "trader will wait before");
        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.FIND_COOLDOWN.@Tooltip[2]", "looking for a new customer");
        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.FIND_COOLDOWN", "Find Cooldown");
        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.FALL", "Fall Over");
        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.NO_ATTACK_CREATIVE", "Fix Creative Change attack");
        this.addAutoConfigOption("ALL_GOBLIN_TRADERS_CONFIG.CAN_GET_KNOCKED_OUT", "Can be Knocked Out");

        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.CAN_SPAWN", "Can Spawn");
        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.SPAWN_DELAY", "Spawn Delay <RR>");
        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.MAX_SPAWN_HEIGHT", "Max Spawn Height <RR>");
        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.MIN_SPAWN_HEIGHT", "Min Spawn Height <RR>");
        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.MIN_SPAWN_HEIGHT.@PrefixText", "They won't spawn under the world, so it's fine (1.18 thing)");
        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.SPAWN_CHANCE", "Spawn Chance <RR>");
        this.addAutoConfigOption("GOBLIN_TRADER_CONFIG.HIT_BACK", "Hit Back");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.CAN_SPAWN", "Can Spawn");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.SPAWN_DELAY", "Spawn Delay <RR>");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.MAX_SPAWN_HEIGHT", "Max Spawn Height <RR>");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.MIN_SPAWN_HEIGHT", "Min Spawn Height <RR>");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.SPAWN_CHANCE", "Spawn Chance <RR>");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.DAMAGED_IN_WATER", "Take Damage In Water");
        this.addAutoConfigOption("VEIN_GOBLIN_TRADER_CONFIG.HIT_BACK", "Hit Back");

        this.addPotionSet(ModPotions.HASTE, "Haste");
        this.addPotionSet(ModPotions.ABSORPTION, "Absorption");
        this.addPotionSet(ModPotions.DOLPHINS_GRACE, "Dolphins Grace");
        this.addPotionSet(ModPotions.LEVITATION, "Levitation");
        this.addPotionSet(ModPotions.BLINDNESS, "Blindness");
        this.addPotionSet(ModPotions.MINING_FATIGUE, "Mining Fatigue");
        //this.addPotionSet("sunlight_phobia", "the Burning Sun");
        //this.addPotionSet("sunlight_resistance", "Sun Resistance");

        //this.add("sun_phobia", "Sunlight Phobia");
        //this.add("sun_resistance", "Sunlight Resistance");

        this.add(ModItems.APPLE_CAPE, "Apple Cape");

        this.add(ModGameRules.GOBLIN_TRADERS_PICK_UP_FOODS, "Goblin Traders pick up foods");
        //this.add("goblinTradersPunchBack", "Goblin Traders punch back");
        //this.add("goblinTradersSpawn", "Goblin Traders spawn");
        //this.add("veinGoblinTradersSpawn", "Vein Goblin Traders spawn");

        // Requiem compat translations
        this.addRequiemTooltip("goblin_food", "Goblin Treat");
        this.addRequiemTooltip("vein_goblin_food", "Vein Goblin Treat");

    }
}
