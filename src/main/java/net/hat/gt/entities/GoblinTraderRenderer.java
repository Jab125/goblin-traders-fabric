package net.hat.gt.entities;

/**
 * Gobin is better, it has no 'l'
 * @deprecated use {@link net.hat.gt.entities.GobinTraderRenderer}
 */
// if you want a goblin trader renderer (a bad one) come here :P
@SuppressWarnings("unused")
@com.jab125.util.Bad(since = "1/1/1970")
@Deprecated
public class GoblinTraderRenderer extends GobinTraderRenderer {
    public GoblinTraderRenderer(net.minecraft.client.render.entity.EntityRendererFactory.Context context) {
        super(context);
    }
}
