package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HoldInHandsGoal;
import net.minecraft.sound.SoundEvents;

import java.util.EnumSet;

public class EatFavouriteFoodGoal extends HoldInHandsGoal<AbstractGoblinEntity>
{
    public EatFavouriteFoodGoal(AbstractGoblinEntity entity)
    {
        super(entity, entity.getFavouriteFood().copy(), SoundEvents.ENTITY_PLAYER_BURP, entity1 -> entity1.getHealth() < entity1.getMaxHealth() && entity1.getRandom().nextInt(100) == 0 && entity1.isAlive());
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }
}
