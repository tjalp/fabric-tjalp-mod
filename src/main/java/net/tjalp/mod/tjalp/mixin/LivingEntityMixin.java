package net.tjalp.mod.tjalp.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.tjalp.mod.tjalp.TjalpClientInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "isFallFlying", at = @At("HEAD"), cancellable = true)
    private void onIsFallFlying(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(TjalpClientInitializer.pose == EntityPose.FALL_FLYING);
    }

    @Inject(method = "isUsingRiptide", at = @At("HEAD"), cancellable = true)
    private void onIsUsingRiptide(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(TjalpClientInitializer.pose == EntityPose.SPIN_ATTACK);
    }
}
