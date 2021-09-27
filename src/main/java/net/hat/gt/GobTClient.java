package net.hat.gt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.hat.gt.entities.GoblinEntityModel;
import net.hat.gt.entities.GoblinEntityRenderer;
import net.hat.gt.init.ModEntities;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class GobTClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("goblintraders", "cube"), "main");
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.INSTANCE.register(ModEntities.GOBLIN_TRADER, GoblinEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, GoblinEntityModel::getTexturedModelData);
    }
}