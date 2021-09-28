package net.hat.gt.entities;

import net.hat.gt.GobT;
import net.hat.gt.GobTClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GobinTraderRenderer extends MobEntityRenderer<AbstractGoblinEntity, GoblinTraderModel<GoblinTraderEntity>> {
        public static final Identifier TEXTURE = GobT.id("textures/entity/goblintrader/goblin_trader.png");

    public GobinTraderRenderer(EntityRendererFactory.Context context) {
        super(context, new GoblinTraderModel<>(context.getPart(GobTClient.GOBLIN_MODEL_LAYER)), .5F);
    }

    @Override
    public Identifier getTexture(AbstractGoblinEntity entity) {
        return TEXTURE;
    }
}
