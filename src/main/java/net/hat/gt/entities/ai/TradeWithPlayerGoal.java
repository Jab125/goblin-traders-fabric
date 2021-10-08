package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class TradeWithPlayerGoal extends Goal
{
    private final AbstractGoblinEntity entity;

    public TradeWithPlayerGoal(AbstractGoblinEntity entity)
    {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    @Override
    public boolean canStart()
    {
        if(!this.entity.isAlive())
        {
            return false;
        }
        else if(this.entity.isTouchingWater())
        {
            return false;
        }
        else if(!this.entity.isOnGround())
        {
            return false;
        }
        else if(this.entity.hurtByWater())
        {
            return false;
        }
        else
        {
            PlayerEntity player = this.entity.getCurrentCustomer();
            if(player == null)
            {
                return false;
            }
            else if(this.entity.squaredDistanceTo(player) > 16.0D)
            {
                return false;
            }
            return player.getInventory() != null;
        }
    }

    @Override
    public void start()
    {
        this.entity.getNavigation().stop();
    }

    @Override
    public void stop()
    {
        this.entity.setCurrentCustomer(null);
    }
}
