package net.hat.gt.compat;

import ladysnake.requiem.api.v1.RequiemPlugin;
import ladysnake.requiem.api.v1.internal.StatusEffectReapplicator;
import ladysnake.requiem.api.v1.remnant.SoulbindingRegistry;
import net.hat.gt.init.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;

public class RequiemCompat implements RequiemPlugin {
    private static void requiemCompat() {
    }

    @Override
    public void onRequiemInitialize() {
        RequiemPlugin.super.onRequiemInitialize();
    }

    @Override
    public void registerSoulBindings(SoulbindingRegistry registry) {
        registry.registerSoulbound(ModStatusEffects.SUN_PHOBIA);
        registry.registerSoulbound(ModStatusEffects.SUN_RESISTANCE);
        RequiemPlugin.super.registerSoulBindings(registry);
    }

    public static void callRemoveCompat(LivingEntity entity, StatusEffect effect) {
        StatusEffectReapplicator.KEY.maybeGet(entity).ifPresent(statusEffectReapplicator -> statusEffectReapplicator.definitivelyClear(effect));
    }
}
