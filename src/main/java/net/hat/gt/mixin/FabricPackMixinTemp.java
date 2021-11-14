package net.hat.gt.mixin;

import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ResourceManagerHelperImpl.class)
public class FabricPackMixinTemp {
    @ModifyVariable(method = "registerBuiltinResourcePack", at = @At(value = "STORE" ,target = "Lnet/fabricmc/fabric/impl/resource/loader/ResourceManagerHelperImpl;registerBuiltinResourcePack(Lnet/minecraft/util/Identifier;Ljava/lang/String;Lnet/fabricmc/loader/api/ModContainer;Lnet/fabricmc/fabric/api/resource/ResourcePackActivationType;)Z"), index = 0, ordinal = 2, remap = false)
    private static String changeName(String name) {
        System.out.println("name: " + name);
        if (name.equals("goblintraders/gobtvanillaish")) {
            return "Vanilla-ish Trades";
        }
        return name;
    }
}
