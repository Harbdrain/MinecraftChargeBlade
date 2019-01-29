package ru.ddemchenko.chargeblade.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.ddemchenko.chargeblade.ChargeBlade;
import ru.ddemchenko.chargeblade.items.ChargeBladeSword;

@Mod.EventBusSubscriber(modid = ChargeBlade.MODID)
public class ModItems {
    public static Item sword;

    public static void init() {
        sword = new ChargeBladeSword("chargeblade_sword");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(sword);
    }
}
