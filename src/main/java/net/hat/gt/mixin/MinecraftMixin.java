package net.hat.gt.mixin;

import net.hat.gt.datagen.DataGen;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getBackendDescription()Ljava/lang/String;"))
    private void main(CallbackInfo info) {
        if (DataGen.ENABLED) {
            try {
                DataGen.run();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }

            System.exit(0);
        }
    }
}