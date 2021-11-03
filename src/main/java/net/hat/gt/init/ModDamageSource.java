package net.hat.gt.init;

import net.minecraft.entity.damage.DamageSource;

public class ModDamageSource extends DamageSource {
    public static final DamageSource INVALIDATED = (new ModDamageSource("invalidated")).setBypassesArmor().setOutOfWorld();
    public ModDamageSource(String name) {
        super(name);
    }
}
