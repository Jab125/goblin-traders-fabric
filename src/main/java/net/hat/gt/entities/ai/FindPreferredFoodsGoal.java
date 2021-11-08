package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.hat.gt.init.ModGameRules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FindPreferredFoodsGoal extends Goal
{
    private ItemEntity itemEntity;
    private ItemStack fakeItem;
    private final AbstractGoblinEntity entity;

    public FindPreferredFoodsGoal(AbstractGoblinEntity entity)
    {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart()
    {
        this.findPreferredFoods();
        return this.itemEntity != null && this.itemEntity.isAlive() && this.entity.getNavigation().findPathTo(this.itemEntity, 0) != null && (!this.itemEntity.isTouchingWater() || (this.itemEntity.isTouchingWater() && this.entity.canSwimToFood())) && (this.entity.getInventory().canInsert(fakeItem) || canInsertPartialStack(this.itemEntity.getStack().copy())) && this.entity.world.getGameRules().getBoolean(ModGameRules.GOBLIN_TRADERS_PICK_UP_FOODS);
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
            // Mental note: Don't simplify this line
            if (this.entity.getInventory().canInsert(fakeItem) && false) {
                ItemStack item = this.itemEntity.getStack();
                this.itemEntity.remove(Entity.RemovalReason.KILLED);
                this.entity.addFoodToStorage(item);
                this.entity.world.playSound(null, this.itemEntity.getX(), this.itemEntity.getY(), this.itemEntity.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1.0F, 0.75F);
            } else {
                ItemStack testItem = this.itemEntity.getStack().copy();
                boolean isSuccessful = false;
                for (int i = this.itemEntity.getStack().copy().getCount(); i > 0; i--) {
                    testItem.setCount(1);
                    if (this.entity.getInventory().canInsert(testItem)) {
                        this.itemEntity.getStack().setCount(this.itemEntity.getStack().copy().getCount() - 1);
                        this.entity.addFoodToStorage(testItem);
                        isSuccessful = true;
                    } else {
                        if (isSuccessful) {
                            this.entity.world.playSound(null, this.itemEntity.getX(), this.itemEntity.getY(), this.itemEntity.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1.0F, 0.75F);
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return this.itemEntity.isAlive() && this.entity.isAlive() && this.entity.getNavigation().findPathTo(this.itemEntity, 0) != null && (!this.itemEntity.isTouchingWater() || (this.itemEntity.isTouchingWater() && this.entity.canSwimToFood())) && (this.entity.getInventory().canInsert(fakeItem) || canInsertPartialStack(this.itemEntity.getStack().copy())) && this.entity.world.getGameRules().getBoolean(ModGameRules.GOBLIN_TRADERS_PICK_UP_FOODS);
    }

    private void findPreferredFoods()
    {
        for (ItemStack food : this.entity.getPreferredFoods()) {
            List<ItemEntity> players = this.entity.world.getEntitiesByClass(ItemEntity.class, this.entity.getBoundingBox().expand(10), itemEntity -> itemEntity.getStack().getItem() == food.getItem());
            if(players.size() > 0)
            {
                this.itemEntity = players.stream().min(Comparator.comparing(this.entity::distanceTo)).get();
                fakeItem = this.itemEntity.getStack().copy();
                fakeItem.setCount(itemEntity.getStack().getCount());
            }
        }

    }

    private boolean canInsertPartialStack(ItemStack itemstack) {
        ItemStack testItem = itemstack.copy();
        for (int i = itemEntity.getStack().copy().getCount(); i > 0; i--) {
            testItem.setCount(i);
            if (this.entity.getInventory().canInsert(testItem))
            return true;
        }
        return false;
    }
}
