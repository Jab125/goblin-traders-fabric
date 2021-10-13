package net.hat.gt.entities.ai;

import net.hat.gt.GobT;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.hat.gt.util.Util;

public class ExcludeCreativeModeRevengeGoal extends AttackRevengeTargetGoal{
    public ExcludeCreativeModeRevengeGoal(AbstractGoblinEntity entity) {
        super(entity);
    }
    @Override
    public boolean shouldContinue()
    {
        return this.entity.isAlive() && this.entity.getAttacker() != null && this.entity.getAttacker().isAlive() && this.entity.distanceTo(this.entity.getAttacker()) <= 10.0F && this.entity.getCurrentCustomer() == null && Util.isInCreativeMode(this.entity);
    }
}
