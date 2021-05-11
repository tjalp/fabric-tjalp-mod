package net.tjalp.mod.tjalp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.Tjalp;

public class TjalpBlock {

    public static Block RUBY_BLOCK;

    private static Block register(String id, Block block) {
        return register(new Identifier(Tjalp.MOD_ID, id), block);
    }

    private static Block register(Identifier id, Block block) {
        return Registry.register(Registry.BLOCK, id, block);
    }

    public static void registerBlocks() {
        RUBY_BLOCK = register("ruby_block", new Block(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL)));
    }
}
