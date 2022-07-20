package dev.inteligentcreations.funken5p7uhen.common.screen.handler.slot.impl;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ExtractOnlySlot
        extends Slot
{
    public ExtractOnlySlot(Inventory inventory,
                           int index,
                           int x,
                           int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return false;
    }
}
