package net.hat.gt.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class GoblinTraderEntity extends AbstractGoblinEntity{
    public GoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.APPLE);
    }

    @Override
    public boolean canAttackBack()
    {
        return true;
    }
}
