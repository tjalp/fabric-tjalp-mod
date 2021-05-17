package net.tjalp.mod.tjalp.client.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.tjalp.mod.tjalp.Tjalp;
import net.tjalp.mod.tjalp.client.entity.model.BlobfishEntityModel;
import net.tjalp.mod.tjalp.entity.BlobfishEntity;

public class BlobfishEntityRenderer extends MobEntityRenderer<BlobfishEntity, BlobfishEntityModel> {
    private static final Identifier TEXTURE = new Identifier(Tjalp.MOD_ID, "textures/entity/blobfish/blobfish.png");

    public BlobfishEntityRenderer(EntityRenderDispatcher entityRenderDispatcher, BlobfishEntityModel entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

    @Override
    public Identifier getTexture(BlobfishEntity entity) {
        return BlobfishEntityRenderer.TEXTURE;
    }
}
