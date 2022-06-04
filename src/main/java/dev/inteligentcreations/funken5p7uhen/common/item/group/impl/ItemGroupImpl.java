package dev.inteligentcreations.funken5p7uhen.common.item.group.impl;

import dev.inteligentcreations.funken5p7uhen.common.item.init.ItemInit;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupImpl extends ItemGroup {
    public static final ItemGroupImpl INSTANCE = new ItemGroupImpl();

    public ItemGroupImpl() {
        super(funken5p7uhen.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return ItemInit.EMBERS_INTERSECTION.get().getDefaultStack();
    }
}
