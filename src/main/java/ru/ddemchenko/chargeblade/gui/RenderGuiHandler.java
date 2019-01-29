package ru.ddemchenko.chargeblade.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.ddemchenko.chargeblade.Vars;

public class RenderGuiHandler {
    private static NBTTagCompound nbt;
    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.player.getHeldItemMainhand().getUnlocalizedName().equals("item.chargeblade_sword")) {
            NBTTagCompound nbt = new NBTTagCompound();
            int flasks = 0, warmth = 0;
            if (mc.player.getHeldItemMainhand().hasTagCompound())
                nbt = mc.player.getHeldItemMainhand().getTagCompound();
            if (nbt.hasKey("flasks")) flasks = nbt.getInteger("flasks");
            if (nbt.hasKey("warmth")) warmth = nbt.getInteger("warmth");

            new FlasksGui(mc, warmth / (Vars.maxWarmth / 3), flasks);
        }
    }
}
