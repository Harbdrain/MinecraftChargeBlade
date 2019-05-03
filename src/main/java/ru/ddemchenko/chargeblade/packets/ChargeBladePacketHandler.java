package ru.ddemchenko.chargeblade.packets;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.ddemchenko.chargeblade.ChargeBlade;

public class ChargeBladePacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ChargeBlade.MODID);

    public static void init() {
        INSTANCE.registerMessage(MyMessageHandler.class, MyMessage.class, 0, Side.SERVER);
    }
}
