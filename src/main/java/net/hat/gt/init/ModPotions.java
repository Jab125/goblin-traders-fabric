package net.hat.gt.init;


import com.jab125.thonkutil.api.RemovePotionRecipe;
import com.jab125.thonkutil.api.SkipPotion;
import net.hat.gt.GobT;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.registry.Registry;

import static com.jab125.thonkutil.api.RemovePotionRecipe.removeLingeringPotion;
import static com.jab125.thonkutil.util.Util.minutesToTick;
import static com.jab125.thonkutil.util.Util.secondsToTick;
import static com.jab125.thonkutil.api.BrewingRecipeRegistry.registerPotionRecipe;

public class ModPotions extends Potions {
    public static final Potion EXTENDED_NIGHT_VISION;
    public static final Potion EXTENDED_INVISIBILITY;
    public static final Potion POWERFUL_JUMP_BOOST ;
    public static final Potion EXTENDED_FIRE_RESISTANCE;
    public static final Potion POWERFUL_SPEED;
    public static final Potion EXTENDED_WATER_BREATHING;
    public static final Potion POWERFUL_INSTANT_HEALTH;
    public static final Potion POWERFUL_INSTANT_DAMAGE;
    public static final Potion POWERFUL_SLOWNESS;
    public static final Potion POWERFUL_REGENERATION;
    public static final Potion EXTENDED_SLOW_FALLING;
    public static final Potion POWERFUL_STRENGTH;
    public static final Potion HASTE;
    public static final Potion ABSORPTION;
    public static final Potion LEVITATION;
    public static final Potion LUCK;
    public static final Potion DOLPHINS_GRACE;
    public static final Potion BLINDNESS;

    public static final Potion LEVITATION_EXTENDED;
    public static final Potion HASTE_EXTENDED;
    public static final Potion ABSORPTION_EXTENDED;
    public static final Potion LUCK_EXTENDED;
    public static final Potion BLINDNESS_EXTENDED;
    public static final Potion BLINDNESS_EXTENDED_2;

    public static final Potion WEAK_NIGHT_VISION;
    public static final Potion WEAK_NIGHT_VISION_EXTENDED;
    public static final Potion WEAK_NIGHT_VISION_EXTENDED_2;


    private static Potion register(String name, Potion potion) {
        RemovePotionRecipe.removeTippedArrow(potion);
        SkipPotion.skipPotion(potion, SkipPotion.TIPPED_ARROW);
        return Registry.register(Registry.POTION, GobT.id(name), potion);
    }
    static{
        EXTENDED_WATER_BREATHING = register("extended_water_breathing", new Potion("water_breathing", new StatusEffectInstance(StatusEffects.WATER_BREATHING, minutesToTick(15, 0))));
        EXTENDED_FIRE_RESISTANCE  = register("extended_fire_resistance", new Potion("fire_resistance", new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, minutesToTick(6, 0))));
        EXTENDED_NIGHT_VISION = register("extended_night_vision", new Potion("night_vision", new StatusEffectInstance(StatusEffects.NIGHT_VISION, minutesToTick(15, 0))));
        EXTENDED_INVISIBILITY  = register("extended_invisibility", new Potion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, minutesToTick(6, 0))));
        EXTENDED_SLOW_FALLING = register("extended_slow_falling", new Potion("slow_falling", new StatusEffectInstance(StatusEffects.SLOW_FALLING, minutesToTick(6, 0))));
        POWERFUL_INSTANT_HEALTH = register("powerful_instant_health", new Potion("healing", new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2)));
        POWERFUL_INSTANT_DAMAGE = register("powerful_instant_damage", new Potion("harming", new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 2)));
        POWERFUL_REGENERATION  = register("powerful_regeneration", new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, secondsToTick(22.5), 2)));
        POWERFUL_SLOWNESS  = register("powerful_slowness", new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, secondsToTick(10), 4)));
        POWERFUL_JUMP_BOOST  = register("powerful_jump_boost", new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, minutesToTick(1, 30), 2)));
        POWERFUL_STRENGTH = register("powerful_strength", new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, secondsToTick(45), 2)));
        POWERFUL_SPEED = register("powerful_speed", new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, minutesToTick(1, 30), 2)));
        DOLPHINS_GRACE = register("dolphins_grace", new Potion("dolphins_grace", new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, minutesToTick(3, 0))));
        ABSORPTION = register("absorption", new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, minutesToTick(3, 0))));
        LEVITATION = register("levitation", new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, secondsToTick(45))));
        HASTE = register("dig_speed", new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, minutesToTick(3, 0))));
        LUCK = register("luck", new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, minutesToTick(3, 0))));
        BLINDNESS = register("blindness", new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, secondsToTick(15))));

        //Extended (from mod) Potions
        ABSORPTION_EXTENDED = register("absorption_extended", new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, minutesToTick(3, 0))));
        LEVITATION_EXTENDED = register("levitation_extended", new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, minutesToTick(1, 30))));
        HASTE_EXTENDED = register("dig_speed_extended", new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, minutesToTick(1, 0))));
        LUCK_EXTENDED = register("luck_extended", new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, minutesToTick(8, 0))));
        BLINDNESS_EXTENDED = register("blindness_extended", new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, secondsToTick(45))));
        BLINDNESS_EXTENDED_2 = register("blindness_extended_2", new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, minutesToTick(1, 30))));

        //Blindness with fermented spider eye
        WEAK_NIGHT_VISION = register("weak_night_vision", new Potion("night_vision", new StatusEffectInstance(StatusEffects.NIGHT_VISION, secondsToTick(15))));
        WEAK_NIGHT_VISION_EXTENDED = register("weak_night_vision_extended", new Potion("night_vision", new StatusEffectInstance(StatusEffects.NIGHT_VISION, secondsToTick(45))));
        WEAK_NIGHT_VISION_EXTENDED_2 = register("weak_night_vision_extended_2", new Potion("night_vision", new StatusEffectInstance(StatusEffects.NIGHT_VISION, secondsToTick(45))));

    }
    public static void registerPotions() {
        removeSplashAndLingeringPotionRecipes();
    }

    private static void removeSplashAndLingeringPotionRecipes() {
        removeSplashAndLingeringPotion(POWERFUL_INSTANT_DAMAGE);
        removeSplashAndLingeringPotion(POWERFUL_SLOWNESS);
    }
    private static void removeSplashAndLingeringPotion(Potion potion) {
        removeLingeringPotion(potion);
        RemovePotionRecipe.removeSplashPotion(potion);
    }
    public static void registerPotionRecipes() {
        registerPotionRecipe(ModPotions.NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, ModPotions.INVISIBILITY);
        registerPotionRecipe(ModPotions.POWERFUL_JUMP_BOOST, Items.FERMENTED_SPIDER_EYE, ModPotions.POWERFUL_SLOWNESS);
        registerPotionRecipe(ModPotions.POWERFUL_SPEED, Items.FERMENTED_SPIDER_EYE, ModPotions.POWERFUL_SLOWNESS);
        registerPotionRecipe(ModPotions.POWERFUL_INSTANT_HEALTH, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_HARMING);
        registerPotionRecipe(ModPotions.ABSORPTION, Items.REDSTONE, ModPotions.ABSORPTION_EXTENDED);
        registerPotionRecipe(ModPotions.LEVITATION, Items.REDSTONE, ModPotions.LEVITATION_EXTENDED);
        registerPotionRecipe(ModPotions.HASTE, Items.REDSTONE, ModPotions.HASTE_EXTENDED);
        registerPotionRecipe(ModPotions.LUCK, Items.REDSTONE, ModPotions.LUCK_EXTENDED);
        registerPotionRecipe(ModPotions.BLINDNESS, Items.REDSTONE, ModPotions.BLINDNESS_EXTENDED);
        registerPotionRecipe(ModPotions.BLINDNESS_EXTENDED, Items.REDSTONE, ModPotions.BLINDNESS_EXTENDED_2);

        //Blindness
        registerPotionRecipe(ModPotions.BLINDNESS, Items.FERMENTED_SPIDER_EYE, ModPotions.WEAK_NIGHT_VISION);
        registerPotionRecipe(ModPotions.BLINDNESS_EXTENDED, Items.FERMENTED_SPIDER_EYE, ModPotions.WEAK_NIGHT_VISION_EXTENDED);
        registerPotionRecipe(ModPotions.BLINDNESS_EXTENDED_2, Items.FERMENTED_SPIDER_EYE, ModPotions.WEAK_NIGHT_VISION_EXTENDED_2);

        //Weak Night Vision from Blindness
        registerPotionRecipe(ModPotions.WEAK_NIGHT_VISION, Items.REDSTONE, ModPotions.WEAK_NIGHT_VISION_EXTENDED);
        registerPotionRecipe(ModPotions.WEAK_NIGHT_VISION_EXTENDED, Items.REDSTONE, ModPotions.WEAK_NIGHT_VISION_EXTENDED_2);
        registerPotionRecipe(ModPotions.WEAK_NIGHT_VISION_EXTENDED_2, Items.REDSTONE, Potions.NIGHT_VISION);

        //Weak Night Vision to Invisibility
        registerPotionRecipe(ModPotions.WEAK_NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, Potions.INVISIBILITY);
        registerPotionRecipe(ModPotions.WEAK_NIGHT_VISION_EXTENDED, Items.FERMENTED_SPIDER_EYE, Potions.INVISIBILITY);
        registerPotionRecipe(ModPotions.WEAK_NIGHT_VISION_EXTENDED_2, Items.FERMENTED_SPIDER_EYE, Potions.INVISIBILITY);
    }
}
