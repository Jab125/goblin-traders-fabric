package net.hat.gt.mixin;

import net.hat.gt.entities.GoblinTraderEntity;
import net.hat.gt.entities.VeinGoblinTraderEntity;
import net.hat.gt.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SpawnRestriction.class)
public abstract class SpawnRestrictionMixin {

    @Shadow
    private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location,
                                                       Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {}

    static {
        register(ModEntities.GOBLIN_TRADER, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GoblinTraderEntity::canGoblinSpawn);
        register(ModEntities.VEIN_GOBLIN_TRADER, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VeinGoblinTraderEntity::canVeinGoblinSpawn);
    }
}


