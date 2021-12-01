package net.hat.gt.entities;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

import java.util.NoSuchElementException;

@Environment(EnvType.CLIENT)
                            //I will murder several penguins if you remove the G again
@SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
public class GoblinTraderModel<G extends AbstractGoblinEntity> extends CompositeEntityModel<AbstractGoblinEntity> implements ModelWithArms, ModelWithHead {
    private final ModelPart head;
    public final ModelPart hood;
    private final ModelPart body;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart bag;
    private AbstractGoblinEntity entity;

    public GoblinTraderModel() {
        textureHeight = 46;
        textureWidth = 46;
        head = new ModelPart(this, 0, 0);
        head.addCuboid(-4.0F, -6.0F, -3.0F, 8.0F, 6.0F, 6.0F);
        head.setPivot(0.0F, 16.0F, 0.0F);

        this.hood = new ModelPart(this, 0, 32);
        this.hood.addCuboid(-4.0F, -6.0F, -3.0F, 8.0F, 8.0F, 6.0F, 0.5F);
        this.hood.setPivot(0.0F, 16.0F, 0.0F);
        //head.addChild(hood);
        // leaving this non-coverted for future use

        ModelPart nose = new ModelPart(this, 22, 0);
        nose.addCuboid(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F);
        nose.setPivot(0.0F, -3.0F, -3.0F);
        head.addChild(nose);

        this.rightEar = new ModelPart(this, 8,8);
        this.rightEar.addCuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F);
        this.rightEar.setPivot(-4.0F, -3.0F, 1.0F);
        head.addChild(rightEar);

        this.leftEar = new ModelPart(this, 0,8);
        this.leftEar.addCuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F);
        this.leftEar.setPivot(4.0F, -3.0F, 1.0F);
        head.addChild(leftEar);

        this.body = new ModelPart(this, 12, 12);
        this.body.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F);
        this.body.setPivot(0.0F, 16.0F, 0.0F);

        this.bag = new ModelPart(this,0,20);
        this.bag.addCuboid(-2.5F, -2.0F, 2.0F, 5.0F, 7.0F, 3.0F);
        this.bag.setPivot(0.0F, 0.0F, 0.0F);
        this.bag.yaw = 0.0F;
        this.bag.pitch = 0.0F;
        this.bag.roll = 0.0F;
        this.body.addChild(bag);

        this.rightArm = new ModelPart(this,30,0);
        this.rightArm.addCuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F);
        this.rightArm.setPivot(-3.0F, 17.0F, 0.0F);

        this.leftArm = new ModelPart(this,38,0);
        this.leftArm.addCuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F);
        this.leftArm.setPivot(3.0F, 17.0F, 0.0F);

        this.leftLeg = new ModelPart(this,36,9);
        this.leftLeg.addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F);
        this.leftLeg.setPivot(1.0F, 20.0F, 0.0F);

        this.rightLeg = new ModelPart(this,26,9);
        this.rightLeg.addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F);
        this.rightLeg.setPivot(-1.0F, 20.0F, 0.0F);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.head, this.hood, this.body, this.rightArm, this.leftArm, this.leftLeg, this.rightLeg);
    }

//    public static TexturedModelData getTexturedModelData() {
//        ModelData modelData = new ModelData();
//        ModelPartData root = modelData.getRoot();
//        ModelPartData body = root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(12,12).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
//        body.addChild("bag", ModelPartBuilder.create().uv(0,20).cuboid(-2.5F, -2.0F, 2.0F, 5.0F, 7.0F, 3.0F), ModelTransform.NONE);
//        //ModelPartData head = root.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0,0).cuboid(-4.0F, -6.0F, -3.0F, 8.0F, 6.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
//        //head.addChild("leftEar", ModelPartBuilder.create().uv(0,8).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F), ModelTransform.pivot(4.0F, -3.0F, 1.0F));
//        //head.addChild("rightEar", ModelPartBuilder.create().uv(8,8).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F), ModelTransform.pivot(-4.0F, -3.0F, 1.0F));
//        head.addChild("nose", ModelPartBuilder.create().uv(22, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F), ModelTransform.pivot(0.0F, -3.0F, -3.0F));
//        root.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(38,0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(3.0F, 17.0F, 0.0F));
    //    root.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(36,9).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(1.0F, 20.0F, 0.0F));
   //     root.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(26,9).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(-1.0F, 20.0F, 0.0F));
//        //root.addChild("hood", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, -6.0F, -3.0F, 8.0F, 8.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
//        return TexturedModelData.of(modelData,46,46);
//    }

    @Override
    public void setAngles(AbstractGoblinEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headroll) {
        this.entity = entity;
        this.leftEar.yaw = 0.7854F;
        this.rightEar.yaw = -0.7854F;
        float rotateFactor;
        rotateFactor = (float) entity.getVelocity().lengthSquared();
        rotateFactor = rotateFactor / 0.2F;
        rotateFactor = rotateFactor * rotateFactor * rotateFactor;
        if(rotateFactor < 1.0F)
        {
            rotateFactor = 1.0F;
        }
        this.rightArm.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 2.0F * limbDistance * 0.5F / rotateFactor;
        this.leftArm.pitch = MathHelper.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F / rotateFactor;
        this.rightArm.yaw = 0.0F;
        this.rightArm.roll = 0.0F;
        this.leftArm.yaw = 0.0F;
        this.leftArm.roll = 0.0F;
        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance / rotateFactor;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance / rotateFactor;
        this.head.yaw = headYaw * ((float) Math.PI / 180F);
        this.head.pitch = headroll * ((float) Math.PI / 180F);
        this.hood.copyTransform(this.head);
        this.bag.pitch = (float) Math.toRadians(0F);
        this.bag.pivotY = 0;
        this.bag.pivotZ = 0;
        this.leftArm.pivotZ = 0;
        this.leftArm.pivotY = 17;
        this.rightArm.pivotZ = 0;
        this.rightArm.pivotY = 17;

        if(this.handSwingProgress > 0.0F)
        {
            ModelPart arm = this.rightArm;
            float progress = this.handSwingProgress;
            this.body.yaw = MathHelper.sin(MathHelper.sqrt(progress) * ((float) Math.PI * 2F)) * 0.2F;
            this.rightArm.yaw += this.body.yaw;
            this.leftArm.yaw += this.body.yaw;
            this.leftArm.pitch += this.body.yaw;
            progress = 1.0F - this.handSwingProgress;
            progress = progress * progress;
            progress = progress * progress;
            progress = 1.0F - progress;
            float f2 = MathHelper.sin(progress * (float) Math.PI);
            float f3 = MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -(this.head.pitch - 0.7F) * 0.75F;
            arm.pitch = (float) ((double) arm.pitch - ((double) f2 * 1.2D + (double) f3));
            arm.yaw += this.body.yaw * 2.0F;
            arm.roll += MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -0.4F;
        }

        if(entity.isUsingItem()) {
            double rotateX = Math.toRadians(-90F + 5F * Math.sin(animationProgress));
            this.leftArm.pitch = (float) rotateX;
            this.rightArm.pitch = (float) rotateX;
            this.rightLeg.yaw = (float) Math.toRadians(25F);
            this.leftLeg.pitch = (float) Math.toRadians(-90F);
            this.leftLeg.yaw = (float) Math.toRadians(-25F);
            this.rightLeg.pitch = (float) Math.toRadians(-90F);
        } else {
            if (entity.isHoldingItem(Hand.MAIN_HAND)) {
                if (entity.getMainArm().equals(Arm.RIGHT)) {
                    this.rightArm.pitch = (float) Math.toRadians(-50F);
                } else {
                    this.leftArm.pitch = (float) Math.toRadians(-50F);
                }
            }
            if (entity.isHoldingItem(Hand.OFF_HAND)) {
                if (entity.getMainArm().equals(Arm.LEFT)) {
                    this.rightArm.pitch = (float) Math.toRadians(-50F);
                } else {
                    this.leftArm.pitch = (float) Math.toRadians(-50F);
                }
            }
            this.rightLeg.yaw = (float) Math.toRadians(0);
            this.leftLeg.yaw = (float) Math.toRadians(0);
        }

        if(entity.isRaining() && !entity.isStunned() && !entity.isUsingItem()){
            this.rightArm.pitch = (float) Math.toRadians(-140F);
            this.rightArm.yaw = (float) Math.toRadians(-20F);
            this.leftArm.pitch = (float) Math.toRadians(-140F);
            this.leftArm.yaw = (float) Math.toRadians(20F);
            this.bag.pitch = (float) Math.toRadians(130F);
            this.bag.pivotY = -3;
            this.bag.pivotZ = -3;
            this.leftArm.pivotZ = -3;
            this.leftArm.pivotY = 15;
            this.rightArm.pivotZ = -3;
            this.rightArm.pivotY = 15;
        }
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices)
    {
        if (this.entity != null) {
            if (!this.entity.isUsingItem()) {
                if (arm.equals(Arm.LEFT)) {
                    this.leftArm.rotate(matrices);
                    matrices.translate(-0.10 / 2, -0.15, 0.25);
                }
                if (arm.equals(Arm.RIGHT)) {
                    this.rightArm.rotate(matrices);
                    matrices.translate(0.10 / 2, -0.15, 0.25);
                }
                matrices.scale(0.75F, 0.75F, 0.75F);
                return;
            }
        }

        if (arm.equals(Arm.LEFT)) {
            this.leftArm.rotate(matrices);
            if (this.entity.isHoldingItem(Hand.MAIN_HAND) && this.entity.isHoldingItem(Hand.OFF_HAND)) {
                matrices.translate(-0.10 / 2, -0.15, 0.25);
            } else {
                matrices.translate(-0.235, -0.15, 0.25);
            }
            matrices.scale(0.75F, 0.75F, 0.75F);
        }
        if (arm.equals(Arm.RIGHT)) {
            this.rightArm.rotate(matrices);
            if (this.entity.isHoldingItem(Hand.MAIN_HAND) && this.entity.isHoldingItem(Hand.OFF_HAND)) {
                matrices.translate(0.10 / 2, -0.15, 0.25);
            } else {
                matrices.translate(0.235, -0.15, 0.25);
            }
            matrices.scale(0.75F, 0.75F, 0.75F);
        }
    }



    @Override
    public ModelPart getHead() {
        return this.head;
    }

    private ModelPart getChild(String name, ModelPart modelPar) {
        ModelPart modelPart = (ModelPart)modelPar.children.get(1);
        if (modelPart == null) {
            throw new NoSuchElementException("Can't find part " + name);
        } else {
            return modelPart;
        }
    }
}
