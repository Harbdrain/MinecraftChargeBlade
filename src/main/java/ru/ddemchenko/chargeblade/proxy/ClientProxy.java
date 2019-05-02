package ru.ddemchenko.chargeblade.proxy;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;
import ru.ddemchenko.chargeblade.ChargeBlade;
import ru.ddemchenko.chargeblade.gui.RenderGuiHandler;
import ru.ddemchenko.chargeblade.init.ModItems;

public class ClientProxy extends CommonProxy {

    public static KeyBinding[] keyBindings;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(ChargeBlade.MODID);

        ModelBakery.registerItemVariants(ModItems.sword, new ResourceLocation(ChargeBlade.MODID, "chargeblade_axe"), new ResourceLocation(ChargeBlade.MODID, "chargeblade_sword"));
        ModelLoader.setCustomMeshDefinition(ModItems.sword, stack -> {
            NBTTagCompound nbt = new NBTTagCompound();
            boolean axe = false;

            if (stack.hasTagCompound()) nbt = stack.getTagCompound();
            if (nbt.hasKey("axe")) axe = nbt.getBoolean("axe");

            if (axe)
                return new ModelResourceLocation( "chargeblade:chargeblade_axe", "inventory");
            return new ModelResourceLocation( "chargeblade:chargeblade_sword", "inventory");
        });

        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        keyBindings = new KeyBinding[1];

        keyBindings[0] = new KeyBinding("key.structure.desc", Keyboard.KEY_V, "key.chargeblade.category");

        for (int i = 0; i < keyBindings.length; i++) {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
        super.postInit(event);
    }
}
