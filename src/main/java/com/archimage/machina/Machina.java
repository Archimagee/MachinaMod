package com.archimage.machina;

import com.archimage.machina.block.MachinaBlocks;
import com.archimage.machina.block.entity.MachinaBlockEntities;
import com.archimage.machina.item.MachinaItems;
import com.archimage.machina.screen.MachinaMenuTypes;
import com.archimage.machina.screen.RockTumblerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Machina.MOD_ID)

public class Machina
{
    public static final String MOD_ID = "machina";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Machina() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MachinaItems.register(eventBus);
        MachinaBlocks.register(eventBus);
        MachinaBlockEntities.register(eventBus);
        MachinaMenuTypes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        MenuScreens.register(MachinaMenuTypes.ROCK_TUMBLER_MENU.get(), RockTumblerScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
