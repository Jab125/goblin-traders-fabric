package net.hat.gt.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hat.gt.entities.GoblinEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<GoblinEntity> GOBLIN_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("goblintraders", "goblin_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GoblinEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(GOBLIN_TRADER, GoblinEntity.createMobAttributes());
    }
}
