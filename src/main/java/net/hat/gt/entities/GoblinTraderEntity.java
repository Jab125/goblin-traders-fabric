package net.hat.gt.entities;

import com.google.common.collect.Sets;
import com.jab125.thonkutil.util.Util;
import net.hat.gt.GobT;
import net.hat.gt.init.ModTrades;
import net.hat.gt.trades.GoblinTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.*;
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


    @SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
    public static boolean canGoblinSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos.down();
        return spawnReason == SpawnReason.SPAWNER || ThreadLocalRandom.current().nextInt(1, GobT.config.GOBLIN_SPAWN_RATE_D + 1) == 1;
    }

}
