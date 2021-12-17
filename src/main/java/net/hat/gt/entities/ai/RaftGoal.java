package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.FluidTags;

import java.util.EnumSet;

public class RaftGoal extends Goal {
    private final AbstractGoblinEntity goblin;

    public RaftGoal(AbstractGoblinEntity goblin) {
        this.goblin = goblin;
        this.setControls(EnumSet.of(Control.JUMP));
        goblin.getNavigation().setCanSwim(true);
    }
    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public boolean canStart() {
        return this.goblin.isTouchingWater() && this.goblin.getFluidHeight(FluidTags.WATER) > this.goblin.getSwimHeight() || this.goblin.isInLava() && goblin.hasBag();
    }

    public void tick() {
        //goblin.waterLevel = goblin.getBodyY(1.0D);
        goblin.updateFloating();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
