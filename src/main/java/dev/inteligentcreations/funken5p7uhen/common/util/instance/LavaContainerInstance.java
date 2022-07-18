package dev.inteligentcreations.funken5p7uhen.common.util.instance;

import net.minecraft.util.math.Direction;

import java.util.Map;

/**
 * We don't want our devices to interact with other mods' fluid storage blocks, so we use a standalone lava container instance.<p>
 *
 * It should also be easier to port the mod to Fabric.
 */
public class LavaContainerInstance
{
    public long storedLava;
    private final long maxInsert;
    private final long maxExtract;
    private final long maxStore;
    private final Map<Direction, Action> availableDirections;

    protected LavaContainerInstance(long maxInsert,
                                    long maxExtract,
                                    long maxStore,
                                    Map<Direction, Action> availableDirections)
    {
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
        this.maxStore = maxStore;
        this.storedLava = 0;
        this.availableDirections = availableDirections;
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
                                                long maxStore,
                                                Map<Direction, Action> availableDirections)
    {
        return new LavaContainerInstance(maxInsert, maxExtract, maxStore, availableDirections);
    }

    public void extractTo(LavaContainerInstance target,
                          long amount)
    {
        if (this == target)
        {
            throw new IllegalArgumentException("Attempt to extract a specific amount from and to a same LavaContainerInstance. Use add(long amount) instead.");
        }
        long actualExtract = Math.min(Math.min(amount, Math.min(this.getMaxExtract(), target.getMaxInsert())), target.getMaxStore() - target.storedLava);
        actualExtract = this.take(actualExtract);
        target.add(actualExtract);
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

    public boolean isActionValid(Direction direction,
                                 Action action)
    {
        return availableDirections.containsKey(direction) && availableDirections.get(direction).equals(action);
    }

    public void addAction(Direction direction,
                          Action action)
    {
        availableDirections.put(direction, action);
    }

    public enum Action
    {
        NO_ACTION,
        LAVA_INPUT,
        LAVA_OUTPUT,
        ITEM_INPUT,
        ITEM_OUTPUT
    }
}
