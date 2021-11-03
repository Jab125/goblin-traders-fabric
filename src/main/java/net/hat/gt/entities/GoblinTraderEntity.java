package net.hat.gt.entities;

import com.google.common.collect.Sets;
import com.jab125.thonkutil.util.Util;
import net.hat.gt.GobT;
import net.hat.gt.init.ModTrades;
import net.hat.gt.trades.GoblinTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
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

    @Override
    protected void fillRecipes() {
        if (GobT.config.GOBLIN_VANILLA_TRADES) {
            TradeOffers.Factory[] factorys = ModTrades.GOBLIN_TRADER_TRADES_VANILLA.get(1);
            TradeOffers.Factory[] factorys2 = ModTrades.GOBLIN_TRADER_TRADES_VANILLA.get(2);
            TradeOffers.Factory[] factorys3 = ModTrades.GOBLIN_TRADER_TRADES_VANILLA.get(3);
            TradeOffers.Factory[] factorys4 = ModTrades.GOBLIN_TRADER_TRADES_VANILLA.get(4);
            if (factorys != null && factorys2 != null && factorys3 != null) {
                TradeOfferList tradeOfferList = this.getOffers();
                this.fillRecipesFromPool(tradeOfferList, factorys, ThreadLocalRandom.current().nextInt(4, 6 + 1));
                this.fillRecipesFromPool(tradeOfferList, factorys2, ThreadLocalRandom.current().nextInt(3, 5 + 1));
                this.fillRecipesFromPool(tradeOfferList, factorys3, ThreadLocalRandom.current().nextInt(1, 3 + 1));
                this.fillRecipesFromPool(tradeOfferList, factorys4, ThreadLocalRandom.current().nextInt(-25, 1 + 1));
            }
        }
        else {
            TradeOffers.Factory[] easterEggFactory = ModTrades.EASTER_EGG_TRADES.get(1);
            TradeOffers.Factory[] factorys = ModTrades.GOBLIN_TRADER_TRADES.get(1);
            TradeOffers.Factory[] factorys2 = ModTrades.GOBLIN_TRADER_TRADES.get(2);
            TradeOffers.Factory[] factorys3 = ModTrades.GOBLIN_TRADER_TRADES.get(3);
            TradeOffers.Factory[] factorys4 = ModTrades.GOBLIN_TRADER_TRADES.get(4);
            if (factorys != null && factorys2 != null && factorys3 != null) {
                TradeOfferList tradeOfferList = this.getOffers();
                this.fillRecipesFromPool(tradeOfferList, factorys, ThreadLocalRandom.current().nextInt(4, 6 + 1));
                this.fillRecipesFromPool(tradeOfferList, factorys2, ThreadLocalRandom.current().nextInt(3, 5 + 1));
                this.fillRecipesFromPool(tradeOfferList, factorys3, ThreadLocalRandom.current().nextInt(1, 3 + 1));
                this.fillRecipesFromPool(tradeOfferList, factorys4, ThreadLocalRandom.current().nextInt(-25, 1 + 1));
            }
        }
    }

    @SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
    public static boolean canGoblinSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos.down();
        return spawnReason == SpawnReason.SPAWNER || ThreadLocalRandom.current().nextInt(1, GobT.config.GOBLIN_SPAWN_RATE_D + 1) == 1;
    }

    @Override
    protected void fillRecipesFromPool(TradeOfferList recipeList, TradeOffers.Factory[] pool, int count) {
        Set<Integer> set = Sets.newHashSet();
        if (pool.length > count) {
            while(set.size() < count) {
                set.add(this.random.nextInt(pool.length));
            }
        } else {
            for(int i = 0; i < pool.length; ++i) {
                set.add(i);
            }
        }

        Iterator var9 = set.iterator();

        while(var9.hasNext()) {
            Integer integer = (Integer)var9.next();
            TradeOffers.Factory factory = pool[integer];
            TradeOffer tradeOffer = factory.create(this, this.random);
            if (tradeOffer != null) {
                if ((int) (Math.random() * 20) == 1 && GobT.config.EASTER_EGGS) {
                    recipeList.add(GoblinTrades.easterEggTrades()[this.random.nextInt(GoblinTrades.easterEggTrades().length)].create(this, this.random));
                }
                recipeList.add(tradeOffer);
            }
        }

    }
}
