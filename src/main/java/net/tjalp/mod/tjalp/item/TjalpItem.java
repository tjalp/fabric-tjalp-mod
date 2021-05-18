package net.tjalp.mod.tjalp.item;

import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.Tjalp;

public class TjalpItem {

    public static Item BAKED_CARROT;
    public static Item BAKED_EGG;

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
        BAKED_CARROT = register("baked_carrot", new Item(new Item.Settings().food(new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), 1F).build())));
        BAKED_EGG = register("baked_egg", new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3F).meat().build())));
    }
}
