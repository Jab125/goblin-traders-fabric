package net.hat.gt.entities;

import com.jab125.thonkutil.util.Util;
import net.hat.gt.GobT;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
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
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.HIT_BACK;
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
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.DAMAGED_IN_WATER;
    }

    @Override
    public boolean isWet() {
        return this.isTouchingWater() || (this.hasBag() && this.isTouchingWaterOrRain());
    }

    @Override
    public int minSpawnHeight() {
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.MIN_SPAWN_HEIGHT;
    }

    @Override
    public int maxSpawnHeight() {
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.MAX_SPAWN_HEIGHT;
    }

    @Override
    public int spawnDelay() {
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.SPAWN_DELAY;
    }

    @Override
    public int spawnChance() {
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.SPAWN_CHANCE;
    }

    @Override
    public boolean canSpawn() {
        return GobT.config.VEIN_GOBLIN_TRADER_CONFIG.CAN_SPAWN;
    }
}
