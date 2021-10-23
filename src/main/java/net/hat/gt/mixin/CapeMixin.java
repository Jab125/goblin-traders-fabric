package net.hat.gt.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hat.gt.GobT;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class CapeMixin {
    @Shadow
    @Nullable
    protected abstract PlayerListEntry getPlayerListEntry();

    @Inject(method = "getCapeTexture", at = @At("RETURN"), cancellable = true)
    private void getCapeTexture_dev(CallbackInfoReturnable<Identifier> info) {
            if ((this.getPlayerListEntry().getProfile().getId().toString().equals("ab0b3d61-eba8-46f9-97ed-c739370ac74c") || this.getPlayerListEntry().getProfile().getId().toString().equals("2fdb9174-c6d7-4842-8172-7d009a30fe6a")) && false) {
            info.setReturnValue(new Identifier(GobT.MODID, "textures/cape/cape_dev.png"));
        }
    }
}
