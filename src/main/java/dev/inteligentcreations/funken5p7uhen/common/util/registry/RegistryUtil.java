package dev.inteligentcreations.funken5p7uhen.common.util.registry;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraftforge.network.IContainerFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class RegistryUtil
{
    @Contract("_ -> new")
    public static <T extends ScreenHandler> @NotNull ScreenHandlerType<T> registerScreenHandler(IContainerFactory<T> containerFactory)
    {
            return new ScreenHandlerType<>(containerFactory);
    }
}
