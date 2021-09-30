package net.hat.gt.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hat.gt.entities.GoblinTraderEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<GoblinTraderEntity> GOBLIN_TRADER = Registry.register(Registry.ENTITY_TYPE, new Identifier("goblintraders", "goblin_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GoblinTraderEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.00f)).build()
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(GOBLIN_TRADER, GoblinTraderEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D));
    }
}
