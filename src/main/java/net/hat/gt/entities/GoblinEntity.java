package net.hat.gt.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class GoblinEntity extends PathAwareEntity {

    public GoblinEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
}