package net.tjalp.mod.tjalp.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import net.tjalp.mod.tjalp.Tjalp;
import net.tjalp.mod.tjalp.TjalpServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class TjalpConfig {
    private final Path path;
    private JsonObject data;

    public TjalpConfig(Path path) {
        this.path = path;
        data(defaultConfig());
    }

    public boolean read(boolean defaultConfig) {
        if (defaultConfig) return readDefault();

        try {
            MinecraftServer server = TjalpServer.instance().server();
            File file = new File(server.getSavePath(WorldSavePath.ROOT) + File.separator + Tjalp.MOD_ID + File.separator + "config.json");
            if (!file.exists()) {
                readDefault();
                write(false);
            }
            FileReader reader = new FileReader(file);
            data(new JsonParser().parse(reader).getAsJsonObject());
            reader.close();
            return true;
        } catch(Exception e) {
            Tjalp.logger().error("Something went wrong whilst reading the world configuration: " + e.getMessage());
            if (data() == null) data(defaultConfig());
        }
        return false;
    }

    private boolean readDefault() {
        try {
            File file = this.path.toFile();
            if (!file.exists()) {
                data(defaultConfig());
                writeDefault();
            }
            FileReader reader = new FileReader(file);
            data(new JsonParser().parse(reader).getAsJsonObject());
            reader.close();
            return true;
        } catch (Exception e) {
            Tjalp.logger().error("Something went wrong whilst reading the default configuration: " + e.getMessage());
            if (data() == null) data(defaultConfig());
        }
        return false;
    }

    public boolean write(boolean defaultConfig) {
        if (defaultConfig) return writeDefault();

        try {
            String prettyData = Tjalp.prettyGson().toJson(data());
            MinecraftServer server = TjalpServer.instance().server();
            File file = new File(server.getSavePath(WorldSavePath.ROOT) + File.separator + Tjalp.MOD_ID + File.separator + "config.json");
            file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(prettyData);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            Tjalp.logger().error("Something went wrong whilst writing the world configuration: " + e.getMessage());
        }
        return false;
    }

    private boolean writeDefault() {
        try {
            String prettyData = Tjalp.prettyGson().toJson(data());
            File file = this.path.toFile();
            file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(prettyData);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            Tjalp.logger().error("Something went wrong whilst writing the default configuration: " + e.getMessage());
        }
        return false;
    }

    public JsonObject data() {
        return this.data;
    }

    public void data(JsonObject data) {
        this.data = data;
    }

    private JsonObject defaultConfig() {
        InputStream in = Tjalp.instance().getClass().getClassLoader().getResourceAsStream("config.json");
        if (in == null) return new JsonObject();
        return new JsonParser().parse(new InputStreamReader(in)).getAsJsonObject();
    }
}
