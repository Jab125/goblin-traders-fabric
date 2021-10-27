package net.hat.gt.mixin;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hat.gt.GobT;
import net.hat.gt.misc.CapesLoader;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static net.hat.gt.GobT.LOGGER;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class CapeMixin {
//    @Shadow @Nullable protected abstract PlayerListEntry getPlayerListEntry();
//
//    @Inject(method = "getCapeTexture", at = @At("RETURN"), cancellable = true)
//    private void getCapeTexture_goblintraders(CallbackInfoReturnable<Identifier> info) {
//        if (CapesLoader.PLAYERS != null) {
//           JsonObject players = CapesLoader.PLAYERS.get("players");
//            LOGGER.info(players.toString());
//
//            if (players.get("uuid").toString().equals(this.getPlayerListEntry().getProfile().getId().toString())) {
//                info.setReturnValue(new Identifier(GobT.MODID, "textures/cape/cape_" + CapesLoader.PLAYERS.get(this.getPlayerListEntry().getProfile().getId()) + ".png"));
//                LOGGER.info("YES CAPE");
//            } else {
//                LOGGER.info("NO CAPE");
//            }
//        }
//    }
}
