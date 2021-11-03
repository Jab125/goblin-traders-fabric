package net.hat.gt.entities;

import net.hat.gt.trades.GoblinTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;

public class TestGoblinTraderEntity extends AbstractTieredGoblinEntity{
    public TestGoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getFavouriteFood() {
        return new ItemStack(Items.COOKED_BEEF);
    }

    @Override
    public boolean canAttackBack() {
        return false;
    }

    @Override
    public boolean canSwimToFood() {
        return false;
    }

    @Override
    TradeOffers.Factory[] noviceTrades() {
        return GoblinTrades.goblinCommonTrades();
    }

    @Override
    TradeOffers.Factory[] apprenticeTrades() {
        return GoblinTrades.goblinUncommonTrades();
    }

    @Override
    TradeOffers.Factory[] journeymanTrades() {
        return GoblinTrades.goblinRareTrades();
    }

    @Override
    TradeOffers.Factory[] expertTrades() {
        return GoblinTrades.epicVeinGoblinTrades();
    }

    @Override
    TradeOffers.Factory[] masterTrades() {
        return GoblinTrades.legendaryGoblinTrades();
    }
}
