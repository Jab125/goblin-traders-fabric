package net.hat.gt.entities;

import net.hat.gt.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractGoblinEntity extends MerchantEntity implements Npc {

    @Nullable
    private BlockPos wanderTarget;
    static Predicate<ItemEntity> FAVOURITE_FOOD;

    //register Goblin to Exist
    public AbstractGoblinEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new StopFollowingCustomerGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5F));
        //this.goalSelector.add(4, new AttackRevengeTargetGoal(this));
        this.goalSelector.add(2, new PlayerPersistanceGoal(this, 2.0D, 0.35D));
        this.goalSelector.add(3, new FindFavouriteFoodGoal());
        this.goalSelector.add(7, new TemptGoal(this, 0.5, Ingredient.ofItems(this.getFavouriteFood().getItem()), false));
        //this.goalSelector.add(8, new EatFavouriteFoodGoal(this));
        //this.goalSelector.add(8, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.35D));
        //this.goalSelector.add(10, new InteractGoal(this, Player.class, 4.0F, 1.0F));
        this.goalSelector.add(5, new LookAtCustomerGoal(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
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
        if (this.isAlive() && !this.hasCustomer()) {
            if (hand == Hand.MAIN_HAND) {
                player.incrementStat(Stats.TALKED_TO_VILLAGER);
            }
            if (!this.getOffers().isEmpty()) {
                if (!this.world.isClient) {
                    this.setCurrentCustomer(player);
                    this.sendOffers(player, this.getDisplayName(), 1);
                }
            }
            return ActionResult.success(this.world.isClient);
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
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.wanderTarget != null) {
            nbt.put("WanderTarget", NbtHelper.fromBlockPos(this.wanderTarget));
        }

    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("WanderTarget")) {
            this.wanderTarget = NbtHelper.toBlockPos(nbt.getCompound("WanderTarget"));
        }

        this.setBreedingAge(Math.max(0, this.getBreedingAge()));
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

    @Override
    protected SoundEvent getAmbientSound() {return ModSounds.IDLE_GRUNT;}
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {return ModSounds.IDLE_GRUNT;}
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.IDLE_GRUNT;
    }
    @Override
    protected SoundEvent getTradingSound(boolean sold) {
        return (sold ? ModSounds.IDLE_GRUNT : ModSounds.ANNOYED_GRUNT);
    }
    public void setWanderTarget(@Nullable BlockPos pos) {
        this.wanderTarget = pos;
    }
    @Nullable
    BlockPos getWanderTarget() {
        return this.wanderTarget;
    }

    class PlayerPersistanceGoal extends Goal {
        final AbstractGoblinEntity trader;
        final double proximityDistance;
        final double speed;

        PlayerPersistanceGoal(AbstractGoblinEntity trader, double proximityDistance, double speed) {
            this.trader = trader;
            this.proximityDistance = proximityDistance;
            this.speed = speed;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        public void stop() {
            AbstractGoblinEntity.this.navigation.stop();
            this.trader.setWanderTarget(null);
        }

        public boolean canStart() {
            BlockPos blockPos = this.trader.getWanderTarget();
            return blockPos != null && this.isTooFarFrom(blockPos, this.proximityDistance);
        }

        public void tick() {
            BlockPos blockPos = this.trader.getWanderTarget();
            if (blockPos != null && AbstractGoblinEntity.this.navigation.isIdle()) {
                if (this.isTooFarFrom(blockPos, 16.0D)) {
                    Vec3d vec3d = (new Vec3d((double)blockPos.getX() - this.trader.getX(), (double)blockPos.getY() - this.trader.getY(), (double)blockPos.getZ() - this.trader.getZ())).normalize();
                    Vec3d vec3d2 = vec3d.multiply(10.0D).add(this.trader.getX(), this.trader.getY(), this.trader.getZ());
                    AbstractGoblinEntity.this.navigation.startMovingTo(vec3d2.x, vec3d2.y, vec3d2.z, this.speed);
                } else {
                    AbstractGoblinEntity.this.navigation.startMovingTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), this.speed);
                }
            }

        }

        private boolean isTooFarFrom(BlockPos pos, double proximityDistance) {
            return !pos.isWithinDistance(this.trader.getPos(), proximityDistance);
        }
    }


    public abstract ItemStack getFavouriteFood();
    class FindFavouriteFoodGoal extends Goal {
        public FindFavouriteFoodGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        public boolean canStart() {
            if (!AbstractGoblinEntity.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else if (AbstractGoblinEntity.this.getTarget() == null && AbstractGoblinEntity.this.getAttacker() == null) {
                 if (AbstractGoblinEntity.this.getRandom().nextInt(10) != 0) {
                    return false;
                } else {
                    List<ItemEntity> list = AbstractGoblinEntity.this.world.getEntitiesByClass(ItemEntity.class, AbstractGoblinEntity.this.getBoundingBox().expand(8.0D, 8.0D, 8.0D), AbstractGoblinEntity.FAVOURITE_FOOD);
                    return !list.isEmpty() && AbstractGoblinEntity.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
                }
            } else {
                return false;
            }
        }

        public void tick() {
            List<ItemEntity> list = AbstractGoblinEntity.this.world.getEntitiesByClass(ItemEntity.class, AbstractGoblinEntity.this.getBoundingBox().expand(8.0D, 8.0D, 8.0D), AbstractGoblinEntity.FAVOURITE_FOOD);
            ItemStack itemStack = AbstractGoblinEntity.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (itemStack.isEmpty() && !list.isEmpty()) {
                AbstractGoblinEntity.this.getNavigation().startMovingTo(list.get(0), 0.5);
            }

        }

        public void start() {
            List<ItemEntity> list = AbstractGoblinEntity.this.world.getEntitiesByClass(ItemEntity.class, AbstractGoblinEntity.this.getBoundingBox().expand(8.0D, 8.0D, 8.0D), AbstractGoblinEntity.FAVOURITE_FOOD);
            if (!list.isEmpty()) {
                AbstractGoblinEntity.this.getNavigation().startMovingTo(list.get(0), 0.5);
            }

        }
    }

    static {
        FAVOURITE_FOOD = (item) -> !item.cannotPickup() && item.isAlive() && item.getStack().isOf(Items.APPLE);
        //NEED TO GO BACK AND ADD CARROTS, ECT...
    }
}