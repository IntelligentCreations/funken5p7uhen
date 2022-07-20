package dev.inteligentcreations.funken5p7uhen.common.screen.handler.init;

import dev.inteligentcreations.funken5p7uhen.common.screen.handler.impl.DevelopingStationScreenHandler;
import dev.inteligentcreations.funken5p7uhen.common.util.registry.RegistryUtil;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ScreenHandlerInit
{
    public static final DeferredRegister<ScreenHandlerType<?>> SCREEN_HANDLERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, funken5p7uhen.MOD_ID);

    public static final RegistryObject<ScreenHandlerType<DevelopingStationScreenHandler>> DEVELOPING_STATION = SCREEN_HANDLERS.register("developing_station", () ->
            RegistryUtil.registerScreenHandler(DevelopingStationScreenHandler::create));
}
