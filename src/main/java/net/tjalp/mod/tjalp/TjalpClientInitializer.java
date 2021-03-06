package net.tjalp.mod.tjalp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityPose;

public class TjalpClientInitializer implements ClientModInitializer {

    public static EntityPose pose = null;

    @Override
    public void onInitializeClient() {

        // register the keybindings
        registerKeyBindings();
    }

    private void registerKeyBindings() {

        KeyBinding dieKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".die",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        KeyBinding flyKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".fly",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        KeyBinding jumpKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".jump",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        KeyBinding sleepKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".sleep",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        KeyBinding spinKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".spin",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        KeyBinding swimKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Tjalp.MOD_ID + ".swim",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category." + Tjalp.MOD_ID + ".tjalp"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (dieKeyBinding.wasPressed()) if (pose == EntityPose.DYING) pose = null; else pose = EntityPose.DYING;
            if (flyKeyBinding.wasPressed()) if (pose == EntityPose.FALL_FLYING) pose = null; else pose = EntityPose.FALL_FLYING;
            if (jumpKeyBinding.wasPressed()) if (pose == EntityPose.LONG_JUMPING) pose = null; else pose = EntityPose.LONG_JUMPING;
            if (sleepKeyBinding.wasPressed()) if (pose == EntityPose.SLEEPING) pose = null; else pose = EntityPose.SLEEPING;
            if (spinKeyBinding.wasPressed()) if (pose == EntityPose.SPIN_ATTACK) pose = null; else pose = EntityPose.SPIN_ATTACK;
            if (swimKeyBinding.wasPressed()) if (pose == EntityPose.SWIMMING) pose = null; else pose = EntityPose.SWIMMING;
        });
    }
}
