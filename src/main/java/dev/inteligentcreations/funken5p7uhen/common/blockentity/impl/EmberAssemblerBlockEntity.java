package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import com.google.common.collect.ImmutableMap;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstance;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstanceProvider;
import dev.inteligentcreations.funken5p7uhen.common.util.inventory.ImplementedInventory;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class EmberAssemblerBlockEntity extends BlockEntity
    implements ImplementedInventory, LavaContainerInstanceProvider, NamedScreenHandlerFactory
{
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    private final LavaContainerInstance INSTANCE;
    private final Map<Direction, LavaContainerInstance.Action> passiveIOMap = new HashMap<>();

    public EmberAssemblerBlockEntity(BlockEntityType<?> type,
                                     BlockPos pos,
                                     BlockState state)
    {
        super(type, pos, state);
        INSTANCE = LavaContainerInstance.createInstance(5,
                5,
                2500,
                passiveIOMap);
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public LavaContainerInstance getInstance()
    {
        return null;
    }

    public static void tick(World world,
                            BlockPos pos,
                            BlockState state,
                            EmberAssemblerBlockEntity be)
    {
        if (world.isClient()) return;
        Map<Map.Entry<BlockState, BlockPos>, Direction> surroundingStates = ImmutableMap.of(
                new AbstractMap.SimpleEntry<>(world.getBlockState(pos.up()), pos.up()), Direction.UP,
                new AbstractMap.SimpleEntry<>(world.getBlockState(pos.down()), pos.down()), Direction.DOWN,
                new AbstractMap.SimpleEntry<>(world.getBlockState(pos.north()), pos.north()), Direction.NORTH,
                new AbstractMap.SimpleEntry<>(world.getBlockState(pos.south()), pos.south()), Direction.SOUTH,
                new AbstractMap.SimpleEntry<>(world.getBlockState(pos.west()), pos.west()), Direction.WEST,
                new AbstractMap.SimpleEntry<>(world.getBlockState(pos.east()), pos.east()), Direction.EAST
        );
        for (Map.Entry<Map.Entry<BlockState, BlockPos>, Direction> entry : surroundingStates.entrySet())
        {
            BlockState state1 = entry.getKey().getKey();
            BlockPos pos1 = entry.getKey().getValue();
            Direction dir = entry.getValue();
            if (state1.getBlock() instanceof BlockEntityProvider)
            {
                BlockEntity blockEntity = world.getBlockEntity(pos1);
                if (blockEntity instanceof LavaContainerInstanceProvider provider)
                {
                    if (provider.getInstance().isActionValid(dir.getOpposite(), LavaContainerInstance.Action.LAVA_INPUT))
                    {
                        if (!existInIOMap(be, dir, LavaContainerInstance.Action.LAVA_OUTPUT))
                        {
                            be.passiveIOMap.put(dir, LavaContainerInstance.Action.LAVA_OUTPUT);
                        }
                        be.push(provider, be.getMaxExtract());
                    }
                    else
                    {
                        if (existInIOMap(be, dir, LavaContainerInstance.Action.LAVA_OUTPUT))
                        {
                            be.passiveIOMap.remove(dir, LavaContainerInstance.Action.LAVA_OUTPUT);
                        }
                    }
                    if (provider.getInstance().isActionValid(dir.getOpposite(), LavaContainerInstance.Action.LAVA_OUTPUT))
                    {
                        if (!existInIOMap(be, dir, LavaContainerInstance.Action.LAVA_INPUT))
                        {
                            be.passiveIOMap.put(dir, LavaContainerInstance.Action.LAVA_INPUT);
                        }
                        provider.push(be, provider.getMaxExtract());
                    }
                    else
                    {
                        if (existInIOMap(be, dir, LavaContainerInstance.Action.LAVA_INPUT))
                        {
                            be.passiveIOMap.remove(dir, LavaContainerInstance.Action.LAVA_INPUT);
                        }
                    }
                    markDirty(world, pos, state);
                }
            }
        }
    }

    protected static boolean existInIOMap(@NotNull EmberAssemblerBlockEntity be,
                                          Direction dir,
                                          LavaContainerInstance.Action action)
    {
        return be.passiveIOMap.containsKey(dir) && be.passiveIOMap.get(dir).equals(action);
    }

    @Override
    public Text getDisplayName()
    {
        return new TranslatableText("gui.funken5p7uhen.assembling");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId,
                                    PlayerInventory inv,
                                    PlayerEntity player)
    {
        return null;
    }
}
