package net.hat.gt.mixin;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.hat.gt.init.ModStats;
import net.minecraft.screen.slot.TradeOutputSlot;
import net.minecraft.util.Identifier;
import net.minecraft.village.Merchant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TradeOutputSlot.class)
public class TradeOutputSlotMixin
{
    @Shadow
    @Final
    private Merchant merchant;

    @ModifyArg(method = "onTakeItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;incrementStat(Lnet/minecraft/util/Identifier;)V"))
    private Identifier modifyAddStat(Identifier stat)
    {
        if(this.merchant instanceof AbstractGoblinEntity)
        {
            return ModStats.TRADE_WITH_GOBLIN;
        }
        return stat;
    }
}
