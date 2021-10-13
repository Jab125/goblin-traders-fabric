package net.hat.gt.entities.ai;

import net.hat.gt.GobT;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.EnumSet;

public class AttackRevengeTargetGoal extends Goal
{
    protected final AbstractGoblinEntity entity;

    public AttackRevengeTargetGoal(AbstractGoblinEntity entity)
    {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart()
    {
        return this.entity.canAttackBack() && this.entity.getAttacker() != null && this.entity.getAttacker().isAlive() && this.entity.distanceTo(this.entity.getAttacker()) <= 10.0F && (!(this.entity.getAttacker() instanceof PlayerEntity) || !((PlayerEntity)this.entity.getAttacker()).isCreative()) && GobT.config.GOBLIN_HIT_BACK;
    }

    @Override
    public void tick()
    {
        LivingEntity revengeTarget = this.entity.getAttacker();
        if(revengeTarget != null && this.entity.getCurrentCustomer() == null)
        {
            this.entity.getLookControl().lookAt(revengeTarget, 10.0F, this.entity.getHeadRollingTimeLeft());
            if(this.entity.distanceTo(revengeTarget) >= 2.0D)
            {
                this.entity.getNavigation().startMovingTo(revengeTarget, 0.5F);
            }
            else
            {
                revengeTarget.damage(DamageSource.mob(this.entity), 1.0F);
                this.entity.swingHand(Hand.MAIN_HAND);
                this.entity.setAttacker(null);
            }
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return this.entity.isAlive() && this.entity.getAttacker() != null && this.entity.getAttacker().isAlive() && this.entity.distanceTo(this.entity.getAttacker()) <= 10.0F && this.entity.getCurrentCustomer() == null;
    }

    @Override
    public void stop()
    {
        this.entity.setAttacker(null);
    }
}
