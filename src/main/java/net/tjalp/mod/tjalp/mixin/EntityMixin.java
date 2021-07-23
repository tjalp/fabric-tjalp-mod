package net.tjalp.mod.tjalp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.tjalp.mod.tjalp.Tjalp;
import net.tjalp.mod.tjalp.TjalpClientInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    /*@Environment(EnvType.CLIENT)
    @Inject(method = "shouldLeaveSwimmingPose", at = @At("HEAD"), cancellable = true)
    private void onShouldLeaveSwimmingPose(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;

        if (entity.getType() != EntityType.PLAYER) return;

        if (TjalpClientInitializer.keepInSwimmingPose) {
            cir.setReturnValue(false);
        }
    }*/

    @Environment(EnvType.CLIENT)
    @Inject(method = "setPose", at = @At("HEAD"), cancellable = true)
    private void onSetPose(EntityPose pose, CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;

        if (entity.getType() != EntityType.PLAYER) return;

        if (TjalpClientInitializer.keepInSwimmingPose) {
            if (pose != EntityPose.SWIMMING) {
                ci.cancel();
                entity.setPose(EntityPose.SWIMMING);
            }
        }
    }

    /*@Environment(EnvType.CLIENT)
    @Inject(method = "updateSwimming", at = @At("HEAD"), cancellable = true)
    private void onUpdateSwimming(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;

        if (entity.getType() != EntityType.PLAYER) return;

        if (TjalpClientInitializer.keepInSwimmingPose) {
            Tjalp.logger().warn("Setting swimming to true");
            entity.setSwimming(true);
        }
    }*/
}
