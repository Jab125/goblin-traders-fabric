package net.hat.gt.entities;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class GoblinTraderModel extends EntityModel<AbstractGoblinEntity> {

    private final Model goblinModel;

    public GoblinTraderModel(ModelPart root) {
        this.goblinModel = new Model(root.getChild("goblin"));
    }

    public static TexturedModelData model() {
        var modelData = new ModelData();
        var root = modelData.getRoot();
        buildGoblinModel(root.addChild("goblin", new ModelPartBuilder(), ModelTransform.NONE));
        return TexturedModelData.of(modelData,46,46);
    }

    private static void buildGoblinModel(ModelPartData root) {
        var body = root.addChild("body", ModelPartBuilder.create()
                .uv(12,12)
                .cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                .uv(0,20)
                .cuboid(-2.5F, -9.5F, 2.0F, 5.0F, 7.0F, 3.0F),
        ModelTransform.pivot(0.0F,24.0F,0.0F));
        var head = body.addChild("head", ModelPartBuilder.create()
                .uv(0,0)
                .cuboid(-4.0F, -14.0F, -3.0F, 8.0F, 6.0F, 6.0F)
                .uv(0,32)
                .cuboid(-4.0F, -14.0F, -3.0F, 8.0F, 8.0F, 6.0F, new Dilation(0.5F))
                .uv(22,0)
                .cuboid(-1.0F, -11.0F, -5.0F, 2.0F, 4.0F, 2.0F),
        ModelTransform.pivot(0.0F,0.0F,0.0F));
        head.addChild("leftear_r1", ModelPartBuilder.create()
                .uv(0,8)
                .cuboid(2.0F, -13.0F, 3.0F, 0.0F, 4.0F, 4.0F),
         ModelTransform.pivot(0.0F,0.0F,0.0F));
        head.addChild("rightear_r1", ModelPartBuilder.create()
                .uv(8,8)
                .cuboid(-2.0F, -13.0F, 3.0F, 0.0F, 4.0F, 4.0F),
        ModelTransform.pivot(0.0F,0.0F,0.0F));
        body.addChild("leftarm", ModelPartBuilder.create().uv(38,0).cuboid(2.0F, -8.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
        body.addChild("rightleg", ModelPartBuilder.create().uv(26,9).cuboid(-2.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
        body.addChild("leftleg", ModelPartBuilder.create().uv(36,9).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 3.0F), ModelTransform.pivot(0.0F,-4.0F,0.0F));
        body.addChild("rightarm", ModelPartBuilder.create().uv(30,0).cuboid(-4.0F, -8.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
    }

    public Model getCurrentModel(){
        return this.goblinModel;
    }

    @Override
    public void setAngles(AbstractGoblinEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        this.getCurrentModel().render(matrices, vertices, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }

    public record Model(ModelPart root) {

        public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
            this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        }
    }
}
