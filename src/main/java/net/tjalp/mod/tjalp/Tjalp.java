package net.tjalp.mod.tjalp;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;
import net.tjalp.mod.tjalp.block.TjalpBlock;
import net.tjalp.mod.tjalp.entity.BlobfishEntity;
import net.tjalp.mod.tjalp.entity.TjalpEntityType;
import net.tjalp.mod.tjalp.item.TjalpItem;
import net.tjalp.mod.tjalp.command.StructureCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tjalp implements ModInitializer {

    public static final String MOD_ID = "tjalp";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String VERSION = FabricLoader.getInstance().getModContainer("tjalp").isPresent() ? FabricLoader.getInstance().getModContainer("tjalp").get().getMetadata().getVersion().getFriendlyString() : "unknown";

    public static Logger logger() {
        return Tjalp.LOGGER;
    }

    public static String version() {
        return Tjalp.VERSION;
    }

    @Override
    public void onInitialize() {
        TjalpBlock.registerBlocks();
        TjalpItem.registerItems();
        TjalpEntityType.registerEntityTypes();
        FabricDefaultAttributeRegistry.register(TjalpEntityType.BLOBFISH, BlobfishEntity.createMobAttributes());
    }

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        StructureCommand.register(dispatcher);
    }
}
