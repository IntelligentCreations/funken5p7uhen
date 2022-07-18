package dev.inteligentcreations.funken5p7uhen.common.util.instance;

import org.jetbrains.annotations.NotNull;

public interface LavaContainerInstanceProvider
{
    LavaContainerInstance getLavaContainerInstance();

    default long getStoredLava()
    {
        return getLavaContainerInstance().storedLava;
    }

    default long getMaxInsert()
    {
        return getLavaContainerInstance().getMaxInsert();
    }

    default long getMaxExtract()
    {
        return getLavaContainerInstance().getMaxExtract();
    }

    default long getMaxStore()
    {
        return getLavaContainerInstance().getMaxStore();
    }

    default long add(long amount)
    {
        return getLavaContainerInstance().add(amount);
    }

    default long remove(long amount)
    {
        return getLavaContainerInstance().take(amount);
    }

    /**
     * Push a specific amount of lava to a nonnull target.
     *
     * @param target The target the specific amount of lava is going to be pushed to
     * @param amount The amount of lava to be pushed to the target
     * @return The amount of lava pushed successfully
     */
    default long push(@NotNull LavaContainerInstanceProvider target, long amount)
    {
        return getLavaContainerInstance().extractTo(target.getLavaContainerInstance(), amount);
    }
}
