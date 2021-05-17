package net.tjalp.mod.tjalp.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.tjalp.mod.tjalp.update.UpdateChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "joinWorld", at = @At("RETURN"))
    public void onWorldJoin(ClientWorld world, CallbackInfo ci) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MinecraftClient client = (MinecraftClient) (Object) this;
            ClientPlayerEntity player = client.player;
            if (UpdateChecker.newVersionAvailable()) if (player != null) player.sendMessage(new TranslatableText("chat.tjalp.update_available").formatted(Formatting.YELLOW), false);
        });
        thread.setName("tjalp mod updater");
        thread.start();
    }
}
