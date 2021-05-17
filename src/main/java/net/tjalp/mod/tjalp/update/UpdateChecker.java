package net.tjalp.mod.tjalp.update;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.tjalp.mod.tjalp.Tjalp;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UpdateChecker {

    public static Version latestVersion() throws Exception {
        URL url = new URL("https://api.github.com/repos/tjalp/fabric-tjalp-mod/releases/latest");

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(IOUtils.toString(url, StandardCharsets.UTF_8)).getAsJsonObject();
        String version = object.get("tag_name").getAsString();
        return new Version(version.replaceFirst("v", ""));
    }

    public static boolean newVersionAvailable() {
        Version currentVersion = new Version(Tjalp.version());
        Version latestVersion;
        try {
            latestVersion = latestVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return currentVersion.compareTo(latestVersion) < 0;
    }
}
