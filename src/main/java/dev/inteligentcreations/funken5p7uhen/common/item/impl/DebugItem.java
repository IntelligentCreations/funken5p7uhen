package dev.inteligentcreations.funken5p7uhen.common.item.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberPumpBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstanceProvider;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;

public class DebugItem extends Item
{
    public DebugItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        if (context.getWorld().isClient()) return ActionResult.PASS;
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() instanceof BlockEntityProvider)
        {
            BlockEntity be = context.getWorld().getBlockEntity(context.getBlockPos());
            if (be instanceof LavaContainerInstanceProvider provider)
            {
                if (be instanceof EmberPumpBlockEntity pump)
                {
                    context.getPlayer().sendMessage(new LiteralText(provider.getStoredLava() + " and " + pump.blazeEnergy), true);
                }
                else
                {
                    context.getPlayer().sendMessage(new LiteralText(String.valueOf(provider.getStoredLava())), true);
                }
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }
}
