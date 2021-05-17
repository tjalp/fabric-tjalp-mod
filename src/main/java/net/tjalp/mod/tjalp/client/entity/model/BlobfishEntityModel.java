package net.tjalp.mod.tjalp.client.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.tjalp.mod.tjalp.entity.BlobfishEntity;

public class BlobfishEntityModel extends EntityModel<BlobfishEntity> {

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart nose;
    private final ModelPart tail;
    private final ModelPart tail_fin;
    private final ModelPart fin_left;
    private final ModelPart fin_right;

    public BlobfishEntityModel() {
        this.textureHeight = 32;
        this.textureWidth = 32;

        root = new ModelPart(this);
        root.setPivot(0, 24, 0);

        body = new ModelPart(this);
        body.setPivot(0.0F, -2.5F, 1.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addCuboid(-3.0F, -2.5F, -8.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);

        nose = new ModelPart(this);
        nose.setPivot(0.0F, -0.5F, -8.0F);
        body.addChild(nose);
        nose.setTextureOffset(0, 19).addCuboid(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, 0.25F, 0.0F);
        body.addChild(tail);
        tail.setTextureOffset(11, 14).addCuboid(-2.0F, -2.25F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        tail_fin = new ModelPart(this);
        tail_fin.setPivot(0.0F, -1.45F, 0.0F);
        tail.addChild(tail_fin);
        tail_fin.setTextureOffset(0, 14).addCuboid(0.0F, -2.0F, -1.0F, 0.0F, 6.0F, 10.0F, 0.0F, false);

        fin_left = new ModelPart(this);
        fin_left.setPivot(3.0F, 1.0F, -4.0F);
        body.addChild(fin_left);
        pivot(fin_left, 0.0F, -0.6109F, 0.0F);
        fin_left.setTextureOffset(0, 0).addCuboid(0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);

        fin_right = new ModelPart(this);
        fin_right.setPivot(-3.0F, 1.0F, -4.0F);
        body.addChild(fin_right);
        pivot(fin_right, 0.0F, 0.6109F, 0.0F);
        fin_right.setTextureOffset(0, 0).addCuboid(-3.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, true);
    }

    private void pivot(ModelPart modelPart, float pitch, float yaw, float roll) {
        modelPart.pitch = pitch;
        modelPart.yaw = yaw;
        modelPart.roll = roll;
    }

    @Override
    public void setAngles(BlobfishEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.body.pitch = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        // render down
        matrices.translate(0, 0, 0);

        // render
        root.render(matrices, vertices, light, overlay);
    }
}
