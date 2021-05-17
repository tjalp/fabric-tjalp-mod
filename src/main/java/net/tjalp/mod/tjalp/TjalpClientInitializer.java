package net.tjalp.mod.tjalp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.tjalp.mod.tjalp.client.entity.model.BlobfishEntityModel;
import net.tjalp.mod.tjalp.client.entity.render.BlobfishEntityRenderer;
import net.tjalp.mod.tjalp.entity.TjalpEntityType;

public class TjalpClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(TjalpEntityType.BLOBFISH, (dispatcher, context) -> new BlobfishEntityRenderer(dispatcher, new BlobfishEntityModel(), 0F));
    }
}
