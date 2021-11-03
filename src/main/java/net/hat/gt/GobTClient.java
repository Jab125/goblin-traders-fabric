package net.hat.gt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hat.gt.entities.GobinTraderRenderer;
import net.hat.gt.entities.GoblinTraderModel;
import net.hat.gt.entities.VeinGobinTraderRenderer;
import net.hat.gt.init.ModEntities;
import net.hat.gt.util.CapesLoader;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GobTClient implements ClientModInitializer {
    public static final EntityModelLayer GOBLIN_MODEL_LAYER = new EntityModelLayer(new Identifier("goblintraders", "goblin"), "goblin_render_layer");
    public static final EntityModelLayer VEIN_GOBLIN_MODEL_LAYER = new EntityModelLayer(new Identifier("goblintraders", "vgoblin"), "vein_goblin_render_layer");
    public static final EntityModelLayer TEST_GOBLIN_MODEL_LAYER = new EntityModelLayer(new Identifier("goblintraders", "tgoblin"), "test_goblin_render_layer");
    @Override
    public void onInitializeClient() {
        CapesLoader.load();
        //EntityRenderer
        EntityRendererRegistry.register(ModEntities.GOBLIN_TRADER, GobinTraderRenderer::new);
        EntityRendererRegistry.register(ModEntities.VEIN_GOBLIN_TRADER, VeinGobinTraderRenderer::new);
        EntityRendererRegistry.register(ModEntities.TEST_GOBLIN_TRADER, GobinTraderRenderer::new);
        //EntityLayer
        EntityModelLayerRegistry.registerModelLayer(GOBLIN_MODEL_LAYER, GoblinTraderModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(VEIN_GOBLIN_MODEL_LAYER, GoblinTraderModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(TEST_GOBLIN_MODEL_LAYER, GoblinTraderModel::getTexturedModelData);

    }
}