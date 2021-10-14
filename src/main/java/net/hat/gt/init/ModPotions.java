package net.hat.gt.init;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.registry.Registry;

import static com.jab125.thonkutil.util.Util.minutesToTick;
import static com.jab125.thonkutil.util.Util.secondsToTick;


public class ModPotions extends Potions {
    public static final Potion EXTENDED_NIGHT_VISION;
    public static final Potion EXTENDED_INVISIBILITY;
    public static final Potion POWERFUL_JUMP_BOOST ;
    public static final Potion EXTENDED_FIRE_RESISTANCE ;
    public static final Potion POWERFUL_SPEED;
    public static final Potion EXTENDED_WATER_BREATHING;
    public static final Potion POWERFUL_INSTANT_HEALTH;
    public static final Potion POWERFUL_REGENERATION ;
    public static final Potion EXTENDED_SLOW_FALLING;
    public static final Potion POWERFUL_STRENGTH;
    public static final Potion HASTE;
    public static final Potion ABSORPTION;
    public static final Potion LEVITATION;
    public static final Potion LUCK;
    public static final Potion DOLPHINS_GRACE;

    public static final Potion LEVITATION_EXTENDED;
    public static final Potion HASTE_EXTENDED;
    public static final Potion ABSORPTION_EXTENDED;


    private static Potion register(String name, Potion potion) {
        return Registry.register(Registry.POTION, name, potion);
    }
    static{
        EXTENDED_WATER_BREATHING = register("gtextended_water_breathing", new Potion(new StatusEffectInstance(StatusEffects.WATER_BREATHING, minutesToTick(15, 0))));
        EXTENDED_FIRE_RESISTANCE  = register("gtextended_fire_resistance", new Potion(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, minutesToTick(6, 0))));
        EXTENDED_NIGHT_VISION = register("gtextended_night_vision", new Potion(new StatusEffectInstance(StatusEffects.NIGHT_VISION, minutesToTick(15, 0))));
        EXTENDED_INVISIBILITY  = register("gtextended_invisibility", new Potion(new StatusEffectInstance(StatusEffects.INVISIBILITY, minutesToTick(6, 0))));
        EXTENDED_SLOW_FALLING = register("gtextended_slow_falling", new Potion(new StatusEffectInstance(StatusEffects.SLOW_FALLING, minutesToTick(6, 0))));
        POWERFUL_INSTANT_HEALTH = register("gtpowerful_instant_health", new Potion(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2)));
        POWERFUL_REGENERATION  = register("gtpowerful_regeneration", new Potion(new StatusEffectInstance(StatusEffects.REGENERATION, secondsToTick(22.5), 2)));
        POWERFUL_JUMP_BOOST  = register("gtpowerful_jump_boost", new Potion(new StatusEffectInstance(StatusEffects.JUMP_BOOST, minutesToTick(1, 30), 2)));
        POWERFUL_STRENGTH = register("gtpowerful_strength", new Potion(new StatusEffectInstance(StatusEffects.STRENGTH, secondsToTick(45), 2)));
        POWERFUL_SPEED = register("gtpowerful_speed", new Potion(new StatusEffectInstance(StatusEffects.SPEED, minutesToTick(1, 30), 2)));
        DOLPHINS_GRACE = register("gtdolphins_grace", new Potion(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, minutesToTick(3, 0))));
        ABSORPTION = register("gtabsorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, minutesToTick(3, 0))));
        LEVITATION = register("gtlevitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, secondsToTick(45))));
        HASTE = register("gtdig_speed", new Potion(new StatusEffectInstance(StatusEffects.HASTE, minutesToTick(3, 0))));
        LUCK = register("gtluck", new Potion(new StatusEffectInstance(StatusEffects.LUCK, minutesToTick(3, 0))));


        ABSORPTION_EXTENDED = register("gtabsorption_extended", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, minutesToTick(3, 0))));
        LEVITATION_EXTENDED = register("gtlevitation_extended", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, minutesToTick(1, 30))));
        HASTE_EXTENDED = register("gtdig_speed_extended", new Potion(new StatusEffectInstance(StatusEffects.HASTE, minutesToTick(1, 0))));
    }
    public static void registerPotions() {
        
    }
}
