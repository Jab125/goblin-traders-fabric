package net.hat.gt.trades;

import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class NullTrade implements UpgradedTradeOfferFactory {
    public NullTrade() {}
    @Nullable
    @Override
    public UpgradedTradeOffer create(Entity entity, Random random) {
        return null;
    }
}
