package net.hat.gt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

@SuppressWarnings("SameParameterValue") //Simplification reasons
public class FirePanicGoal extends Goal
{
    private final AbstractGoblinEntity goblin;
    private final double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public FirePanicGoal(AbstractGoblinEntity goblin, double speedIn)
    {
        this.goblin = goblin;
        this.speed = speedIn;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart()
    {
        if(this.goblin.isOnFire())
        {
            BlockPos blockpos = this.getClosestWaterPos(this.goblin.world, this.goblin, 5, 4);
            if(blockpos != null)
            {
                this.randPosX = blockpos.getX();
                this.randPosY = blockpos.getY();
                this.randPosZ = blockpos.getZ();
                return true;
            }
            return this.findRandomPosition();
        }
        return false;
    }

    @Override
    public void start()
    {
        this.goblin.getNavigation().startMovingTo(this.randPosX, this.randPosY, this.randPosZ, this.speed);
    }

    @Override
    public boolean shouldContinue()
    {
        return !this.goblin.getNavigation().isIdle() && this.goblin.isAlive();
    }

    private boolean findRandomPosition()
    {
        Vec3d randomPos = NoPenaltyTargeting.find(this.goblin, 5, 4);
        if(randomPos == null)
        {
            return false;
        }
        else
        {
            this.randPosX = randomPos.x;
            this.randPosY = randomPos.y;
            this.randPosZ = randomPos.z;
            return true;
        }
    }

    @Nullable
    private BlockPos getClosestWaterPos(World blockGetter, Entity entityIn, int horizontalRange, int verticalRange)
    {
        BlockPos entityPos = entityIn.getBlockPos();
        int entityX = entityPos.getX();
        int entityY = entityPos.getY();
        int entityZ = entityPos.getZ();
        float range = (float) (horizontalRange * horizontalRange * verticalRange * 2);
        BlockPos randomPos = null;
        BlockPos.Mutable currentPos = new BlockPos.Mutable();
        for(int x = entityX - horizontalRange; x <= entityX + horizontalRange; ++x)
        {
            for(int y = entityY - verticalRange; y <= entityY + verticalRange; ++y)
            {
                for(int z = entityZ - horizontalRange; z <= entityZ + horizontalRange; ++z)
                {
                    currentPos.set(x, y, z);
                    if(blockGetter.getFluidState(currentPos).isIn(FluidTags.WATER))
                    {
                        float f1 = (float) ((x - entityX) * (x - entityX) + (y - entityY) * (y - entityY) + (z - entityZ) * (z - entityZ));
                        if(f1 < range)
                        {
                            range = f1;
                            randomPos = new BlockPos(currentPos);
                        }
                    }
                }
            }
        }
        return randomPos;
    }
}
