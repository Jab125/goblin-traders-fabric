package net.hat.gt.util;


import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;

import net.hat.gt.GobT;

import net.hat.gt.entities.ai.ExcludeCreativeModeRevengeGoal;



import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import static com.jab125.thonkutil.util.Util.isEnchantmentDisplaysInstalled;


public class Util {

    public static boolean maxEnchantTextConfig() {
        return GobT.config.MAX_ENCHANTMENT_TEXT && !isEnchantmentDisplaysInstalled();
    }

    public static boolean isInCreativeMode(Entity entity) {
        if (entity.isPlayer()) {
            PlayerEntity playerEntity = (PlayerEntity)entity;
            return playerEntity.isCreative();
        }
        return false;
    }

    public static boolean isInCreativeModeStop(Entity entity, ExcludeCreativeModeRevengeGoal goal) {
        if (isInCreativeMode(entity)) {
            goal.stop();
        }
        return isInCreativeMode(entity);

    }

}
