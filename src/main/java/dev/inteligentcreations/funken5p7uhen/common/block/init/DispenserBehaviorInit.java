package dev.inteligentcreations.funken5p7uhen.common.block.init;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberPumpBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Map;

public class DispenserBehaviorInit
{
    public static void registerBehaviors()
    {
        for (Map.Entry<Item, Long> entry
                : EmberPumpBlockEntity.CHARGEABLE.entrySet())
        {
            Item provider = entry.getKey();
            DispenserBlock.registerBehavior(provider, new FallibleItemDispenserBehavior()
            {
                @Override
                public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack)
                {
                    Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
                    BlockPos pos = pointer.getPos().offset(direction);
                    World world = pointer.getWorld();
                    BlockState state = world.getBlockState(pos);
                    if (state.isOf(BlockInit.EMBER_PUMP.get()))
                    {
                        BlockEntity be = world.getBlockEntity(pos);
                        if (be instanceof EmberPumpBlockEntity pump)
                        {
                            long addition = EmberPumpBlockEntity.CHARGEABLE.get(stack.getItem());
                            if (pump.blazeEnergy < pump.getMaxEnergy())
                            {
                                pump.blazeEnergy += Math.min(addition, pump.getMaxEnergy() - pump.blazeEnergy);
                                if (stack.isDamageable())
                                {
                                    stack.damage(1, world.random, null);
                                }
                                else
                                {
                                    stack.decrement(1);
                                }
                                setSuccess(true);
                                EmberPumpBlockEntity.beMarkDirty(world, pos, state);
                                return stack;
                            }
                            else return super.dispenseSilently(pointer, stack);
                        }
                        else return super.dispenseSilently(pointer, stack);
                    }
                    else return super.dispenseSilently(pointer, stack);
                }
            });
        }
    }
}
