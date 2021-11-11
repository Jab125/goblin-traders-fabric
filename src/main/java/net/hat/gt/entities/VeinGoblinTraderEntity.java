package net.hat.gt.entities;

import com.jab125.thonkutil.util.Util;
import net.hat.gt.GobT;
import net.hat.gt.init.ModTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class VeinGoblinTraderEntity extends AbstractGoblinEntity{
    public VeinGoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Collection<ItemStack> getPreferredFoods() {
        Collection<ItemStack> preferredFoods = new ArrayList<>();
        preferredFoods.add(Util.toItemStack(Items.CARROT));
        preferredFoods.add(Util.toItemStack(Items.GOLDEN_CARROT));
        return preferredFoods;
    }

    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.CARROT);
    }

    @Override
    public boolean canAttackBack()
    {
        return true;
    }


    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean canSwimToFood()
    {
        return false;
    }

    @Override
    public void tick(){
        super.tick();
        if (!this.world.isClient) {
            this.updateLeash();
        }
        if (ThreadLocalRandom.current().nextInt(0, 2 + 1) == 2) {
            this.world.addParticle(ParticleTypes.FLAME, this.getX() - 0.5 + this.getRandom().nextDouble(), this.getY() + 0.5 - 0.5 + this.getRandom().nextDouble(), this.getZ() - 0.5 + this.getRandom().nextDouble(), 0, 0, 0);
        }
    }

    public boolean hurtByWater() {
        return GobT.config.VEIN_GOBLINS_DIE_IN_WATER;
    }

    @Override
    public boolean isWet() {
        return this.isTouchingWater();
    }

    @SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
    public static boolean canVeinGoblinSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos.down();
        return spawnReason == SpawnReason.SPAWNER || ThreadLocalRandom.current().nextInt(1, GobT.config.VEIN_GOBLIN_SPAWN_RATE_D + 1) == 1;
    }
}
