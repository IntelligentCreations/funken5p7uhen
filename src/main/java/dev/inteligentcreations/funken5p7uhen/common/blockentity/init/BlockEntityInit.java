package dev.inteligentcreations.funken5p7uhen.common.blockentity.init;

import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberContainerBlockEntity;
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
}
