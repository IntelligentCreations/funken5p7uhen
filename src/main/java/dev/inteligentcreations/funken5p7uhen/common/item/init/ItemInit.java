package dev.inteligentcreations.funken5p7uhen.common.item.init;

import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.item.group.impl.ItemGroupImpl;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_KEY, funken5p7uhen.MOD_ID);

    public static final RegistryObject<Item> EMBERS_INTERSECTION = ITEMS.register("ember_intersection", () ->
            new BlockItem(BlockInit.EMBER_INTERSECTION.get(), new Item.Settings().group(ItemGroupImpl.INSTANCE)));
    public static final RegistryObject<Item> EMBER_TUNNEL = ITEMS.register("ember_tunnel", () ->
            new BlockItem(BlockInit.EMBER_TUNNEL.get(), new Item.Settings().group(ItemGroupImpl.INSTANCE)));
    public static final RegistryObject<Item> EMBER_CONTAINER = ITEMS.register("ember_container", () ->
            new BlockItem(BlockInit.EMBER_CONTAINER.get(), new Item.Settings().group(ItemGroupImpl.INSTANCE)));
}
