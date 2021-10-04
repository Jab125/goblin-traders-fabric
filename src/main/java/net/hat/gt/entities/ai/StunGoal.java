package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class StunGoal extends Goal {

    private final AbstractGoblinEntity entity;

    public StunGoal(AbstractGoblinEntity entity)
    {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        return this.entity.isStunned();
    }
}
