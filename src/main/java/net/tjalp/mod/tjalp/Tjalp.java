package net.tjalp.mod.tjalp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;
import net.tjalp.mod.tjalp.block.TjalpBlock;
import net.tjalp.mod.tjalp.command.StructureCommand;
import net.tjalp.mod.tjalp.command.TjalpCommand;
import net.tjalp.mod.tjalp.config.TjalpConfig;
import net.tjalp.mod.tjalp.entity.BlobfishEntity;
import net.tjalp.mod.tjalp.entity.TjalpEntityType;
import net.tjalp.mod.tjalp.item.TjalpItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tjalp implements ModInitializer {

    private static Tjalp INSTANCE;

    public static final String MOD_ID = "tjalp";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String VERSION = FabricLoader.getInstance().getModContainer("tjalp").isPresent() ? FabricLoader.getInstance().getModContainer("tjalp").get().getMetadata().getVersion().getFriendlyString() : "unknown";
    private static final Path CONFIG_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toString() + File.separator + Tjalp.MOD_ID, "config.json");
    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    private TjalpConfig config;

    public static Tjalp instance() {
        return Tjalp.INSTANCE;
    }

    public static Logger logger() {
        return Tjalp.LOGGER;
    }

    public static String version() {
        return Tjalp.VERSION;
    }

    public static Path configPath() {
        return Tjalp.CONFIG_PATH;
    }

    public TjalpConfig config() {
        return this.config;
    }

    public static Gson prettyGson() {
        return Tjalp.PRETTY_GSON;
    }

    @Override
    public void onInitialize() {
        Tjalp.INSTANCE = this;

        this.config = new TjalpConfig(configPath());
        config.read(true);

        TjalpBlock.registerBlocks();
        TjalpItem.registerItems();
        TjalpEntityType.registerEntityTypes();
        FabricDefaultAttributeRegistry.register(TjalpEntityType.BLOBFISH, BlobfishEntity.createMobAttributes());
    }

    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        StructureCommand.register(dispatcher);
        TjalpCommand.register(dispatcher);
    }
}
