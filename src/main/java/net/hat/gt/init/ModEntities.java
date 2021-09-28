package net.hat.gt.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hat.gt.entities.GoblinTraderEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class ModEntities {

    public static final EntityType<GoblinTraderEntity> GOBLIN_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("goblintraders", "goblin_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GoblinTraderEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(GOBLIN_TRADER, GoblinTraderEntity.createMobAttributes());
    }
}
