package ru.ddemchenko.chargeblade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import ru.ddemchenko.chargeblade.items.ChargeBladeSword;
import ru.ddemchenko.chargeblade.packets.ChargeBladePacketHandler;
import ru.ddemchenko.chargeblade.packets.MyMessage;
import ru.ddemchenko.chargeblade.proxy.ClientProxy;

//@Mod.EventBusSubscriber(modid = ChargeBlade.MODID)
public class ChargeBladeEventHandler {
    @SubscribeEvent
    public static void onEvent(InputEvent.KeyInputEvent event) {
        System.out.println("work!");
        KeyBinding[] keyBindings = ClientProxy.keyBindings;

        if (keyBindings[0].isKeyDown()) {
           ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
            if (stack.getUnlocalizedName().equals("item.chargeblade_sword")) {
                ChargeBladePacketHandler.INSTANCE.sendToServer(new MyMessage());
                System.out.println("Key Input Event");
            }
        }
    }
}
