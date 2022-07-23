package dev.inteligentcreations.funken5p7uhen.common.screen.handler.slot.impl;

import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import dev.inteligentcreations.funken5p7uhen.common.util.inventory.DevelopingStationInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.Optional;

public class DevelopingResultSlot extends ExtractOnlySlot
{
    private final Inventory inventory;

    public DevelopingResultSlot(Inventory inventory,
                                int index,
                                int x,
                                int y)
    {
        super(inventory, index, x, y);
        this.inventory = inventory;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack)
    {
        Optional<DevelopingStationRecipe> recipe = player.getWorld().getServer().getRecipeManager().getFirstMatch(
                DevelopingStationRecipe.Type.INSTANCE,
                inventory,
                player.getWorld()
        );
        if (recipe.isPresent() && !stack.isEmpty())
        {
            inventory.getStack(0).decrement(stack.getCount());
            inventory.getStack(1).decrement(stack.getCount());
        }
        super.onTakeItem(player, stack);
    }
}
