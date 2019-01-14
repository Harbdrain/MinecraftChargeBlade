package ru.ddemchenko.chargeblade.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import ru.ddemchenko.chargeblade.ChargeBlade;
import ru.ddemchenko.chargeblade.Vars;

public class FlasksGui extends Gui {
    private ResourceLocation bar;
    public FlasksGui(Minecraft mc, int warmth, int flasks) {
        bar = new ResourceLocation(ChargeBlade.MODID, Vars.flasksTexture[warmth][flasks]);
        ScaledResolution scaled = new ScaledResolution(mc);
        mc.renderEngine.bindTexture(bar);

        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, (mc.displayWidth*215)/1920, (mc.displayHeight*50)/1080, (mc.displayWidth*215)/1920, (mc.displayHeight*50)/1080);
    }
}
