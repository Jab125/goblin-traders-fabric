package net.hat.gt.entities.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.Objects;
import java.util.Optional;

public class GoblinTraderBrain {
    private static final UniformIntProvider AVOID_MEMORY_DURATION = TimeHelper.betweenSeconds(5, 20);
    public static Brain<AbstractGoblinEntity> create(AbstractGoblinEntity entity, Brain<AbstractGoblinEntity> brain) {
        addAvoidActivities(brain);
        //brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addAvoidActivities(Brain<AbstractGoblinEntity> brain) {
        brain.setTaskList(Activity.AVOID, 10, ImmutableList.of(GoToRememberedPositionTask.toEntity(MemoryModuleType.AVOID_TARGET, 1.0F, 12, true)), MemoryModuleType.AVOID_TARGET);
    }

//    public static void tickActivities(AbstractGoblinEntity piglin) {
//        Brain<AbstractGoblinEntity> brain = piglin.getBrain();
//        //Activity activity = (Activity)brain.getFirstPossibleNonCoreActivity().orElse((Object)null);
//        brain.resetPossibleActivities(ImmutableList.of(Activity.AVOID));
//        //Activity activity2 = (Activity)brain.getFirstPossibleNonCoreActivity().orElse((Object)null);
////        if (activity != activity2) {
////            Optional var10000 = getCurrentActivitySound(piglin);
////            Objects.requireNonNull(piglin);
////            var10000.ifPresent(piglin::playSound);
////        }
//
//    }

    private static void runAwayFrom(AbstractGoblinEntity piglin, LivingEntity target) {
//        piglin.getBrain().forget(MemoryModuleType.ANGRY_AT);
//        piglin.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
//        piglin.getBrain().forget(MemoryModuleType.WALK_TARGET);
        piglin.getBrain().remember(MemoryModuleType.AVOID_TARGET, target, (long)AVOID_MEMORY_DURATION.get(piglin.world.random));
        //rememberHunting(piglin);
    }
}
