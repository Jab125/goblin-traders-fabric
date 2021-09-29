package net.hat.gt.entities;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
                            //DON'T DELETE G!!!
                            //WE NEED THE G - backup - <G extends AbstractGoblinEntity>
public class GoblinTraderModel<G extends AbstractGoblinEntity> extends CompositeEntityModel<AbstractGoblinEntity> {
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
    private final ModelPart nose;
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
        rotateFactor = (float) entity.getRotationVector().lengthSquared();
        rotateFactor = rotateFactor / 0.2F;
        rotateFactor = rotateFactor * rotateFactor * rotateFactor;
        if(rotateFactor < 1.0F)
        {
            rotateFactor = 1.0F;
        }
        this.rightArm.pitch = (float) (Math.cos(limbAngle * 0.6662F + (float) Math.PI) * 2.0F * limbDistance * 0.5F / rotateFactor);
        this.leftArm.pitch = (float) (Math.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F / rotateFactor);
        this.rightArm.yaw = 0.0F;
        this.rightArm.roll = 0.0F;
        this.leftArm.yaw = 0.0F;
        this.leftArm.roll = 0.0F;
        this.rightLeg.pitch = (float) (Math.cos(limbAngle * 0.6662F) * 1.4F * limbDistance / rotateFactor);
        this.leftLeg.pitch = (float) (Math.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance / rotateFactor);
        this.head.yaw = headYaw * ((float) Math.PI / 180F);
        this.head.pitch = headroll * ((float) Math.PI / 180F);
        this.hood.copyTransform(this.head);

        if(entity.isUsingItem())
        {
            double rotateX = Math.toRadians(-90F + 5F * Math.sin(animationProgress));
            this.rightArm.pitch = (float) rotateX;
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
    }
}
