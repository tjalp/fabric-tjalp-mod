package net.tjalp.mod.tjalp;

import net.minecraft.server.MinecraftServer;

public class TjalpServer {

    public static TjalpServer INSTANCE;
    private final MinecraftServer server;

    public TjalpServer(MinecraftServer server) {
        TjalpServer.INSTANCE = this;
        this.server = server;
    }

    public void onServerLoadWorld() {

    }

    public void onServerLoadWorldComplete() {

    }

    public void onServerShutdown() {

    }

    public MinecraftServer server() {
        return this.server;
    }
}
