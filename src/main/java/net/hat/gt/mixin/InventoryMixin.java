package net.hat.gt.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(Inventory.class)
public abstract class InventoryMixin {

    @Shadow public abstract int size();
    @Shadow public abstract ItemStack getStack(int i);
    boolean containsItemStack(ItemStack itemstack) {
        for(int i = 0; i < this.size(); ++i) {
            ItemStack itemStack = this.getStack(i);
            if (itemStack.equals(itemstack) && itemStack.getCount() > 0) {
                return true;
            }
        }

        return false;
    }
}
