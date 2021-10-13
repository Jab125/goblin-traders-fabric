package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.hat.gt.util.Util;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Hand;

//TODO: MAKE IT WORK
public class ExcludeCreativeModeRevengeGoal extends AttackRevengeTargetGoal{
    public ExcludeCreativeModeRevengeGoal(AbstractGoblinEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        LivingEntity revengeTarget = this.entity.getAttacker();
        assert revengeTarget != null;
        if (Util.isInCreativeMode(revengeTarget)) stop();
        if(this.entity.getCurrentCustomer() == null)
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

    //    @Override
//    public boolean shouldContinue()
//    {
//        if (Util.isInCreativeMode(entity)) {
//            stop();
//        }
//        return this.entity.isAlive() && this.entity.getAttacker() != null && this.entity.getAttacker().isAlive() && this.entity.distanceTo(this.entity.getAttacker()) <= 10.0F && this.entity.getCurrentCustomer() == null && !Util.isInCreativeModeStop(this.entity.getAttacker(), this);
//    }
}
