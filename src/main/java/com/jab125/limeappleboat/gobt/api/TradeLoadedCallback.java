package com.jab125.limeappleboat.gobt.api;

import com.jab125.util.tradehelper.TradeRarity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.util.ActionResult;

public interface TradeLoadedCallback {
    Event<TradeLoadedCallback> EVENT = EventFactory.createArrayBacked(TradeLoadedCallback.class,
            (listeners) -> (goblin, tradeRarity) -> {
        for(TradeLoadedCallback listener : listeners) {
            ActionResult result = listener.interact(goblin, tradeRarity);
            if (result != ActionResult.PASS) {
                return result;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult interact(AbstractGoblinEntity goblin, TradeRarity rarity);
}
