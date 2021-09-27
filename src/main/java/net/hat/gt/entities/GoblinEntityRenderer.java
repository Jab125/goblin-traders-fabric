package net.hat.gt.entities;

import net.hat.gt.GobTClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GoblinEntityRenderer extends MobEntityRenderer<GoblinEntity, GoblinEntityModel> {

    public GoblinEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GoblinEntityModel(context.getPart(GobTClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(GoblinEntity entity) {
        return new Identifier("goblintraders", "textures/entity/goblintrader/goblin_trader.png");
    }
}
