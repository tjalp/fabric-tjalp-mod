package net.tjalp.mod.tjalp;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.item.CustomAxeItem;
import net.tjalp.mod.tjalp.item.CustomHoeItem;
import net.tjalp.mod.tjalp.item.CustomPickaxeItem;
import net.tjalp.mod.tjalp.item.ruby.RubyToolMaterial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tjalp implements ModInitializer {

    public static final String MOD_ID = "tjalp";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final Item RUBY = new Item(new Item.Settings().group(ItemGroup.MATERIALS).fireproof());
    public static final ToolItem RUBY_SWORD = new SwordItem(RubyToolMaterial.INSTANCE, 4, -2.4F, new Item.Settings().group(ItemGroup.COMBAT).fireproof());
    public static final ToolItem RUBY_SHOVEL = new ShovelItem(RubyToolMaterial.INSTANCE, 2.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
    public static final ToolItem RUBY_PICKAXE = new CustomPickaxeItem(RubyToolMaterial.INSTANCE, 2, -2.8F, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
    public static final ToolItem RUBY_AXE = new CustomAxeItem(RubyToolMaterial.INSTANCE, 6.0F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
    public static final ToolItem RUBY_HOE = new CustomHoeItem(RubyToolMaterial.INSTANCE, -4, 0.0F, new Item.Settings().group(ItemGroup.TOOLS).fireproof());

    @Override
    public void onInitialize() {
        LOGGER.info("Registering items");
        registerItems();
    }

    private void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Tjalp.MOD_ID, "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier(Tjalp.MOD_ID, "ruby_sword"), RUBY_SWORD);
        Registry.register(Registry.ITEM, new Identifier(Tjalp.MOD_ID, "ruby_shovel"), RUBY_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(Tjalp.MOD_ID, "ruby_pickaxe"), RUBY_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(Tjalp.MOD_ID, "ruby_axe"), RUBY_AXE);
        Registry.register(Registry.ITEM, new Identifier(Tjalp.MOD_ID, "ruby_hoe"), RUBY_HOE);
    }
}
