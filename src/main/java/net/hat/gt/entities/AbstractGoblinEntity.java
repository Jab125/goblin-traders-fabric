package net.hat.gt.entities;

import net.hat.gt.init.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.Npc;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AbstractGoblinEntity extends MerchantEntity implements Npc {

    //register Goblin to Exist
    public AbstractGoblinEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new StopFollowingCustomerGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5F));
        //this.goalSelector.add(2, new TradeWithPlayerGoal(this));
        this.goalSelector.add(3, new LookAtCustomerGoal(this));
        //this.goalSelector.add(4, new AttackRevengeTargetGoal(this));
        //this.goalSelector.add(5, new FollowPotentialCustomerGoal(this));
        //this.goalSelector.add(6, new FindFavouriteFoodGoal(this));
        //this.goalSelector.add(7, new TemptGoal(this, 0.4D, Ingredient.of(this.getFavouriteFood()), false));
        //this.goalSelector.add(8, new EatFavouriteFoodGoal(this));
        //this.goalSelector.add(8, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.35D));
        //this.goalSelector.add(10, new InteractGoal(this, Player.class, 4.0F, 1.0F));
        this.goalSelector.add(11, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }



    @Override
    protected void afterUsing(TradeOffer offer) {
        if (offer.shouldRewardPlayerExperience()) {
            int i = 3 + this.random.nextInt(4);
            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.isAlive() && !this.hasCustomer()) {
            if (hand == Hand.MAIN_HAND) {
                player.incrementStat(Stats.TALKED_TO_VILLAGER);
            }
            if (this.getOffers().isEmpty()) {
                return ActionResult.success(this.world.isClient);
            } else {
                if (!this.world.isClient) {
                    this.setCurrentCustomer(player);
                    this.sendOffers(player, this.getDisplayName(), 1);
                }
                return ActionResult.success(this.world.isClient);
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    protected void fillRecipes() {
        TradeOffers.Factory[] factorys = TradeOffers.WANDERING_TRADER_TRADES.get(1);
        TradeOffers.Factory[] factorys2 = TradeOffers.WANDERING_TRADER_TRADES.get(2);
        if (factorys != null && factorys2 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factorys, 5);
            int i = this.random.nextInt(factorys2.length);
            TradeOffers.Factory factory = factorys2[i];
            TradeOffer tradeOffer = factory.create(this, this.random);
            if (tradeOffer != null) {
                tradeOfferList.add(tradeOffer);
            }

        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean canRefreshTrades() {
        return super.canRefreshTrades();
    }

    @Override
    public void sendOffers(PlayerEntity player, Text test, int levelProgress) {
        super.sendOffers(player, test, levelProgress);
    }

    @Override
    public boolean isLeveledMerchant() {
        return false;
    }

    public static ItemStack getFavouriteFood() {
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.IDLE_GRUNT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.IDLE_GRUNT;
    }
    @Override
    protected SoundEvent getTradingSound(boolean sold) {
        return (sold ? ModSounds.IDLE_GRUNT : ModSounds.ANNOYED_GRUNT);
    }
}