package dev.inteligentcreations.funken5p7uhen.common.blockentity.init;

import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registry.BLOCK_ENTITY_TYPE_KEY, funken5p7uhen.MOD_ID);
}
