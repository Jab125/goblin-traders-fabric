package net.hat.gt.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*@Mixin(TradeOffers.class)
public class TraderOffersMixin {

    @Final
    @Mutable
    @Shadow
    public static final Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES;

    public static class SellItemFactory implements TradeOffers.Factory {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactory(Block block, int i, int j, int k, int l) {
            this(new ItemStack(block), i, j, k, l);
        }

        public SellItemFactory(Item item, int i, int j, int k) {
            this(new ItemStack(item), i, j, 12, k);
        }

        public SellItemFactory(Item item, int i, int j, int k, int l) {
            this(new ItemStack(item), i, j, k, l);
        }

        public SellItemFactory(ItemStack itemStack, int i, int j, int k, int l) {
            this(itemStack, i, j, k, l, 0.05F);
        }

        public SellItemFactory(ItemStack itemStack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = itemStack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(Items.EMERALD, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    private native static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> immutableMap);
    static {

        GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{
                new TraderOffersMixin.SellItemFactory(Items.GOLDEN_APPLE, 6, 1, 12,1),
                new TraderOffersMixin.SellItemFactory(Items.DIAMOND_AXE, 16, 1, 2,1)}));
        ///BetterWanderingTraderConfig config = AutoConfig.getConfigHolder(BetterWanderingTraderConfig.class).getConfig();
        /*if (BetterWanderingTraderMod.CONFIG.trades.enable_user_added_traded) {
            TradeOffers.Factory[] new_trades = new TradeOffers.Factory[BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.number_of_trades];
            for (int i = 0; i <BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.array.length; i++ ) {
                //Item item = Item.byRawId(config.invisibleTradeFactory.array[i].numeric_id);
                Item item = Registry.ITEM.get(Identifier.tryParse(BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.array[i].identifier));
                int price = BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.array[i].price;
                int count = BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.array[i].count;
                int max_uses = BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.array[i].maxUses;
                int experience = BetterWanderingTraderMod.CONFIG.invisibleTradeFactory.array[i].experience;
                new_trades[i] = new TraderOffersMixin.SellItemFactory(item, price, count, max_uses,experience);
            }

            //TradeOffers.Factory[] final_trades = ArrayUtils.addAll(WANDERING_TRADER_TRADES.get(1),new_trades);
            //WANDERING_TRADER_TRADES.replace(1,final_trades);
            WANDERING_TRADER_TRADES.put(3,new_trades);
        }
    }
}*/

@Mixin(TitleScreen.class)
public class TraderOffersMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        System.out.println("IM WORKING LALALALALALALALALALALALALAA!");
    }
}
