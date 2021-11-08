package net.hat.gt.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hat.gt.util.CapesLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.net.URL;

// Just putting this in here, Jab.
// Thanks for all the work that you did on the cape system. Half of this stuff I don't fully understand how it works.
// Your help is greatly appreciated throughout the project, and I couldn't thank you more for it.

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class CapeMixin {
    @Shadow
    @Nullable
    protected abstract PlayerListEntry getPlayerListEntry();

    @Inject(method = "getCapeTexture", at = @At("RETURN"), cancellable = true)
    private void getCapeTexture_goblintraders(CallbackInfoReturnable<Identifier> info) {
        if (CapesLoader.UUID_CAPE_MAP != null) {
            if (this.getPlayerListEntry() != null && CapesLoader.UUID_CAPE_MAP.containsKey(this.getPlayerListEntry().getProfile().getId().toString())) {
                try {
                    final URL url = new URL(String.format("https://raw.githubusercontent.com/3e2j/goblin-traders-fabric/1.17/capes/cape_%s.png", CapesLoader.UUID_CAPE_MAP.get(this.getPlayerListEntry().getProfile().getId().toString())));
                    final NativeImage nativeImage = NativeImage.read(url.openStream());
                    final NativeImageBackedTexture dynamicTexture = new NativeImageBackedTexture(CapesLoader.parseCape(nativeImage));
                    final Identifier resourceLocation = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("goblintradercapes/", dynamicTexture);
                    info.setReturnValue(resourceLocation);
                } catch (IOException ignored) {

                }
            }
        }
    }
}