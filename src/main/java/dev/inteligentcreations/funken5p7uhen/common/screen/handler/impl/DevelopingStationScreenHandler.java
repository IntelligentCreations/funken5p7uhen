package dev.inteligentcreations.funken5p7uhen.common.screen.handler.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.DevelopingStationBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.category.init.RecipeCategoryInit;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.init.ScreenHandlerInit;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.slot.impl.DevelopingResultSlot;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.slot.impl.InsertLimitedSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Optional;

public class DevelopingStationScreenHandler {} /* extends AbstractRecipeScreenHandler<Inventory>
{
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private final PlayerEntity player;

    public DevelopingStationScreenHandler(int syncId,
                                          PlayerInventory playerInventory)
    {
        this(syncId, playerInventory, new SimpleInventory(3), ScreenHandlerContext.EMPTY);
    }

    public DevelopingStationScreenHandler(int syncId,
                                          PlayerInventory playerInventory,
                                          Inventory inventory,
                                          ScreenHandlerContext context)
    {
        super(ScreenHandlerInit.DEVELOPING_STATION.get(), syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        this.context = context;
        this.player = playerInventory.player;
        inventory.onOpen(playerInventory.player);
        int m;
        int l;
        this.addSlot(new Slot(inventory, 0, 44, 33));
        this.addSlot(new InsertLimitedSlot(inventory, 1, 62, 33,
                new ItemStack(Items.PAPER)));
        this.addSlot(new DevelopingResultSlot(inventory, 2, 107, 33));
        for (m = 0; m < 3; ++m)
        {
            for (l = 0; l < 9; ++l)
            {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m)
        {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    public static DevelopingStationScreenHandler create(int syncId,
                                                        PlayerInventory playerInventory,
                                                        PacketByteBuf buffer)
    {
        return new DevelopingStationScreenHandler(syncId, playerInventory);
    }

    protected static void updateResult(ScreenHandler handler,
                                       World world,
                                       PlayerEntity player,
                                       Inventory inv)
    {
        if (!world.isClient)
        {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<DevelopingStationRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(
                    DevelopingStationRecipe.Type.INSTANCE,
                    inv,
                    world
            );
            if (optional.isPresent())
            {
                DevelopingStationRecipe recipe = optional.get();
                if (inv.getStack(1).getItem() == Items.PAPER)
                {
                    itemStack = recipe.craft(inv);
                }
            }
            inv.setStack(2, itemStack);
            handler.setPreviousTrackedSlot(2, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 2, itemStack));
        }
    }

    public void onContentChanged(Inventory inventory)
    {
        this.context.run((world, pos) -> {
                updateResult(this, world, this.player, this.inventory);
                DevelopingStationBlockEntity.beMarkDirty(world, pos, world.getBlockState(pos));
        });
        super.onContentChanged(inventory);
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player,
                                  int invSlot)
    {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack())
        {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size())
            {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(originalStack, 0, this.inventory.size(), false))
            {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty())
            {
                slot.setStack(ItemStack.EMPTY);
            }
            else
            {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public void populateRecipeFinder(RecipeMatcher finder)
    {
        DefaultedList<ItemStack> stacks = DefaultedList.of();
        for (int i = 0; i < this.inventory.size(); i++)
        {
            stacks.add(inventory.getStack(i));
        }
        for (ItemStack itemStack : stacks) {
            finder.addUnenchantedInput(itemStack);
        }
    }

    @Override
    public void clearCraftingSlots()
    {
        for (int i = 0; i < this.inventory.size(); i++)
        {
            inventory.getStack(i).decrement(inventory.getStack(i).getCount());
        }
    }

    @Override
    public boolean matches(Recipe<? super Inventory> recipe)
    {
        return recipe.matches(this.inventory, player.getWorld());
    }

    @Override
    public int getCraftingResultSlotIndex()
    {
        return 2;
    }

    @Override
    public int getCraftingWidth()
    {
        return 2;
    }

    @Override
    public int getCraftingHeight()
    {
        return 1;
    }

    @Override
    public int getCraftingSlotCount()
    {
        return 3;
    }

    @Override
    public RecipeBookCategory getCategory()
    {
        return RecipeCategoryInit.DEVELOPING;
    }

    @Override
    public boolean canInsertIntoSlot(int index)
    {
        return index != 2;
    }
}
*/
