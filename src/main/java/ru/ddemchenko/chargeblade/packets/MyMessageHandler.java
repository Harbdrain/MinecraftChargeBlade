package ru.ddemchenko.chargeblade.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.ddemchenko.chargeblade.items.ChargeBladeSword;

public class MyMessageHandler implements IMessageHandler<MyMessage, IMessage> {
    @Override
    public IMessage onMessage(MyMessage message, MessageContext ctx) {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
        ItemStack item = serverPlayer.getHeldItemMainhand();

        serverPlayer.getServerWorld().addScheduledTask(() -> ChargeBladeSword.switchMode(item));
        return null;
    }
}
