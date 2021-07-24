package net.tjalp.mod.tjalp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.tjalp.mod.tjalp.TjalpClientInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Environment(EnvType.CLIENT)
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    private void onIsSwimming(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(TjalpClientInitializer.pose == EntityPose.SWIMMING);
    }
}
