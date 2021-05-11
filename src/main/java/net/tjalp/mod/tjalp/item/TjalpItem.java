package net.tjalp.mod.tjalp.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.Tjalp;
import net.tjalp.mod.tjalp.item.ruby.RubyToolMaterial;

public class TjalpItem {

    public static Item RUBY;
    public static Item RUBY_SWORD;
    public static Item RUBY_SHOVEL;
    public static Item RUBY_PICKAXE;
    public static Item RUBY_AXE;
    public static Item RUBY_HOE;

    private static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(Block block, ItemGroup group) {
        return register(new BlockItem(block, (new Item.Settings()).group(group)));
    }

    private static Item register(BlockItem item) {
        return register(item.getBlock(), item);
    }

    protected static Item register(Block block, Item item) {
        return register(Registry.BLOCK.getId(block), item);
    }

    private static Item register(String id, Item item) {
        return register(new Identifier(Tjalp.MOD_ID, id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registry.ITEM, id, item);
    }

    public static void registerItems() {
        RUBY = register("ruby", new Item(new Item.Settings().group(ItemGroup.MATERIALS).fireproof()));
        RUBY_SWORD = register("ruby_sword", new SwordItem(RubyToolMaterial.INSTANCE, 4, -2.4F, new Item.Settings().group(ItemGroup.COMBAT).fireproof()));
        RUBY_SHOVEL = register("ruby_shovel", new ShovelItem(RubyToolMaterial.INSTANCE, 2.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).fireproof()));
        RUBY_PICKAXE = register("ruby_pickaxe", new CustomPickaxeItem(RubyToolMaterial.INSTANCE, 2, -2.8F, new Item.Settings().group(ItemGroup.TOOLS).fireproof()));
        RUBY_AXE = register("ruby_axe", new CustomAxeItem(RubyToolMaterial.INSTANCE, 6.0F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).fireproof()));
        RUBY_HOE = register("ruby_hoe", new CustomHoeItem(RubyToolMaterial.INSTANCE, -4, 0.0F, new Item.Settings().group(ItemGroup.TOOLS).fireproof()));
    }
}
