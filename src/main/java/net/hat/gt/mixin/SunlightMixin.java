package net.hat.gt.mixin;

import ladysnake.requiem.api.v1.remnant.RemnantState;
import net.fabricmc.loader.api.FabricLoader;
import net.hat.gt.init.ModStatusEffects;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class SunlightMixin {
    @Shadow public abstract boolean isAlive();

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract void sendEquipmentBreakStatus(EquipmentSlot slot);

    @Shadow public abstract void equipStack(EquipmentSlot slot, ItemStack stack);

    @Shadow public abstract Random getRandom();

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void goblintraders$tickMovement(CallbackInfo ci) {
        if ((Object)this instanceof PlayerEntity player && (player.isCreative() || player.isSpectator())) return;
        if ((Object)this instanceof ZombieEntity zombieEntity) {
            if (zombieEntity.burnsInDaylight()) {
                return;
            }
        }
        if (this.isAlive()) {
            boolean bl = this.hasStatusEffect(ModStatusEffects.SUN_PHOBIA) && isAffectedByDaylight();
            if (bl) {
                ItemStack itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
                if (!itemStack.isEmpty()) {
                    if (itemStack.isDamageable()) {
                        itemStack.setDamage(itemStack.getDamage() + this.getRandom().nextInt(2));
                        if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                            this.sendEquipmentBreakStatus(EquipmentSlot.HEAD);
                            this.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    bl = false;
                }

                if (bl) {
                    ((LivingEntity)(Object)this).setOnFireFor(8);
                }
            }
        }
    }


    @Unique
    protected boolean isAffectedByDaylight() {

        if (FabricLoader.getInstance().isModLoaded("trinkets")) {
            try {
                if (((Object) this) instanceof PlayerEntity player && ((RemnantState) player).isIncorporeal()) return false;
            } catch (Exception exception) {
                // REQUIEM IS PROBABLY NOT INSTALLED ON SERVER
            }

        }

        if (((LivingEntity)(Object)this).world.isDay() && !((LivingEntity)(Object)this).world.isClient) {
            float f = ((LivingEntity)(Object)this).getBrightnessAtEyes();
            BlockPos blockPos = new BlockPos(((LivingEntity)(Object)this).getX(), ((LivingEntity)(Object)this).getEyeY(), ((LivingEntity)(Object)this).getZ());
            boolean bl = ((LivingEntity)(Object)this).isWet() || ((LivingEntity)(Object)this).inPowderSnow || ((LivingEntity)(Object)this).wasInPowderSnow;
            if (f > 0.5F && ((LivingEntity)(Object)this).getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && ((LivingEntity)(Object)this).world.isSkyVisible(blockPos)) {
                return true;
            }
        }

        return false;
    }
}
