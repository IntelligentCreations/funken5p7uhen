package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.impl.DevelopingStationScreenHandler;
import dev.inteligentcreations.funken5p7uhen.common.util.inventory.DevelopingStationInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DevelopingStationBlockEntity extends BlockEntity
        implements DevelopingStationInventory, NamedScreenHandlerFactory
{
    private int developingTime;
    private int maxDevelopingTime;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return developingTime;
        }

        @Override
        public void set(int index, int value) {
            developingTime = value;
        }

        @Override
        public int size() {
            return 1;
        }
    };

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public DevelopingStationBlockEntity(BlockPos pos,
                                        BlockState state)
    {
        super(BlockEntityInit.DEVELOPING_STATION.get(), pos, state);
        this.developingTime = 0;
        this.maxDevelopingTime = 5700;
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
        developingTime = nbt.getInt("developingTime");
        maxDevelopingTime = nbt.getInt("maxDevelopingTime");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("developingTime", developingTime);
        nbt.putInt("maxDevelopingTime", maxDevelopingTime);
        super.writeNbt(nbt);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId,
                                    PlayerInventory inv,
                                    PlayerEntity player)
    {
        return new DevelopingStationScreenHandler(syncId, inv, this, propertyDelegate);
    }

    public static void tick(World world,
                            BlockPos pos,
                            BlockState state,
                            DevelopingStationBlockEntity be)
    {
        if (world.isClient()) return;
        if (be.developingTime <= 0)
        {
            be.developingTime = 0;
        }
        if (be.developingTime > be.maxDevelopingTime)
        {
            be.developingTime = be.maxDevelopingTime;
        }
        Optional<DevelopingStationRecipe> match = world.getRecipeManager().getFirstMatch(
                DevelopingStationRecipe.Type.INSTANCE,
                be,
                world
        );
        if (match.isPresent())
        {
            ItemStack stack = be.getStack(1);
            if (stack.getItem() == Items.PAPER)
            {
                be.developingTime += 1;
                if (be.developingTime >= be.maxDevelopingTime)
                {
                    be.setStack(2, match.get().getOutput());
                    be.setStack(0, new ItemStack(be.getStack(0).getItem(),
                            be.getStack(0).getCount() - 1));
                    be.setStack(1, new ItemStack(be.getStack(1).getItem(),
                            be.getStack(1).getCount() - 1));
                    be.developingTime = 0;
                }
                markDirty(world, pos, state);
            }
            else
            {
                be.developingTime -= 1;
                markDirty(world, pos, state);
            }
        }
        else
        {
            be.developingTime -= 1;
            markDirty(world, pos, state);
        }
    }
}
