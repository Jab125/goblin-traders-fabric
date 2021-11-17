package net.hat.gt.entities;

import net.hat.gt.GobT;
import net.hat.gt.GobTClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;

// No. I refuse to rename this class. This is Goblins without the L. So basically a better version.
public class GobinTraderRenderer extends MobEntityRenderer<AbstractGoblinEntity, GoblinTraderModel<GoblinTraderEntity>> {
        public static final Identifier TEXTURE = GobT.id("textures/entity/goblintrader/goblin_trader.png");
        public static final Identifier TEXTUREVEIN = GobT.id("textures/entity/goblintrader/vein_goblin_trader.png");

    public GobinTraderRenderer(EntityRendererFactory.Context context) {
        super(context, new GoblinTraderModel<>(context.getPart(GobTClient.GOBLIN_MODEL_LAYER)), .5F);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    /** if someone wants to add a texture to the test goblin trader make a resource pack (and optionally sumbit a pull request)
     *
     * @param entity is the entity
     * @return the texture
     */
    @Override
    public Identifier getTexture(AbstractGoblinEntity entity) {
        return entity instanceof VeinGoblinTraderEntity ? TEXTUREVEIN : TEXTURE;
    }

    public void render(AbstractGoblinEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        if(mobEntity.isUsingItem())
        {
            matrixStack.translate(0, -0.15, 0);
        }
        if(mobEntity.isStunned() && mobEntity.isAlive() && GobT.config.ALL_GOBLIN_TRADERS_CONFIG.FALL)
        {
            float progress = Math.min(10F, mobEntity.getFallCounter() + g) / 10F;
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-mobEntity.getStunRotation()));
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90F * progress));
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(mobEntity.getStunRotation()));
        }
        super.render(mobEntity, 0F, g, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
    }
    protected int getBlockLight(AbstractGoblinEntity goblinEntity, BlockPos blockPos) {

        return goblinEntity instanceof VeinGoblinTraderEntity ? Math.max(super.getBlockLight(goblinEntity, blockPos), 10) : super.getBlockLight(goblinEntity, blockPos);
    }
}
