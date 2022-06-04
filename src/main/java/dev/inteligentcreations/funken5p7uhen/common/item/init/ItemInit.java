package dev.inteligentcreations.funken5p7uhen.common.item.init;

import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.item.group.impl.ItemGroupImpl;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_KEY, funken5p7uhen.MOD_ID);

    public static final RegistryObject<Item> EMBERS_INTERSECTION = ITEMS.register("ember_section", () ->
            new BlockItem(BlockInit.EMBER_INTERSECTION.get(), new Item.Settings().group(ItemGroupImpl.INSTANCE)));
}