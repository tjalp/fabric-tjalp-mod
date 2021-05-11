package net.tjalp.mod.tjalp;

import net.fabricmc.api.ModInitializer;
import net.tjalp.mod.tjalp.block.TjalpBlock;
import net.tjalp.mod.tjalp.item.TjalpItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tjalp implements ModInitializer {

    public static final String MOD_ID = "tjalp";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        TjalpBlock.registerBlocks();
        TjalpItem.registerItems();
    }
}
