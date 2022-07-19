package dev.inteligentcreations.funken5p7uhen.common.util.instance.lava;

import dev.inteligentcreations.funken5p7uhen.common.util.instance.InstanceProvider;
import org.jetbrains.annotations.NotNull;

public interface LavaContainerInstanceProvider
        extends InstanceProvider<LavaContainerInstance>
{
    default long getStoredLava()
    {
        return getInstance().storedLava;
    }

    default long getMaxInsert()
    {
        return getInstance().getMaxInsert();
    }

    default long getMaxExtract()
    {
        return getInstance().getMaxExtract();
    }

    default long getMaxStore()
    {
        return getInstance().getMaxStore();
    }

    default long add(long amount)
    {
        return getInstance().add(amount);
    }

    default long remove(long amount)
    {
        return getInstance().take(amount);
    }

    /**
     * Push a specific amount of lava to a nonnull target.
     *
     * @param target The target the specific amount of lava is going to be pushed to
     * @param amount The amount of lava to be pushed to the target
     */
    default void push(@NotNull LavaContainerInstanceProvider target, long amount)
    {
        getInstance().extractTo(target.getInstance(), amount);
    }
}
