package dev.inteligentcreations.funken5p7uhen.common.block.init;

import dev.inteligentcreations.funken5p7uhen.common.block.impl.*;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.block.*;
import net.minecraft.util.registry.Registry;
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

    public static final RegistryObject<Block> RESEARCHING_TABLE = BLOCKS.register("researching_table", () ->
            new ResearchingTableBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
}
