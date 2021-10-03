package net.hat.gt.init;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.registry.Registry;

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


    private static Potion register(String name, Potion potion) {
        return Registry.register(Registry.POTION, name, potion);
    }
    static{
        EXTENDED_WATER_BREATHING = register("gtextended_water_breathing", new Potion(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 18000)));
        EXTENDED_FIRE_RESISTANCE  = register("gtextended_fire_resistance", new Potion(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 7200)));
        EXTENDED_NIGHT_VISION = register("gtextended_night_vision", new Potion(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 18000)));
        EXTENDED_INVISIBILITY  = register("gtextended_invisibility", new Potion(new StatusEffectInstance(StatusEffects.INVISIBILITY, 7200)));
        EXTENDED_SLOW_FALLING = register("gtextended_slow_falling", new Potion(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 7200)));
        POWERFUL_INSTANT_HEALTH = register("gtpowerful_instant_health", new Potion(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2)));
        POWERFUL_REGENERATION  = register("gtpowerful_regeneration", new Potion(new StatusEffectInstance(StatusEffects.REGENERATION, 450, 2)));
        POWERFUL_JUMP_BOOST  = register("gtpowerful_jump_boost", new Potion(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1800, 2)));
        POWERFUL_STRENGTH = register("gtpowerful_strength", new Potion(new StatusEffectInstance(StatusEffects.STRENGTH, 900, 2)));
        POWERFUL_SPEED = register("gtpowerful_speed", new Potion(new StatusEffectInstance(StatusEffects.SPEED, 1800, 2)));
        DOLPHINS_GRACE = register("gtdolphins_grace", new Potion(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 3600)));
        ABSORPTION = register("gtabsorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 3600)));
        LEVITATION = register("gtlevitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 900)));
        HASTE = register("gtdig_speed", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 3600)));
        LUCK = register("gtluck", new Potion(new StatusEffectInstance(StatusEffects.LUCK, 3600)));

    }
}
