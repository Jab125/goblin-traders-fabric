package net.hat.gt.init;

import net.hat.gt.statuseffect.ModStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ModStatusEffects {
    public static StatusEffect SUN_PHOBIA = new ModStatusEffect(StatusEffectCategory.HARMFUL, 0xf5b042);
    public static StatusEffect SUN_RESISTANCE = new ModStatusEffect(StatusEffectCategory.BENEFICIAL, 0x405ef5);

    public static void registerStatusEffects() {
        if (true)
        return;
        try {
            for (Field field : ModStatusEffects.class.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.get(null) instanceof StatusEffect statusEffect) {
                    Registry.register(Registry.STATUS_EFFECT, Identifier.tryParse("goblintraders:" + field.getName().toLowerCase()), statusEffect);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }
}
