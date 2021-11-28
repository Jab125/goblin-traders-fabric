package net.hat.gt.mixin;

import net.hat.gt.datagen.DataGen;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

/**
 * Makes the datagen run is correct conditions
 */
@Mixin(Main.class)
public class MainMixin {
    @Inject(method = "main", at = @At(value = "NEW", target = "net/minecraft/server/dedicated/ServerPropertiesLoader"), cancellable = true)
    private static void main(String[] args, CallbackInfo info) throws IOException {
        if (DataGen.ENABLED) {
            DataGen.run();
            info.cancel();
        }
    }
}