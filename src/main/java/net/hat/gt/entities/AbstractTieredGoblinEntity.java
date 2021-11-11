package net.hat.gt.entities;

import com.google.common.annotations.Beta;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.hat.gt.init.ModStats;
import net.hat.gt.trades.UpgradedTradeOffer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;
import net.minecraft.world.World;

import static net.hat.gt.init.ModTrades.copyToFastUtilMap;

@Beta
public abstract class AbstractTieredGoblinEntity extends AbstractGoblinEntity {
    private static final TrackedData<? super Integer> XP = DataTracker.registerData(AbstractGoblinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<? super Integer> LVL = DataTracker.registerData(AbstractGoblinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    boolean levelingUp;
    int levelUpTimer;
    public AbstractTieredGoblinEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isLeveledMerchant() {
        return true;
    }
    private void beginTradeWith(PlayerEntity customer) {
        this.prepareOffersFor(customer);
        this.setCurrentCustomer(customer);
        this.sendOffers(customer, this.getDisplayName(), this.getLevel());
    }
    abstract TradeOffers.Factory[] noviceTrades();
    abstract TradeOffers.Factory[] apprenticeTrades();
    abstract TradeOffers.Factory[] journeymanTrades();
    abstract TradeOffers.Factory[] expertTrades();
    abstract TradeOffers.Factory[] masterTrades();


    private void prepareOffersFor(PlayerEntity player) {
        int i = 0;
        if (i != 0) {

            for (TradeOffer tradeOffer : this.getOffers()) {
                tradeOffer.increaseSpecialPrice(-MathHelper.floor((float) i * tradeOffer.getPriceMultiplier()));
            }
        }
    }

    protected void fillRecipes() {
        Int2ObjectMap<TradeOffers.Factory[]> int2ObjectMap = copyToFastUtilMap(ImmutableMap.of(
                1, noviceTrades(), 2, apprenticeTrades(), 3, journeymanTrades(), 4, expertTrades(), 5, masterTrades()));
        //Int2ObjectMap<TradeOffers.Factory[]> int2ObjectMap = TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(VillagerProfession.BUTCHER);
        if (!int2ObjectMap.isEmpty()) {
            TradeOffers.Factory[] factorys = int2ObjectMap.get(this.getLvl());
            if (factorys != null) {
                TradeOfferList tradeOfferList = this.getOffers();
                this.fillRecipesFromPool(tradeOfferList, factorys, 2);
            }
        }
    }

    public int getLvl() {
        return getLevel();
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (this.isAlive() && !this.hasCustomer() && (this.isFireImmune() || !this.isOnFire()) && !isStunned()) {
            if (hand.equals(Hand.MAIN_HAND)) {
                player.incrementStat(ModStats.TRADE_WITH_GOBLIN);
            }
            if (!this.getOffers().isEmpty()) {
                if (!this.world.isClient) {
                    this.beginTradeWith(player);
                }
            }
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }
//    @Override
//    public ActionResult interactMob(PlayerEntity player, Hand hand) {
//        ItemStack itemStack = player.getStackInHand(hand);
//        if (!itemStack.isOf(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isSleeping() && player.isTouchingWater()) {
//            if (this.isBaby()) {
//                //this.sayNo();
//                return ActionResult.success(this.world.isClient);
//            } else {
//                boolean bl = this.getOffers().isEmpty();
//                if (hand == Hand.MAIN_HAND) {
//                    //this.sayNo();
//
//                    player.incrementStat(Stats.TALKED_TO_VILLAGER);
//                }
//
//                if (bl) {
//                    return ActionResult.success(this.world.isClient);
//                } else {
//                    if (!this.world.isClient && !this.getOffers().isEmpty()) {
//                        this.beginTradeWith(player);
//                    }
//
//                    return ActionResult.success(this.world.isClient);
//                }
//            }
//        } else {
//            return super.interactMob(player, hand);
//        }
//    }

    @Override
    public TradeOfferList getOffers() {
        if (this.offers == null) {
            this.offers = new TradeOfferList();
            this.fillRecipes();
        }

        return this.offers;
    }

    @Override
    protected void afterUsing(TradeOffer offer) {
        int i = offer instanceof UpgradedTradeOffer ? ((UpgradedTradeOffer) offer).getPlayerExperience() : offer.getMerchantExperience() * 10;
        //this.experience += offer.getMerchantExperience();
        this.dataTracker.set(XP, (int)this.dataTracker.get(XP) + offer.getMerchantExperience());
        if (this.canLevelUp()) {
            this.levelUpTimer = 40;
            this.levelingUp = true;
            i += 5;
        }

        if (offer.shouldRewardPlayerExperience()) {
            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }

    }
    private boolean canLevelUp() {
        int i = this.getLvl();
        return VillagerData.canLevelUp(i) && getExperience() >= VillagerData.getUpperLevelExperience(i);
    }

    private void levelUp() {
        this.setLevel(this.getLvl() + 1);
        this.fillRecipes();
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        if (!this.hasCustomer() && this.levelUpTimer > 0) {
            --this.levelUpTimer;
            if (this.levelUpTimer <= 0) {
                if (this.levelingUp) {
                    this.levelUp();
                    this.levelingUp = false;
                }

                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0));
            }
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(XP, 0);
        this.dataTracker.startTracking(LVL, 1);
    }


    public int getXP() {
        return (int) this.dataTracker.get(XP);
    }

    public int getLevel() {
        return (int) this.dataTracker.get(LVL);
    }
    public void setLevel(int level) {
        this.dataTracker.set(LVL, level);
    }
    public void setXP(int xp) {
        this.dataTracker.set(XP, xp);
    }

    @Override
    public int getExperience() {
        return getXP();
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Xp", this.getXP());
        nbt.putInt("Lvl", this.getLvl());
        nbt.putInt("LevelUpTimer", this.levelUpTimer);
        nbt.putBoolean("LevelingUp", this.levelingUp);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Xp", 3)) {
            this.setXP(nbt.getInt("Xp"));
        }
        if (nbt.contains("Lvl", 3)) {
            this.setLevel(nbt.getInt("Lvl"));
        }
        if (nbt.contains("LevelUpTimer", 3)) {
            this.levelUpTimer = nbt.getInt("LevelUpTimer");
        }
        if (nbt.contains("LevelingUp", 3)) {
            this.levelingUp = nbt.getBoolean("LevelingUp");
        }
    }
}
