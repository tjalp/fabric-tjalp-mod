package net.tjalp.mod.tjalp;

import net.minecraft.server.MinecraftServer;

public class TjalpServer {

    // static
    private static TjalpServer INSTANCE;

    public static TjalpServer instance() {
        return TjalpServer.INSTANCE;
    }



    // object
    private MinecraftServer server;

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

    public void server(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer server() {
        return this.server;
    }
}
