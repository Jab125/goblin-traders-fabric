package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FindFavouriteFoodGoal extends Goal
{
    private ItemEntity itemEntity;
    private final AbstractGoblinEntity entity;

    public FindFavouriteFoodGoal(AbstractGoblinEntity entity)
    {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart()
    {
        this.findFavouriteFood();
        return this.itemEntity != null && this.itemEntity.isAlive() && this.entity.getNavigation().findPathTo(this.itemEntity, 0) != null;
    }

    @Override
    public void tick()
    {
        this.entity.getLookControl().lookAt(this.itemEntity, 10.0F, (float) this.entity.getHeadRollingTimeLeft());
        this.entity.getNavigation().stop();
        Path path = this.entity.getNavigation().findPathTo(this.itemEntity, 0);
        if(path != null) this.entity.getNavigation().startMovingAlong(path, 0.4F);
        if(this.entity.distanceTo(this.itemEntity) <= 1.0D && this.itemEntity.isAlive())
        {
            this.itemEntity.remove(Entity.RemovalReason.KILLED);
            this.entity.world.playSound(null, this.itemEntity.getX(), this.itemEntity.getY(), this.itemEntity.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1.0F, 0.75F);
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return this.itemEntity.isAlive() && this.entity.isAlive() && this.entity.getNavigation().findPathTo(this.itemEntity, 0) != null;
    }

    private void findFavouriteFood()
    {
        List<ItemEntity> players = this.entity.world.getEntitiesByClass(ItemEntity.class, this.entity.getBoundingBox().expand(10), itemEntity -> itemEntity.getStack().getItem() == this.entity.getFavouriteFood().getItem());
        if(players.size() > 0)
        {
            this.itemEntity = players.stream().min(Comparator.comparing(this.entity::distanceTo)).get();
        }
    }
}
