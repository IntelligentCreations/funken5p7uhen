package dev.inteligentcreations.funken5p7uhen.common.util.instance;

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
}
