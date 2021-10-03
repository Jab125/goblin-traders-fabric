package net.hat.gt.entities;

import net.hat.gt.init.ModTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;


public class GoblinTraderEntity extends AbstractGoblinEntity{
    public GoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static boolean goblinsCanSpawn = true;

    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.APPLE);
    }

    @Override
    public boolean canAttackBack()
    {
        return true;
    }

    @Override
    protected void fillRecipes() {
        TradeOffers.Factory[] factorys = ModTrades.GOBLIN_TRADER_TRADES.get(1);
        TradeOffers.Factory[] factorys2 = ModTrades.GOBLIN_TRADER_TRADES.get(2);
        TradeOffers.Factory[] factorys3 = ModTrades.GOBLIN_TRADER_TRADES.get(3);
        TradeOffers.Factory[] factorys4 = ModTrades.GOBLIN_TRADER_TRADES.get(4);
        if (factorys != null && factorys2 != null && factorys3 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factorys, ThreadLocalRandom.current().nextInt(4, 6 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys2, ThreadLocalRandom.current().nextInt(2, 5 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys3, ThreadLocalRandom.current().nextInt(1, 2 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys4, ThreadLocalRandom.current().nextInt(-10, 1 + 1));
        }
    }
}
