package dev.inteligentcreations.funken5p7uhen;

import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.item.init.ItemInit;
import dev.inteligentcreations.funken5p7uhen.common.util.nothingtoseeheremovealong.LoggerSplashKinda;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(funken5p7uhen.MOD_ID)
public class funken5p7uhen
{
    public static final String MOD_ID = "funken5p7uhen";
    public static final Logger LOGGER = LoggerFactory.getLogger("fμnken5p7ühen");

    public funken5p7uhen()
    {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_BUS.addListener(this::commonSetup);
        MOD_BUS.addListener(this::clientSetup);
        BlockInit.BLOCKS.register(MOD_BUS);
        ItemInit.ITEMS.register(MOD_BUS);
        BlockEntityInit.BLOCK_ENTITIES.register(MOD_BUS);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Initialized.");
        LoggerSplashKinda.generateRandomSplash();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        RenderLayers.setRenderLayer(BlockInit.EMBER_TUNNEL.get(), RenderLayer.getCutout());
    }
}
