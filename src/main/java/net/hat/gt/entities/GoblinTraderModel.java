package net.hat.gt.entities;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
                            //DON'T DELETE G!!!
                            //WE NEED THE G - backup - <G extends AbstractGoblinEntity>
@SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
public class GoblinTraderModel<G extends AbstractGoblinEntity> extends CompositeEntityModel<AbstractGoblinEntity> implements ModelWithArms, ModelWithHead {
    private final ModelPart root;
    private final ModelPart head;
    public final ModelPart hood;
    private final ModelPart body;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart nose; // leaving this non-coverted for future use
    private final ModelPart bag;

    public GoblinTraderModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.hood = root.getChild("hood");
        this.nose = this.head.getChild("nose");
        this.rightEar = this.head.getChild("rightEar");
        this.leftEar = this.head.getChild("leftEar");
        this.body = root.getChild(EntityModelPartNames.BODY);
        this.bag = this.body.getChild("bag");
        this.rightArm = root.getChild(EntityModelPartNames.RIGHT_ARM);
        this.leftArm = root.getChild(EntityModelPartNames.LEFT_ARM);
        this.leftLeg = root.getChild(EntityModelPartNames.LEFT_LEG);
        this.rightLeg = root.getChild(EntityModelPartNames.RIGHT_LEG);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        ModelPartData body = root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(12,12).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        body.addChild("bag", ModelPartBuilder.create().uv(0,20).cuboid(-2.5F, -2.0F, 2.0F, 5.0F, 7.0F, 3.0F), ModelTransform.NONE);
        ModelPartData head = root.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0,0).cuboid(-4.0F, -6.0F, -3.0F, 8.0F, 6.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        head.addChild("leftEar", ModelPartBuilder.create().uv(0,8).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F), ModelTransform.pivot(4.0F, -3.0F, 1.0F));
        head.addChild("rightEar", ModelPartBuilder.create().uv(8,8).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F), ModelTransform.pivot(-4.0F, -3.0F, 1.0F));
        head.addChild("nose", ModelPartBuilder.create().uv(22, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F), ModelTransform.pivot(0.0F, -3.0F, -3.0F));
        root.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(30,0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(-3.0F, 17.0F, 0.0F));
        root.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(38,0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(3.0F, 17.0F, 0.0F));
        root.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(36,9).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(1.0F, 20.0F, 0.0F));
        root.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(26,9).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(-1.0F, 20.0F, 0.0F));
        root.addChild("hood", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, -6.0F, -3.0F, 8.0F, 8.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        return TexturedModelData.of(modelData,46,46);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.root);
    }

    @Override
    public void setAngles(AbstractGoblinEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headroll) {
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

        if(entity.isUsingItem())
        {
            double rotateX = Math.toRadians(-90F + 5F * Math.sin(animationProgress));
            this.leftArm.pitch = (float) rotateX;
            this.rightArm.pitch = (float) Math.toRadians(-90F);
            this.rightLeg.yaw = (float) Math.toRadians(25F);
            this.leftLeg.pitch = (float) Math.toRadians(-90F);
            this.leftLeg.yaw = (float) Math.toRadians(-25F);
        }
        else
        {
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
        switch(arm)
        {
            case LEFT -> {
                this.leftArm.rotate(matrices);
                matrices.translate(-0.235, -0.15, 0.25);
                matrices.scale(0.75F, 0.75F, 0.75F);
            }
            case RIGHT -> {
                this.rightArm.rotate(matrices);
                matrices.translate(0.235, -0.15, 0.25);
                matrices.scale(0.75F, 0.75F, 0.75F);
            }
        }
    }


    @Override
    public ModelPart getHead() {
        return this.head;
    }
}
