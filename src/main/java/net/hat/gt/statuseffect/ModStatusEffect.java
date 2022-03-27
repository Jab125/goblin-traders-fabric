package net.hat.gt.statuseffect;

import ladysnake.requiem.api.v1.internal.StatusEffectReapplicator;
import net.hat.gt.init.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ModStatusEffect extends StatusEffect {
    public ModStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity.getStatusEffect(ModStatusEffects.SUN_RESISTANCE) != null && entity.getStatusEffect(ModStatusEffects.SUN_PHOBIA) != null) {
            removeStatusEffect(entity, (int)(entity.getRandom().nextDouble() * 2) == 0 ? ModStatusEffects.SUN_PHOBIA : ModStatusEffects.SUN_RESISTANCE);
        }
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getStatusEffect(ModStatusEffects.SUN_RESISTANCE) != null && entity.getStatusEffect(ModStatusEffects.SUN_PHOBIA) != null) {
            removeStatusEffect(entity, (int)(entity.getRandom().nextDouble() * 2) == 0 ? ModStatusEffects.SUN_PHOBIA : ModStatusEffects.SUN_RESISTANCE);
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    protected void removeStatusEffect(LivingEntity entity, StatusEffect effect) {
        StatusEffectReapplicator.KEY.maybeGet(entity).ifPresent(r -> r.definitivelyClear(effect));
        StatusEffectReapplicator.KEY.maybeGet(entity).ifPresent(r -> r.definitivelyClear(effect));
        entity.removeStatusEffect(effect);
    }
}
