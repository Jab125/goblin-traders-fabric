package net.hat.gt.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hat.gt.entities.GoblinTraderEntity;
import net.hat.gt.entities.VeinGoblinTraderEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<GoblinTraderEntity> GOBLIN_TRADER = Registry.register(Registry.ENTITY_TYPE, new Identifier("goblintraders", "goblin_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GoblinTraderEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.0f)).build()
    );
    public static final EntityType<VeinGoblinTraderEntity> VEIN_GOBLIN_TRADER = Registry.register(Registry.ENTITY_TYPE, new Identifier("goblintraders", "vein_goblin_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, VeinGoblinTraderEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.0f)).build()
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(GOBLIN_TRADER, GoblinTraderEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7D));
        FabricDefaultAttributeRegistry.register(VEIN_GOBLIN_TRADER, VeinGoblinTraderEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7D));
       /* if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
        }*/
    }
}
