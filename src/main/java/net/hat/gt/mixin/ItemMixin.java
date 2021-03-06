package net.hat.gt.mixin;

import net.hat.gt.GobT;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

/**
 * Add a tiny little easter egg
 */
@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract String getTranslationKey();

    @Inject(method = "appendTooltip", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (stack.isOf(Items.ACACIA_BOAT) && GobT.config.EASTER_EGGS) {
            tooltip.add(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.GRAY));
        }
    }
}
