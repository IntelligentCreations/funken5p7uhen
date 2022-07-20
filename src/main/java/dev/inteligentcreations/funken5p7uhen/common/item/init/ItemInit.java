package dev.inteligentcreations.funken5p7uhen.common.item.init;

import com.google.common.collect.Lists;
import dev.inteligentcreations.funken5p7uhen.common.block.init.BlockInit;
import dev.inteligentcreations.funken5p7uhen.common.item.group.impl.ItemGroupImpl;
import dev.inteligentcreations.funken5p7uhen.common.item.impl.BlueprintItem;
import dev.inteligentcreations.funken5p7uhen.common.item.impl.DebugItem;
import dev.inteligentcreations.funken5p7uhen.common.item.impl.ItemWithTooltip;
import dev.inteligentcreations.funken5p7uhen.common.util.blueprint.type.BlueprintTypeImpl;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
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
    public static final RegistryObject<Item> EMBER_PUMP = ITEMS.register("ember_pump", () ->
            new BlockItem(BlockInit.EMBER_PUMP.get(), new Item.Settings().group(ItemGroupImpl.INSTANCE)));
    public static final RegistryObject<Item> DEVELOPING_STATION = ITEMS.register("developing_station", () ->
            new BlockItem(BlockInit.DEVELOPING_STATION.get(), new Item.Settings().group(ItemGroupImpl.INSTANCE)));

    public static final RegistryObject<Item> ABANDONED_EMBER_INTERSECTION = ITEMS.register("abandoned_ember_intersection", () ->
            new ItemWithTooltip(new Item.Settings().group(ItemGroupImpl.INSTANCE),
                    Lists.newArrayList(new TranslatableText("tooltip.funken5p7uhen.abandoned")
                            .formatted(Formatting.GRAY, Formatting.ITALIC))));
    public static final RegistryObject<Item> ABANDONED_EMBER_TUNNEL = ITEMS.register("abandoned_ember_tunnel", () ->
            new ItemWithTooltip(new Item.Settings().group(ItemGroupImpl.INSTANCE),
                    Lists.newArrayList(new TranslatableText("tooltip.funken5p7uhen.abandoned")
                            .formatted(Formatting.GRAY, Formatting.ITALIC))));
    public static final RegistryObject<Item> ABANDONED_EMBER_CONTAINER = ITEMS.register("abandoned_ember_container", () ->
            new ItemWithTooltip(new Item.Settings().group(ItemGroupImpl.INSTANCE),
                    Lists.newArrayList(new TranslatableText("tooltip.funken5p7uhen.abandoned")
                            .formatted(Formatting.GRAY, Formatting.ITALIC))));
    public static final RegistryObject<Item> ABANDONED_EMBER_PUMP = ITEMS.register("abandoned_ember_pump", () ->
            new ItemWithTooltip(new Item.Settings().group(ItemGroupImpl.INSTANCE),
                    Lists.newArrayList(new TranslatableText("tooltip.funken5p7uhen.abandoned")
                            .formatted(Formatting.GRAY, Formatting.ITALIC))));

    public static final RegistryObject<Item> EMBER_INTERSECTION_BLUEPRINT = ITEMS.register("ember_intersection_blueprint", () ->
            new BlueprintItem(BlueprintTypeImpl.EMBER_INTERSECTION, new Item.Settings().group(ItemGroupImpl.INSTANCE)));
    public static final RegistryObject<Item> EMBER_TUNNEL_BLUEPRINT = ITEMS.register("ember_tunnel_blueprint", () ->
            new BlueprintItem(BlueprintTypeImpl.EMBER_TUNNEL, new Item.Settings().group(ItemGroupImpl.INSTANCE)));
    public static final RegistryObject<Item> EMBER_CONTAINER_BLUEPRINT = ITEMS.register("ember_container_blueprint", () ->
            new BlueprintItem(BlueprintTypeImpl.EMBER_CONTAINER, new Item.Settings().group(ItemGroupImpl.INSTANCE)));
    public static final RegistryObject<Item> EMBER_PUMP_BLUEPRINT = ITEMS.register("ember_pump_blueprint", () ->
            new BlueprintItem(BlueprintTypeImpl.EMBER_PUMP, new Item.Settings().group(ItemGroupImpl.INSTANCE)));

    public static final RegistryObject<Item> DEBUG_ITEM = ITEMS.register("debug_core", () ->
            new DebugItem(new Item.Settings()));
}
