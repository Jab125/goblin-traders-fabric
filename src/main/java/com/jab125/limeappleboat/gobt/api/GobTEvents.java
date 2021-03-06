package com.jab125.limeappleboat.gobt.api;

import com.jab125.util.tradehelper.TradeRarities;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public interface GobTEvents {
    Event<TradeLoaded> TRADE_LOADED = EventFactory.createArrayBacked(TradeLoaded.class,
            (listeners) -> (goblin, tradeRarity) -> {
        for(TradeLoaded listener : listeners) {
            ActionResult result = listener.interact(goblin, tradeRarity);
            if (result != ActionResult.PASS) {
                return result;
            }
        }
        return ActionResult.PASS;
    });

    Event<OnAttemptSpawn> ON_ATTEMPT_SPAWN = EventFactory.createArrayBacked(OnAttemptSpawn.class,
            (listeners) -> (goblinType, serverWorld, pos) -> {
                for(OnAttemptSpawn listener : listeners) {
                    ActionResult result = listener.interact(goblinType, serverWorld, pos);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });


    interface TradeLoaded {
        ActionResult interact(AbstractGoblinEntity goblin, TradeRarities rarity);
    }

    interface OnAttemptSpawn {
        ActionResult interact(EntityType<?> goblinTraderType, ServerWorld world, BlockPos safestPos);
    }
}
