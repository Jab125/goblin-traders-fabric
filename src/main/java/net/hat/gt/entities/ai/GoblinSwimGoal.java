package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.ai.goal.SwimGoal;

public class GoblinSwimGoal extends SwimGoal {
    AbstractGoblinEntity goblinEntity;

    public GoblinSwimGoal(AbstractGoblinEntity goblin) {
        super(goblin);
        this.goblinEntity = goblin;
    }

    @Override
    public boolean canStart() {
        return super.canStart() && !this.goblinEntity.isStunned();
    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue() && !this.goblinEntity.isStunned();
    }
}
