package net.hat.gt.mixin;

import ladysnake.requiem.api.v1.remnant.StickyStatusEffect;
import net.hat.gt.statuseffect.ModStatusEffect;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

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
