package dev.inteligentcreations.funken5p7uhen.common.util.registry;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraftforge.network.IContainerFactory;

public class RegistryUtil
{
    public static <T extends ScreenHandler> ScreenHandlerType<T> registerScreenHandler(IContainerFactory<T> containerFactory)
    {
            return new ScreenHandlerType<>(containerFactory);
    }
}
