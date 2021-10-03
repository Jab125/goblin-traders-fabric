package net.hat.gt.entities;

import net.hat.gt.init.ModTrades;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class VeinGoblinTraderEntity extends AbstractGoblinEntity{
    public VeinGoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static boolean goblinsCanSpawn = true;

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
    protected void fillRecipes() {
        TradeOffers.Factory[] factorys = ModTrades.VEIN_GOBLIN_TRADER_TRADES.get(1);
        TradeOffers.Factory[] factorys2 = ModTrades.VEIN_GOBLIN_TRADER_TRADES.get(2);
        TradeOffers.Factory[] factorys3 = ModTrades.VEIN_GOBLIN_TRADER_TRADES.get(3);
        TradeOffers.Factory[] factorys4 = ModTrades.VEIN_GOBLIN_TRADER_TRADES.get(4);
        TradeOffers.Factory[] factorys5 = ModTrades.VEIN_GOBLIN_TRADER_TRADES.get(5);
        if (factorys != null && factorys2 != null && factorys3 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factorys, ThreadLocalRandom.current().nextInt(3, 4 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys2, ThreadLocalRandom.current().nextInt(1, 2 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys3, ThreadLocalRandom.current().nextInt(1, 2 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys4, ThreadLocalRandom.current().nextInt(0, 1 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys5, ThreadLocalRandom.current().nextInt(-10, 1 + 1));
        }
    }

    @Override
    public boolean isFireImmune() {
        return true;
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
}
