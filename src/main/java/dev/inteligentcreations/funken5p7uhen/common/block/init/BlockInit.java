package dev.inteligentcreations.funken5p7uhen.common.block.init;

import dev.inteligentcreations.funken5p7uhen.common.block.impl.*;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.block.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_KEY, funken5p7uhen.MOD_ID);

    public static final RegistryObject<Block> EMBER_INTERSECTION = BLOCKS.register("ember_intersection", () ->
            new EmberIntersectionBlock(Block.Settings.of(Material.STONE, MapColor.BLACK).hardness(2.0f).requiresTool()));
    public static final RegistryObject<Block> EMBER_TUNNEL = BLOCKS.register("ember_tunnel", () ->
            new EmberTunnelBlock(Block.Settings.of(Material.STONE, MapColor.BLACK).hardness(2.0f).requiresTool()));
    public static final RegistryObject<Block> EMBER_CONTAINER = BLOCKS.register("ember_container", () ->
            new EmberContainerBlock(Block.Settings.of(Material.STONE, MapColor.BLACK).hardness(2.0f).requiresTool()));
    public static final RegistryObject<Block> EMBER_PUMP = BLOCKS.register("ember_pump", () ->
            new EmberPumpBlock(Block.Settings.of(Material.STONE, MapColor.BLACK).hardness(2.0f).requiresTool()));

    // public static final RegistryObject<Block> DEVELOPING_STATION = BLOCKS.register("developing_station", () ->
    //         new DevelopingStationBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> BASALT_PIGLIN_SCULPTURE = BLOCKS.register("basalt_piglin_sculpture", () ->
            new BasaltPiglinSculptureBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
    public static final RegistryObject<Block> BASALT_THIN_PILLAR = BLOCKS.register("basalt_thin_pillar", () ->
            new ThinPillarBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
    public static final RegistryObject<Block> BASALT_THIN_PILLAR_BASE = BLOCKS.register("basalt_thin_pillar_base", () ->
            new CustomShapedBlock(AbstractBlock.Settings.copy(Blocks.BASALT), VoxelShapes.union(
                    VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.9375),
                    VoxelShapes.cuboid(0.1875, 0.1875, 0.1875, 0.8125, 1, 0.8125)
            )));
    public static final RegistryObject<Block> BASALT_THIN_PILLAR_TOP = BLOCKS.register("basalt_thin_pillar_top", () ->
            new CustomShapedBlock(AbstractBlock.Settings.copy(Blocks.BASALT), VoxelShapes.union(
                    VoxelShapes.cuboid(0.0625, 0.8125, 0.0625, 0.9375, 1, 0.9375),
                    VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.8125, 0.8125, 0.8125)
            )));
}
