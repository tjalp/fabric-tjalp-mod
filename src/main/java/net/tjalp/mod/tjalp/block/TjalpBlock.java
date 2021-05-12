package net.tjalp.mod.tjalp.block;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.Tjalp;

public class TjalpBlock {



    private static Block register(String id, Block block) {
        return register(new Identifier(Tjalp.MOD_ID, id), block);
    }

    private static Block register(Identifier id, Block block) {
        return Registry.register(Registry.BLOCK, id, block);
    }

    public static void registerBlocks() {

    }
}
