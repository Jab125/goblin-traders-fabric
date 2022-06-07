package net.hat.gt.entities.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.LivingTargetCache;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GoblinTraderSpecificSensor extends Sensor<AbstractGoblinEntity> {
    @Override
    protected void sense(ServerWorld world, AbstractGoblinEntity entity) {
        Brain<?> brain = entity.getBrain();
        Optional<WardenEntity> optional = Optional.empty();
        LivingTargetCache livingTargetCache = (LivingTargetCache) brain.getOptionalMemory(MemoryModuleType.VISIBLE_MOBS).orElse(LivingTargetCache.empty());

        for (LivingEntity livingEntity : livingTargetCache.iterate((livingEntityx) -> true)) {
            //                    if (livingEntity instanceof PlayerEntity) {
//                        PlayerEntity playerEntity = (PlayerEntity)livingEntity;
//                        if (optional6.isEmpty() && !PiglinBrain.wearsGoldArmor(playerEntity) && entity.canTarget(livingEntity)) {
//                            optional6 = Optional.of(playerEntity);
//                        }
//
//                        if (optional7.isEmpty() && !playerEntity.isSpectator() && PiglinBrain.isGoldHoldingPlayer(playerEntity)) {
//                            optional7 = Optional.of(playerEntity);
//                        }
            /*} else*/
            if (optional.isEmpty() && (livingEntity instanceof WardenEntity)) {
                optional = Optional.of((WardenEntity) livingEntity);
            }
            brain.remember(MemoryModuleType.AVOID_TARGET, optional);
            return;
        }
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return ImmutableSet.of(MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.AVOID_TARGET);
    }
}
