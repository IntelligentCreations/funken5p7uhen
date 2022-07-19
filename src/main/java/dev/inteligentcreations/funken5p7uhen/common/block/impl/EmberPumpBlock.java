package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberPumpBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmberPumpBlock extends BlockWithEntity
{
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    public EmberPumpBlock(Settings settings)
    {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(CHARGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state,
                              World world,
                              BlockPos pos,
                              PlayerEntity player,
                              Hand hand,
                              BlockHitResult hit)
    {
        if (world.isClient())
        {
            return ActionResult.SUCCESS;
        }
        else
        {
            if (state.getBlock() instanceof BlockEntityProvider)
            {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof EmberPumpBlockEntity pump)
                {
                    ItemStack stack = player.getStackInHand(hand);
                    if (EmberPumpBlockEntity.CHARGEABLE.containsKey(stack.getItem()))
                    {
                        long addition = EmberPumpBlockEntity.CHARGEABLE.get(stack.getItem());
                        if (pump.blazeEnergy < pump.getMaxEnergy())
                        {
                            pump.blazeEnergy += Math.min(addition, pump.getMaxEnergy() - pump.blazeEnergy);
                            world.playSound(null,
                                    pos,
                                    SoundEvents.ENTITY_BLAZE_HURT,
                                    SoundCategory.BLOCKS,
                                    1.0f,
                                    1.0f);
                            if (!player.isCreative())
                            {
                                if (stack.isDamageable())
                                {
                                    stack.damage(1, player.getRandom(), (ServerPlayerEntity) player);
                                }
                                else
                                {
                                    stack.decrement(1);
                                }
                            }
                            EmberPumpBlockEntity.beMarkDirty(world, pos, state);
                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(CHARGED);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos,
                                         BlockState state)
    {
        return new EmberPumpBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type)
    {
        return checkType(type, BlockEntityInit.EMBER_PUMP.get(), EmberPumpBlockEntity::tick);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state)
    {
        return state.get(CHARGED);
    }

    @Override
    public int getComparatorOutput(BlockState state,
                                   World world,
                                   BlockPos pos)
    {
        return 1;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView view,
                                      BlockPos pos,
                                      ShapeContext context)
    {
        return VoxelShapes.cuboid(0.01f, 0.01f, 0.01f,
                0.99f, 0.99f, 0.99f);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state,
                                        BlockView world,
                                        BlockPos pos,
                                        ShapeContext context)
    {
        return VoxelShapes.cuboid(0f, 0f, 0f,
                1f, 1f, 1f);
    }

    @Override
    public void appendTooltip(ItemStack itemStack,
                              BlockView world,
                              List<Text> tooltip,
                              TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("block.funken5p7uhen.ember_pump.tooltip")
                .formatted(Formatting.GRAY, Formatting.ITALIC));
    }
}
