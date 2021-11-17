package net.hat.gt.entities;

import com.jab125.thonkutil.util.Util;
import com.mojang.bridge.game.PackType;
import net.hat.gt.GobT;
import net.minecraft.SharedConstants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;


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
        return GobT.config.GOBLIN_TRADER_CONFIG.HIT_BACK;
    }

    @Override
    public boolean canSwimToFood()
    {
        return true;
    }

    @Override
    public int minSpawnHeight() {
        return GobT.config.GOBLIN_TRADER_CONFIG.MIN_SPAWN_HEIGHT;
    }

    @Override
    public int maxSpawnHeight() {
        return GobT.config.GOBLIN_TRADER_CONFIG.MAX_SPAWN_HEIGHT;
    }

    @Override
    public int spawnDelay() {
        return GobT.config.GOBLIN_TRADER_CONFIG.SPAWN_DELAY;
    }

    @Override
    public int spawnChance() {
        return GobT.config.GOBLIN_TRADER_CONFIG.SPAWN_CHANCE;
    }

    @Override
    public boolean canSpawn() {
        return GobT.config.GOBLIN_TRADER_CONFIG.CAN_SPAWN;
    }

}
