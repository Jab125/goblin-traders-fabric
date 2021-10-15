package net.hat.gt.util;

import net.hat.gt.GobT;

import static com.jab125.thonkutil.util.Util.isEnchantmentDisplaysInstalled;

public class Util {

    public static boolean maxEnchantTextConfig() {
        return GobT.config.MAX_ENCHANTMENT_TEXT && !isEnchantmentDisplaysInstalled();
    }
}
