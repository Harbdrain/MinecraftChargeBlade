package ru.ddemchenko.chargeblade.items;

import net.minecraft.item.Item;
import ru.ddemchenko.chargeblade.ChargeBlade;

public class ChargeBladeShield extends Item {
    public ChargeBladeShield(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(ChargeBlade.tab);
    }
}
