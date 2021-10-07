package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FollowPotentialCustomerGoal extends Goal
{
    private PlayerEntity potentialCustomer;
    private AbstractGoblinEntity entity;
    private int coolDown = 0;
    private int timeout = 600;

    public FollowPotentialCustomerGoal(AbstractGoblinEntity entity)
    {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart()
    {
        if(this.entity.getCurrentCustomer() != null)
        {
            return false;
        }
        if(this.coolDown > 0)
        {
            this.coolDown--;
            return false;
        }
        this.findCustomer();
        return this.potentialCustomer != null && this.potentialCustomer.isAlive() && this.entity.isPreviousCustomer(this.potentialCustomer) && !this.potentialCustomer.isTouchingWater();
    }

    @Override
    public void tick()
    {
        this.entity.getLookControl().lookAt(this.potentialCustomer, 10.0F, (float) this.entity.getHeadRollingTimeLeft());
        if(this.entity.distanceTo(this.potentialCustomer) >= 2.0D)
        {
            this.entity.getNavigation().startMovingTo(this.potentialCustomer, 0.4F);
        }
        this.timeout--;
    }

    @Override
    public boolean shouldContinue()
    {
        return this.potentialCustomer != null && this.potentialCustomer.isAlive() && this.entity.getCurrentCustomer() == null && !this.potentialCustomer.isSpectator() && !this.potentialCustomer.isCreative() && !this.potentialCustomer.isTouchingWater() && this.entity.isPreviousCustomer(this.potentialCustomer) && this.entity.distanceTo(this.potentialCustomer) <= 10.0D && this.timeout > 0;
    }

    @Override
    public void stop()
    {
        this.entity.getNavigation().stop();
        this.potentialCustomer = null;
        this.timeout = 600;
        this.coolDown = 2400;
    }

    @Nullable
    private void findCustomer()
    {
        List<PlayerEntity> players = this.entity.world.getEntitiesByClass(PlayerEntity.class, this.entity.getBoundingBox().expand(10), playerEntity -> !playerEntity.isCreative() && !playerEntity.isSpectator());
        if(players.size() > 0)
        {
            this.potentialCustomer = players.stream().min(Comparator.comparing(this.entity::distanceTo)).get();
        }
    }
}
