package ru.ddemchenko.chargeblade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.ddemchenko.chargeblade.items.ChargeBladeSword;
import ru.ddemchenko.chargeblade.proxy.ClientProxy;

@Mod.EventBusSubscriber(modid = ChargeBlade.MODID)
public class ChargeBladeEventHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(InputEvent.KeyInputEvent event) {
        System.out.println("Key Input Event");

        KeyBinding[] keyBindings = ClientProxy.keyBindings;

        if (keyBindings[0].isPressed()) {
            ItemStack item = Minecraft.getMinecraft().player.inventory.getCurrentItem();
            if (item.getUnlocalizedName().equals("item.chargeblade_sword")) {
                ChargeBladeSword.switchMode(item);
            }
        }
    }
}
