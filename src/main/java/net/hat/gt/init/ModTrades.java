package net.hat.gt.init;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ModTrades {
    public static Int2ObjectMap<TradeOffers.Factory[]> GOBLIN_TRADER_TRADES;
    public static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> trades) {
        return new Int2ObjectOpenHashMap<>(trades);
    }
    static {
        GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
                /**
                 * Right so if you're reading through the code and are planning on making an addon or something to add custom trades -
                 * at least before I do... this is where the trades are done. They are easily accessable here, and you can put your own
                 * in via a mixin into this. I've made my own ItemFactory called TradeWithoutEmerald, this is due to default
                 * tradeFactories all using emeralds, so I needed my own.
                 *
                 * The default format is:PaymentItem, Price, SellItem, SellCount, MaxUses, ExperienceGained
                 *
                 * There is also a second format if you wish to add a second Item -
                 * The format is: PaymentItem, Price, SecondItem, SecondPrice, SellItem, SellCount, MaxUses, ExperienceGained
                 *
                 * I've also went ahead and made everything public in here, so feel free to access it without an accesswidener :)
                 */
                /* ************************************************************************************** *
                 *                                     COMMON                                             *
                 * ************************************************************************************** */
                   1, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.APPLE, 1, Items.EMERALD, 1, 24, 10),
                        new TradeWithoutEmerald(Items.IRON_ORE, 1, Items.IRON_INGOT, 2, 30, 20),
                        new TradeWithoutEmerald(Items.GOLD_ORE, 2, Items.GOLD_INGOT, 3, 30, 30),
                        new TradeWithoutEmerald(Items.ROTTEN_FLESH, 4, Items.COAL, 1, 24, 15),
                        new TradeWithoutEmerald(Items.GRAVEL, 1, Items.FLINT, 2, 32, 15),
                        new TradeWithoutEmerald(Items.COBBLESTONE, 32, Items.EMERALD, 1, 128, 15)
                /* ************************************************************************************** *
                 *                                     UNCOMMON                                           *
                 * ************************************************************************************** */
                }, 2, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.GUNPOWDER, 2, 32, 20),
                        new TradeWithoutEmerald(Items.TURTLE_EGG, 1, Items.EMERALD, 6, 2, 50),
                        new TradeWithoutEmerald(Items.PUFFERFISH_BUCKET, 1, Items.EMERALD, 8, 4, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 8, Items.SPONGE, 8, 4, 30),
                        new TradeWithoutEmerald(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 4 ,Items.CHIPPED_ANVIL, 1, 32, 20),
                        new TradeWithoutEmerald(Items.CLAY_BALL, 1, Items.TERRACOTTA, 2, 64, 20),
                        new TradeWithoutEmerald(Items.LEATHER, 1, Items.PAPER, 3, Items.BOOK, 2, 32, 20),
                /* ************************************************************************************** *
                 *                                      RARE                                              *
                 * ************************************************************************************** */
                }, 3, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.EMERALD, 3, Items.EXPERIENCE_BOTTLE, 1, 64, 40),
                        new TradeWithoutEmerald(Items.EMERALD, 24, Items.PAPER, 8, Items.NAME_TAG, 1, 2, 40),
                        new TradeWithoutEmerald(Items.PACKED_ICE, 4, Items.BLUE_ICE, 1, 64, 40),
                        //new TradeWithoutEmerald(Items.EMERALD, 3, Items.ENCHANTED_BOOK, 1, 64, 40),//Watch this one
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_CAT, 1, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_BLOCKS, 1, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_CHIRP, 1, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_MELLOHI, 1, 1, 50),
                        new TradeWithoutEmerald(Items.EMERALD, 32, Items.MUSIC_DISC_STAL, 1, 1, 50)
                /* ************************************************************************************** *
                 *                                    LEGENDARY                                           *
                 * ************************************************************************************** */

                }, 4, new TradeOffers.Factory[]{
                        //new TradeWithoutEmerald(Items.DRAGON_HEAD, 5, Items.DIAMOND_PICKAXE, 1, Items.DIAMOND_PICKAXE, 1, 64, 40), //Fortune + Unbreaking
                }
                ));
    }
}
class TradeWithoutEmerald implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final ItemStack secondBuy;
    private final int secondPrice;
    private final int price;
    private final ItemStack sell;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;
            // This one is default. One Item Input -> One Output
    public TradeWithoutEmerald(ItemConvertible item, int $, Item sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item);
        this.secondPrice = 0;
        this.sell = new ItemStack(sellItem);
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }
            // This one is if you want to have a second Item required for the trade.
    public TradeWithoutEmerald(ItemConvertible item, int $, ItemConvertible item2, int sC, Item sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = $;
        this.secondBuy = new ItemStack(item2);
        this.secondPrice = sC;
        this.sell = new ItemStack(sellItem);
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        return new TradeOffer(new ItemStack(this.firstBuy.getItem(), this.price), new ItemStack(this.secondBuy.getItem(), this.secondPrice), new ItemStack(this.sell.getItem(), this.sellCount), this.maxUses, this.experience, this.multiplier);
    }
}
