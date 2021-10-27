package net.hat.gt.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hat.gt.GobT;
import net.hat.gt.misc.CapesLoader;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
                info.setReturnValue(new Identifier(GobT.MODID, "textures/cape/cape_" + CapesLoader.UUID_CAPE_MAP.get(this.getPlayerListEntry().getProfile().getId().toString()) + ".png"));
            }
        }
    }
}