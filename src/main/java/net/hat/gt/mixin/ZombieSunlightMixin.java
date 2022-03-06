package net.hat.gt.mixin;

import net.hat.gt.init.ModStatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieSunlightMixin {
    @Inject(method = "burnsInDaylight", at = @At("HEAD"), cancellable = true)
    private void goblintraders$removeBurn(CallbackInfoReturnable<Boolean> cir) {
        if (((ZombieEntity) (Object) this).hasStatusEffect(ModStatusEffects.SUN_RESISTANCE)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
