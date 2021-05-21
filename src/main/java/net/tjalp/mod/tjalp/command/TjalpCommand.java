package net.tjalp.mod.tjalp.command;

import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.tjalp.mod.tjalp.Tjalp;
import net.tjalp.mod.tjalp.config.TjalpConfig;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class TjalpCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = literal("tjalp")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("config")
                    .then(literal("query")
                        .executes(context -> executeConfigQuery(context.getSource())))
                    .then(literal("reload")
                        .executes(context -> executeConfigReload(context.getSource(), false))
                        .then(argument("read default", bool())
                            .executes(context -> executeConfigReload(context.getSource(), true))))
                    .then(literal("remove")
                        .then(argument("key", greedyString())
                            .executes(context -> executeConfigRemove(context.getSource(), getString(context, "key")))))
                    .then(literal("save")
                        .executes(context -> executeConfigSave(context.getSource(), false))
                        .then(argument("save default", bool())
                            .executes(context -> executeConfigSave(context.getSource(), getBool(context, "save default")))))
                    .then(literal("set")
                        .then(argument("key", string())
                            .then(argument("value", greedyString())
                                .executes(context -> executeConfigSet(context.getSource(), getString(context, "key"), getString(context, "value")))))));

        dispatcher.register(literalArgumentBuilder);
    }

    private static int executeConfigQuery(ServerCommandSource source) {
        source.sendFeedback(new LiteralText(Tjalp.prettyGson().toJson(Tjalp.instance().config().data())), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int executeConfigReload(ServerCommandSource source, boolean readDefault) {
        boolean success = Tjalp.instance().config().read(readDefault);
        if (success) source.sendFeedback(new TranslatableText("commands.tjalp.tjalp.config.reload_success", readDefault), true);
        else source.sendError(new TranslatableText("commands.tjalp.tjalp.config.reload_failed"));
        return Command.SINGLE_SUCCESS;
    }

    private static int executeConfigRemove(ServerCommandSource source, String key) {
        TjalpConfig config = Tjalp.instance().config();
        JsonObject data = config.data();
        data.remove(key);
        config.data(data);
        source.sendFeedback(new TranslatableText("commands.tjalp.tjalp.config.remove", key), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int executeConfigSave(ServerCommandSource source, boolean saveDefault) {
        boolean success = Tjalp.instance().config().write(saveDefault);
        if (success) source.sendFeedback(new TranslatableText("commands.tjalp.tjalp.config.save_success", saveDefault), false);
        else source.sendError(new TranslatableText("commands.tjalp.tjalp.config.save_failed"));
        return Command.SINGLE_SUCCESS;
    }

    private static int executeConfigSet(ServerCommandSource source, String key, String value) {
        TjalpConfig config = Tjalp.instance().config();
        JsonObject data = config.data();
        data.addProperty(key, value);
        config.data(data);
        source.sendFeedback(new TranslatableText("commands.tjalp.tjalp.config.set", key, value), true);
        return Command.SINGLE_SUCCESS;
    }
}
