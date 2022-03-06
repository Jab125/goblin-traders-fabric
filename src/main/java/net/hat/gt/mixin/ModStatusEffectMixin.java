package net.hat.gt.mixin;

import ladysnake.requiem.api.v1.internal.StatusEffectReapplicator;
import ladysnake.requiem.api.v1.remnant.StickyStatusEffect;
import net.hat.gt.statuseffect.ModStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModStatusEffect.class)
public abstract class ModStatusEffectMixin implements StickyStatusEffect {
    @Override
    public boolean shouldStick(LivingEntity entity) {
        return true;
    }

    @Override
    public boolean shouldFreezeDuration(LivingEntity entity) {
        return true;
    }
}
