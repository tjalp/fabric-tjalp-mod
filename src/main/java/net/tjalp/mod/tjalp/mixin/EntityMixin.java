package net.tjalp.mod.tjalp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.tjalp.mod.tjalp.Tjalp;
import net.tjalp.mod.tjalp.TjalpClientInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Environment(EnvType.CLIENT)
    @Inject(method = "setPose", at = @At("HEAD"), cancellable = true)
    private void onSetPose(EntityPose pose, CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;

        if (entity.getType() != EntityType.PLAYER) return;

        if (TjalpClientInitializer.pose != null) {
            if (pose != TjalpClientInitializer.pose) {
                ci.cancel();
                entity.setPose(TjalpClientInitializer.pose);
            }
        }
    }
}
