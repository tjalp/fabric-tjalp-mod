package net.tjalp.mod.tjalp.mixin;

import net.minecraft.server.MinecraftServer;
import net.tjalp.mod.tjalp.TjalpServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "loadWorld", at = @At("HEAD"))
    private void serverStart(CallbackInfo ci) {
        TjalpServer server = new TjalpServer((MinecraftServer) (Object) this);
        server.onServerLoadWorld();
    }

    @Inject(method = "loadWorld", at = @At("RETURN"))
    private void loadedWorld(CallbackInfo ci) {
        TjalpServer.INSTANCE.onServerLoadWorldComplete();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo ci) {
        TjalpServer.INSTANCE.onServerShutdown();
    }
}
