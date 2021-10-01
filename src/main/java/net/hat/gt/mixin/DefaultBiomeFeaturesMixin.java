package net.hat.gt.mixin;

import net.hat.gt.init.ModEntities;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin{

    @Inject(at = @At("TAIL"), method = "addCaveMobs")
    private static void addCaveMobs(SpawnSettings.Builder builder, CallbackInfo callbackInfo){
        addGoblinTraderMob(builder);
    }

    private static void addGoblinTraderMob(SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.GOBLIN_TRADER, 1, 1, 1));
    }
}
