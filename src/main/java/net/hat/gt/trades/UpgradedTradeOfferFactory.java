package net.hat.gt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public interface UpgradedTradeOfferFactory extends TradeOffers.Factory {
    @Nullable
    @Override
    UpgradedTradeOffer create(Entity entity, Random random);
}
