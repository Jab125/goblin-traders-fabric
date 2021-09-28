package net.hat.gt.entities;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CompositeEntityModel;

@Environment(EnvType.CLIENT)
                            //DON'T DELETE G!!!
                            //WE NEED THE G - backup - <G extends AbstractGoblinEntity>
public class GoblinTraderModel<G extends AbstractGoblinEntity> extends CompositeEntityModel<AbstractGoblinEntity> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public GoblinTraderModel(ModelPart root) {
        this.head = root.getChild("head");
        this.rightEar = this.head.getChild("rightEar");
        this.leftEar = this.head.getChild("leftEar");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("rightArm");
        this.leftArm = root.getChild("leftArm");
        this.leftLeg = root.getChild("leftLeg");
        this.rightLeg = root.getChild("rightLeg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("body", ModelPartBuilder.create().uv(0,20).cuboid(-2.5F, -9.5F, 2.0F, 5.0F, 7.0F, 3.0F).uv(12,12).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
        ModelPartData modelPartData1 = modelPartData.addChild("head", ModelPartBuilder.create().uv(0,0).cuboid(-4.0F, -14.0F, -3.0F, 8.0F, 6.0F, 6.0F).uv(0,32).cuboid(-4.0F, -14.0F, -3.0F, 8.0F, 8.0F, 6.0F, new Dilation(0.5F)).uv(22,0).cuboid(-1.0F, -11.0F, -5.0F, 2.0F, 4.0F, 2.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
        modelPartData1.addChild("leftEar", ModelPartBuilder.create().uv(0,8).cuboid(2.0F, -13.0F, 3.0F, 0.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
        modelPartData1.addChild("rightEar", ModelPartBuilder.create().uv(8,8).cuboid(-2.0F, -13.0F, 3.0F, 0.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
        modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(30,0).cuboid(-4.0F, -8.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
        modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(38,0).cuboid(2.0F, -8.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
        modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(36,9).cuboid(0.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
        modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(26,9).cuboid(-2.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
        return TexturedModelData.of(modelData,46,46);
        //Redo Model
    }



    @Override
    public void setAngles(AbstractGoblinEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.leftEar.yaw = 0.7854F;
        this.rightEar.yaw = -0.7854F;
        /*float rotateFactor;
        rotateFactor = (float) entity.getRotationVector().lengthSquared();
        rotateFactor = rotateFactor / 0.2F;
        rotateFactor = rotateFactor * rotateFactor * rotateFactor;
        if(rotateFactor < 1.0F)
        {
            rotateFactor = 1.0F;
        }
        this.rightArm.pivotX = (float) (Math.cos(limbAngle * 0.6662F + (float) Math.PI) * 2.0F * limbDistance * 0.5F / rotateFactor);
        this.leftArm.pivotX = (float) (Math.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F / rotateFactor);
        this.rightArm.pivotY = 0.0F;
        this.rightArm.pivotZ = 0.0F;
        this.leftArm.pivotY = 0.0F;
        this.leftArm.pivotZ = 0.0F;
        this.rightLeg.pivotX = (float) (Math.cos(limbAngle * 0.6662F) * 1.4F * limbDistance / rotateFactor);
        this.leftLeg.pivotX = (float) (Math.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance / rotateFactor);
        this.head.pivotY = headYaw * ((float) Math.PI / 180F);
        this.head.pivotX = headPitch * ((float) Math.PI / 180F);

        if(entity.isUsingItem())
        {
            double rotateX = Math.toRadians(-90F + 5F * Math.sin(animationProgress));
            this.rightArm.pivotX = (float) rotateX;
            this.leftArm.pivotX = (float) rotateX;
            this.rightArm.pivotX = (float) Math.toRadians(-90F);
            this.rightLeg.pivotY = (float) Math.toRadians(25F);
            this.leftLeg.pivotX = (float) Math.toRadians(-90F);
            this.leftLeg.pivotY = (float) Math.toRadians(-25F);
        }
        else
        {
            this.rightLeg.pivotY = (float) Math.toRadians(0);
            this.leftLeg.pivotY = (float) Math.toRadians(0);
        }*/
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.head, this.body);
    }
}
