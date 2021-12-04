package net.hat.gt.trades;

import com.jab125.thonkutil.api.tradeoffer.IdentifiableTrade;
import net.hat.gt.GobT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;

public class UpgradedTradeOffer extends TradeOffer implements IdentifiableTrade {
    private final int playerExperience;

    public UpgradedTradeOffer(NbtCompound nbt) {
        super(nbt);
        this.playerExperience = nbt.getInt("playerXp");
    }

    public UpgradedTradeOffer(ItemStack buyItem, ItemStack sellItem, int maxUses, int merchantExperience, float priceMultiplier, int playerExperience) {
        super(buyItem, sellItem, maxUses, merchantExperience, priceMultiplier);
        this.playerExperience = playerExperience;
    }

    public UpgradedTradeOffer(ItemStack firstBuyItem, ItemStack secondBuyItem, ItemStack sellItem, int maxUses, int merchantExperience, float priceMultiplier, int playerExperience) {
        super(firstBuyItem, secondBuyItem, sellItem, maxUses, merchantExperience, priceMultiplier);
        this.playerExperience = playerExperience;
    }

    public UpgradedTradeOffer(ItemStack firstBuyItem, ItemStack secondBuyItem, ItemStack sellItem, int uses, int maxUses, int merchantExperience, float priceMultiplier, int playerExperience) {
        super(firstBuyItem, secondBuyItem, sellItem, uses, maxUses, merchantExperience, priceMultiplier);
        this.playerExperience = playerExperience;
    }

    public UpgradedTradeOffer(ItemStack firstBuyItem, ItemStack secondBuyItem, ItemStack sellItem, int uses, int maxUses, int merchantExperience, float priceMultiplier, int demandBonus, int playerExperience) {
        super(firstBuyItem, secondBuyItem, sellItem, uses, maxUses, merchantExperience, priceMultiplier, demandBonus);
        this.playerExperience = playerExperience;
    }

    /** getPlayerExperience()
     *
     * @return the amount of xp to give to the player
     */
    public int getPlayerExperience() {
        return playerExperience;
    }

    @Override
    public NbtCompound toNbt() {
        NbtCompound nbtCompound = super.toNbt();
        nbtCompound.putInt("playerXp", this.playerExperience);
        nbtCompound.putString("id", GobT.id("upgraded_trade_offer").toString());
        return nbtCompound;
    }

    @Override
    public Identifier getId() {
        return GobT.id("upgraded_trade_offer");
    }
}
