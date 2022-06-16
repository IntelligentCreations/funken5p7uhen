package dev.inteligentcreations.funken5p7uhen.common.util.instance;

/**
 * We don't want our devices to interact with other mods' fluid transfer/storage blocks, so we use a standalone lava container instance.
 * It should also be easier to port the mod to Fabric.
 */
public class LavaContainerInstance
{
    public long storedLava;
    private final long maxInsert;
    private final long maxExtract;
    private final long maxStore;

    protected LavaContainerInstance(long maxInsert,
                                    long maxExtract,
                                    long maxStore)
    {
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
        this.maxStore = maxStore;
        this.storedLava = 0;
    }

    public long getMaxInsert()
    {
        return maxInsert;
    }

    public long getMaxExtract()
    {
        return maxExtract;
    }

    public long getMaxStore()
    {
        return maxStore;
    }

    public static LavaContainerInstance createInstance(long maxInsert,
                                                long maxExtract,
                                                long maxStore)
    {
        return new LavaContainerInstance(maxInsert, maxExtract, maxStore);
    }

    public long extractTo(LavaContainerInstance target,
                          long amount)
    {
        if (this == target)
        {
            throw new IllegalArgumentException("Attempt to extract a specific amount from and to a same LavaContainerInstance. Use add(long amount) instead.");
        }
        long actualExtract = Math.min(Math.min(amount, this.getMaxExtract()), target.getMaxStore() - target.storedLava);
        this.take(actualExtract);
        target.add(actualExtract);
        return actualExtract;
    }

    public long add(long amount)
    {
        if (amount < 0)
        {
            throw new IllegalArgumentException("Attempt to add a negative amount.");
        }
        long actualAdd = Math.min(amount, this.getMaxStore() - storedLava);
        storedLava += actualAdd;
        return actualAdd;
    }

    public long take(long amount)
    {
        if (amount < 0)
        {
            throw new IllegalArgumentException("Attempt to take a negative amount.");
        }
        long actualTake = Math.min(amount, storedLava);
        storedLava -= actualTake;
        return actualTake;
    }
}
