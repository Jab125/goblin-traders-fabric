package net.hat.gt.entities;

import com.jab125.thonkutil.util.Util;
import com.mojang.bridge.game.PackType;
import net.hat.gt.GobT;
import net.minecraft.SharedConstants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GoblinTraderEntity extends AbstractGoblinEntity {

    public GoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.APPLE);
    }

    @Override
    public Collection<ItemStack> getPreferredFoods() {
        Collection<ItemStack> preferredFoods = new ArrayList<>();
        preferredFoods.add(Util.toItemStack(Items.APPLE));
        preferredFoods.add(Util.toItemStack(Items.GOLDEN_APPLE));
        preferredFoods.add(Util.toItemStack(Items.ENCHANTED_GOLDEN_APPLE));
        return preferredFoods;
    }

    @Override
    public boolean canAttackBack()
    {
        return true;
    }

    @Override
    public boolean canSwimToFood()
    {
        return true;
    }

    @Override
    public int minSpawnHeight() {
        return SharedConstants.getGameVersion().getPackVersion(PackType.DATA) > 7 ? GobT.config.GOBLIN_TRADER_MIN_SPAWN_HEIGHT : GobT.config.GOBLIN_TRADER_MIN_SPAWN_HEIGHT_1_17;
    }

    @Override
    public int maxSpawnHeight() {
        return GobT.config.GOBLIN_TRADER_MAX_SPAWN_HEIGHT;
    }

    @Override
    public int spawnDelay() {
        return GobT.config.GOBLIN_TRADER_SPAWN_DELAY;
    }

    @Override
    public int spawnChance() {
        return GobT.config.GOBLIN_TRADER_SPAWN_CHANCE;
    }


    @SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
    public static boolean canGoblinSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos.down();
        return spawnReason == SpawnReason.SPAWNER || ThreadLocalRandom.current().nextInt(1, GobT.config.GOBLIN_SPAWN_RATE_D + 1) == 1;
    }
}
