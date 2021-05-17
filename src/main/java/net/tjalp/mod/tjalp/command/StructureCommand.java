package net.tjalp.mod.tjalp.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.world.gen.feature.StructureFeature;
import net.tjalp.mod.tjalp.Tjalp;

import java.util.Map;

import static net.minecraft.server.command.CommandManager.literal;

public class StructureCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = literal("structure")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("generate"));

        for (Map.Entry<String, StructureFeature<?>> structure : StructureFeature.STRUCTURES.entrySet()) {
            literalArgumentBuilder
                    .then(literal("generate")
                        .then(literal(structure.getKey())
                            .executes(context -> executeSpawn(context.getSource(), structure.getValue()))));
        }

        dispatcher.register(literalArgumentBuilder);
    }

    private static int executeSpawn(ServerCommandSource source, StructureFeature<?> structureFeature) {
        if (source.getEntity() == null) return Command.SINGLE_SUCCESS;

        source.sendFeedback(new LiteralText("Structure is " + structureFeature.getName()), false);
        return Command.SINGLE_SUCCESS;
    }
}
