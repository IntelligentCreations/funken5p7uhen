package dev.inteligentcreations.funken5p7uhen.common.screen.handler.impl;

import dev.inteligentcreations.funken5p7uhen.common.screen.handler.init.ScreenHandlerInit;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.slot.impl.ExtractOnlySlot;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.slot.impl.InsertLimitedSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DevelopingStationScreenHandler extends ScreenHandler
{
    private final Inventory inventory;
    private PropertyDelegate propertyDelegate;

    public DevelopingStationScreenHandler(int syncId,
                                          PlayerInventory playerInventory)
    {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(1));
    }

    public DevelopingStationScreenHandler(int syncId,
                                          PlayerInventory playerInventory,
                                          Inventory inventory,
                                          PropertyDelegate propertyDelegate)
    {
        super(ScreenHandlerInit.DEVELOPING_STATION.get(), syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        inventory.onOpen(playerInventory.player);
        int m;
        int l;
        this.addSlot(new Slot(inventory, 0, 44, 33));
        this.addSlot(new InsertLimitedSlot(inventory, 1, 62, 33,
                new ItemStack(Items.PAPER)));
        this.addSlot(new ExtractOnlySlot(inventory, 2, 107, 33));
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

    public int getProgress()
    {
        return propertyDelegate.get(0);
    }

    public static DevelopingStationScreenHandler create(int syncId,
                                                        PlayerInventory playerInventory,
                                                        PacketByteBuf buffer)
    {
        return new DevelopingStationScreenHandler(syncId, playerInventory);
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
}
