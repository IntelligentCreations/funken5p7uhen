package dev.inteligentcreations.funken5p7uhen;

import dev.inteligentcreations.funken5p7uhen.client.screen.init.ScreenInit;
import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.block.init.DispenserBehaviorInit;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.category.init.RecipeCategoryInit;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.serializer.init.RecipeSerializerInit;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.init.RecipeTypeInit;
import dev.inteligentcreations.funken5p7uhen.common.item.init.ItemInit;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.init.ScreenHandlerInit;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public funken5p7uhen()
    {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_BUS.addListener(this::commonSetup);
        MOD_BUS.addListener(this::clientSetup);
        BlockInit.BLOCKS.register(MOD_BUS);
        ItemInit.ITEMS.register(MOD_BUS);
        BlockEntityInit.BLOCK_ENTITIES.register(MOD_BUS);
        ScreenHandlerInit.SCREEN_HANDLERS.register(MOD_BUS);
        RecipeTypeInit.TYPES.register(MOD_BUS);
        RecipeSerializerInit.SERIALIZERS.register(MOD_BUS);
        DispenserBehaviorInit.registerBehaviors();
        RecipeCategoryInit.registerCategories();
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Initialized.");
        LoggerSplashKinda.generateRandomSplash();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // i probably dont know what to place here, but i keep it in case i need to do something in the common setup
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ScreenInit.registerScreens();
        RenderLayers.setRenderLayer(BlockInit.EMBER_TUNNEL.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlockInit.EMBER_CONTAINER.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlockInit.EMBER_PUMP.get(), RenderLayer.getCutout());
        // RenderLayers.setRenderLayer(BlockInit.DEVELOPING_STATION.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlockInit.BASALT_PIGLIN_SCULPTURE.get(), RenderLayer.getCutout());
    }
}
