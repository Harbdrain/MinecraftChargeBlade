package ru.ddemchenko.chargeblade;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.ddemchenko.chargeblade.init.ModItems;
import ru.ddemchenko.chargeblade.proxy.CommonProxy;
import ru.ddemchenko.chargeblade.tab.ChargeBladeCreativeTab;

@Mod(modid = ChargeBlade.MODID, name = ChargeBlade.NAME, version = ChargeBlade.VERSION)
public class ChargeBlade
{
    public static final String MODID = "chargeblade";
    public static final String NAME = "ChargeBlade";
    public static final String VERSION = "1.1";

    @SidedProxy(clientSide = "ru.ddemchenko.chargeblade.proxy.ClientProxy", serverSide = "ru.ddemchenko.chargeblade.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static ChargeBlade instance;

    public static ChargeBladeCreativeTab tab;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)  {
        tab = new ChargeBladeCreativeTab(CreativeTabs.getNextID(), "tab_chargeblade");
        ModItems.init();
        Vars.init();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
