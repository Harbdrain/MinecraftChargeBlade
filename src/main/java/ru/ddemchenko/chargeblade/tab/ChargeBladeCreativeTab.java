package ru.ddemchenko.chargeblade.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChargeBladeCreativeTab extends CreativeTabs {
    public ChargeBladeCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.EMERALD);
    }
}
