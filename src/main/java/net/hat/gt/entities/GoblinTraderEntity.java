package net.hat.gt.entities;

import net.hat.gt.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class GoblinTraderEntity extends AbstractGoblinEntity{
    public GoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean goblinsCanSpawn = true;

    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.APPLE);
    }

    @Override
    public boolean canAttackBack()
    {
        return true;
    }

    @Override
    public boolean canSpawn(WorldView worldView){
        BlockPos blockUnderEntity = new BlockPos(this.getX(), this.getY() -1, this.getZ());
        BlockPos posEntity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return worldView.intersectsEntities(this)
                && !world.containsFluid(this.getBoundingBox())
                && this.world.getBlockState(posEntity).getBlock().canMobSpawnInside()
                && this.world.getBlockState(blockUnderEntity).allowsSpawning(worldView, blockUnderEntity, ModEntities.GOBLIN_TRADER)
                && goblinsCanSpawn;
    }
}
