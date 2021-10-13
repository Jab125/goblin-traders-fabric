package net.hat.gt.util;

import net.fabricmc.loader.api.FabricLoader;
import net.hat.gt.GobT;
import net.hat.gt.entities.ai.ExcludeCreativeModeRevengeGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;


public class Util {
    public static boolean isModInstalled(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }
    public static boolean isOverflowInstalled() {
        return isModInstalled("overflow");
    }
    public static boolean isEnchantmentDisplaysInstalled() {
        return isModInstalled("enchantment-displays");
    }
    public static boolean maxEnchantTextConfig() {
        return GobT.config.MAX_ENCHANTMENT_TEXT && !isEnchantmentDisplaysInstalled();
    }

    public static boolean isInCreativeMode(Entity entity) {
        if (entity.isPlayer()) {
            PlayerEntity playerEntity = (PlayerEntity)entity;
            if (playerEntity.isCreative()) {
                return true;
            }
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
