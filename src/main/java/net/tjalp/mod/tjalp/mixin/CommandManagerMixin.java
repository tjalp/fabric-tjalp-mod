package net.tjalp.mod.tjalp.mixin;

import net.minecraft.server.command.CommandManager;
import net.tjalp.mod.tjalp.Tjalp;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    
    @Inject(method = "<init>", at = @At("RETURN"))
    private void commandRegistration(CommandManager.RegistrationEnvironment environment, CallbackInfo ci) {
        Tjalp.instance().registerCommands(((CommandManager) (Object) this).getDispatcher());
    }
}
