package net.hat.gt.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.hat.gt.config.CompatUtil.maxEnchantTextConfig;

// Remapped to yarn by Jab125
@Mixin(Enchantment.class)
public class EnchantmentMixin {
    int level;
    Enchantment enchantment;
    @Inject(method = "getName", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void append(int level, CallbackInfoReturnable<Text> cir) {
        this.level = level;
        this.enchantment = (Enchantment) (Object) this;
    }
    @ModifyVariable(method = "getName", at = @At(value = "RETURN"), index = 2, ordinal = 0)
    private MutableText append(MutableText mutabletext) {
        if (this.level > this.enchantment.getMaxLevel() && !this.enchantment.isCursed() && maxEnchantTextConfig()) {
            mutabletext.formatted(Formatting.LIGHT_PURPLE);
        }
        return mutabletext;
    }
}
