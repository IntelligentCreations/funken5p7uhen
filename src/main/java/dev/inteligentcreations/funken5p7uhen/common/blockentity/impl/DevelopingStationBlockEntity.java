package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
// import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.impl.DevelopingStationScreenHandler;
import dev.inteligentcreations.funken5p7uhen.common.util.inventory.DevelopingStationInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
// import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
// import net.minecraft.world.World;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// import java.util.Optional;

public class DevelopingStationBlockEntity {} /* extends BlockEntity
        implements DevelopingStationInventory, NamedScreenHandlerFactory
{
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public DevelopingStationBlockEntity(BlockPos pos,
                                        BlockState state)
    {
        super(BlockEntityInit.DEVELOPING_STATION.get(), pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public Text getDisplayName()
    {
        return new TranslatableText("gui.funken5p7uhen.developing");
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        super.writeNbt(nbt);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId,
                                    PlayerInventory inv,
                                    PlayerEntity player)
    {
        return new DevelopingStationScreenHandler(syncId,
                inv,
                this,
                ScreenHandlerContext.create(world, pos));
    }

    public static void tick(World world,
                            BlockPos pos,
                            BlockState state,
                            DevelopingStationBlockEntity be)
    {
        if (world.isClient()) return;
        Optional<DevelopingStationRecipe> match = world.getServer().getRecipeManager().getFirstMatch(
                DevelopingStationRecipe.Type.INSTANCE,
                be,
                world
        );
        if (match.isPresent())
        {
            ItemStack stack = be.getStack(1);
            if (stack.getItem() == Items.PAPER)
            {
                be.setStack(2, match.get().getOutput());
                markDirty(world, pos, state);
            }
        }
        else
        {
            be.setStack(2, ItemStack.EMPTY);
            markDirty(world, pos, state);
        }
    }

    public static void beMarkDirty(World world,
                                   BlockPos pos,
                                   BlockState state)
    {
        markDirty(world, pos, state);
    }
}
 */
