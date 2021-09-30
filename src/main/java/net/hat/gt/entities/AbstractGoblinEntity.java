package net.hat.gt.entities;

import net.hat.gt.entities.ai.*;
import net.hat.gt.init.ModSounds;
import net.hat.gt.init.ModStats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Npc;
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
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class AbstractGoblinEntity extends MerchantEntity implements Npc {

    @Nullable
    private BlockPos wanderTarget;
    static Predicate<ItemEntity> FAVOURITE_FOOD;
    private Set<UUID> tradedCustomers = new HashSet<>();

    //register Goblin to Exist
    public AbstractGoblinEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FirePanicGoal(this, 0.5F));
        this.goalSelector.add(2, new TradeWithPlayerGoal(this));
        this.goalSelector.add(3, new LookAtCustomerGoal(this));
        this.goalSelector.add(4, new AttackRevengeTargetGoal(this));
        this.goalSelector.add(5, new FollowPotentialCustomerGoal(this));
        this.goalSelector.add(6, new FindFavouriteFoodGoal(this));
        this.goalSelector.add(7, new TemptGoal(this, 0.5, Ingredient.ofItems(this.getFavouriteFood().getItem()), false));
        this.goalSelector.add(8, new EatFavouriteFoodGoal(this));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.35D));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
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
        if (this.isAlive() && !this.hasCustomer() && (this.isFireImmune() || !this.isOnFire())) {
            if (hand == Hand.MAIN_HAND) {
                player.incrementStat(ModStats.TRADE_WITH_GOBLIN);
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
    public ItemStack eatFood(World level, ItemStack stack) {
        if(stack.getItem() == this.getFavouriteFood().getItem() && stack.getItem().getFoodComponent() != null)
        {
            this.setHealth(this.getHealth() + stack.getItem().getFoodComponent().getHunger());
        }
        return super.eatFood(level, stack);
    }

    @Override
    protected void spawnConsumptionEffects(ItemStack stack, int count)
    {
        if(!stack.isEmpty() && this.isUsingItem())
        {
            if(stack.getUseAction() == UseAction.EAT)
            {
                this.spawnFoodParticles(stack, count);
                this.playSound(this.getEatSound(stack), 0.5F + 0.5F * (float) this.getRandom().nextInt(2), (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected void spawnFoodParticles(ItemStack stack, int count)
    {
        for(int i = 0; i < count; ++i)
        {
            Vec3d frontPosition = Vec3d.fromPolar(0F, this.bodyYaw).multiply(0.25);
            frontPosition = frontPosition.add(0, 0.35, 0);
            frontPosition = frontPosition.add(this.getPos());
            Vec3d motion = new Vec3d(this.getRandom().nextDouble() * 0.2 - 0.1, 0.1, this.getRandom().nextDouble() * 0.2 - 0.1);
            if(this.world instanceof ServerWorld)
            {
                //((ServerWorld) this.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, stack) {}, frontPosition.x, frontPosition.y, frontPosition.z, 1, motion.x, motion.y + 0.05D, motion.z, 0.0D);
            }
            else
            {
                //this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), frontPosition.x, frontPosition.y, frontPosition.z, motion.x, motion.y + 0.05D, motion.z);
            }
        }
    }

    @Override
    protected void fillRecipes() {
        TradeOffers.Factory[] factorys = TradeOffers.WANDERING_TRADER_TRADES.get(1);
        TradeOffers.Factory[] factorys2 = TradeOffers.WANDERING_TRADER_TRADES.get(2);
        if (factorys != null && factorys2 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factorys, 7);
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
    protected SoundEvent getAmbientSound() {
        return ModSounds.IDLE_GRUNT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.IDLE_GRUNT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.IDLE_GRUNT;
    }

    @Override
    protected SoundEvent getTradingSound(boolean sold) {
        return (sold ? ModSounds.IDLE_GRUNT : ModSounds.ANNOYED_GRUNT);
    }

    public boolean isPreviousCustomer(PlayerEntity player)
    {
        return this.tradedCustomers.contains(player.getUuid());
    }

    public abstract ItemStack getFavouriteFood();

    public abstract boolean canAttackBack();


    static {
        FAVOURITE_FOOD = (item) -> !item.cannotPickup() && item.isAlive() && item.getStack().isOf(Items.APPLE);
    }
}