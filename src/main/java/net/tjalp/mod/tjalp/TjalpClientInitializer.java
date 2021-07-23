package net.tjalp.mod.tjalp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityPose;
import org.lwjgl.glfw.GLFW;

public class TjalpClientInitializer implements ClientModInitializer {

    public static boolean keepInSwimmingPose = false;

    @Override
    public void onInitializeClient() {

        // register the keybindings
        registerKeyBindings();
    }

    private void registerKeyBindings() {

        KeyBinding swimKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".swim",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (swimKeyBinding.wasPressed()) {
                keepInSwimmingPose = !keepInSwimmingPose;
                Tjalp.logger().warn("Pressed keybind: " + keepInSwimmingPose);
            }

            if (keepInSwimmingPose) {
                client.player.setPose(EntityPose.SWIMMING);
            }
        });
    }
}
