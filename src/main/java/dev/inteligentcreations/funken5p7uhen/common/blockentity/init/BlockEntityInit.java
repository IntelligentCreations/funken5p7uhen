package dev.inteligentcreations.funken5p7uhen.common.blockentity.init;

import com.mojang.datafixers.DSL;
import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.*;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registry.BLOCK_ENTITY_TYPE_KEY, funken5p7uhen.MOD_ID);

    public static final RegistryObject<BlockEntityType<EmberContainerBlockEntity>> EMBER_CONTAINER = BLOCK_ENTITIES.register("ember_container", () ->
            BlockEntityType.Builder.create(EmberContainerBlockEntity::new, BlockInit.EMBER_CONTAINER.get()).build(null));
    public static final RegistryObject<BlockEntityType<EmberTunnelBlockEntity>> EMBER_TUNNEL = BLOCK_ENTITIES.register("ember_tunnel", () ->
            BlockEntityType.Builder.create(EmberTunnelBlockEntity::new, BlockInit.EMBER_TUNNEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<EmberIntersectionBlockEntity>> EMBER_INTERSECTION = BLOCK_ENTITIES.register("ember_intersection", () ->
            BlockEntityType.Builder.create(EmberIntersectionBlockEntity::new, BlockInit.EMBER_INTERSECTION.get()).build(null));
    public static final RegistryObject<BlockEntityType<EmberPumpBlockEntity>> EMBER_PUMP = BLOCK_ENTITIES.register("ember_pump", () ->
            BlockEntityType.Builder.create(EmberPumpBlockEntity::new, BlockInit.EMBER_PUMP.get()).build(null));

    public static final RegistryObject<BlockEntityType<DevelopingStationBlockEntity>> DEVELOPING_STATION = BLOCK_ENTITIES.register("developing_station", () ->
            BlockEntityType.Builder.create(DevelopingStationBlockEntity::new, BlockInit.DEVELOPING_STATION.get()).build(DSL.remainderType()));
}
