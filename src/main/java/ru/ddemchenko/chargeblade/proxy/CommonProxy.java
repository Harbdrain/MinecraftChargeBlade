package ru.ddemchenko.chargeblade.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;
import ru.ddemchenko.chargeblade.ChargeBladeEventHandler;
import ru.ddemchenko.chargeblade.config.ChargeBladeConfig;
import ru.ddemchenko.chargeblade.gui.RenderGuiHandler;
import ru.ddemchenko.chargeblade.packets.ChargeBladePacketHandler;
import ru.ddemchenko.chargeblade.packets.MyMessage;
import ru.ddemchenko.chargeblade.packets.MyMessageHandler;

import java.io.File;

public class CommonProxy {
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event) {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "ChargeBlade.cfg"));
        ChargeBladeConfig.readConfig();
    }
    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
    }
}
