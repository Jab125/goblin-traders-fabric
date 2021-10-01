package net.hat.gt.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;

@SuppressWarnings("deprecation")
public class GoblinTraderEntity extends AbstractGoblinEntity{
    public GoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static boolean goblinsCanSpawn = true;

    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.APPLE);
    }

    @Override
    public boolean canAttackBack()
    {
        return true;
    }

    public static boolean canSpawn(EntityType<GoblinTraderEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (pos.getY() >= world.getSeaLevel() && !goblinsCanSpawn) {
            return false;
        } else {
            int i = world.getLightLevel(pos);
            int j = 4;
            return i <= random.nextInt(j) && canMobSpawn(type, world, spawnReason, pos, random);
        }
    }
}
