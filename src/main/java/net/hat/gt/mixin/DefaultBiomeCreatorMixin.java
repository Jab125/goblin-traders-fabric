package net.hat.gt.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeCreator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultBiomeCreator.class)
public class DefaultBiomeCreatorMixin {
    @Inject(method = "createNetherWastes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/SpawnSettings$Builder;build()Lnet/minecraft/world/biome/SpawnSettings;"))
    private static void injected(CallbackInfoReturnable<Biome> cir){
    }
}
