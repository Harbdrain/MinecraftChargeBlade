package ru.ddemchenko.chargeblade.proxy;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.Sys;
import ru.ddemchenko.chargeblade.ChargeBlade;
import ru.ddemchenko.chargeblade.gui.RenderGuiHandler;
import ru.ddemchenko.chargeblade.init.ModItems;

public class ClientProxy implements CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(ChargeBlade.MODID);

        //ModelLoader.setCustomModelResourceLocation(ModItems.sword, 0, new ModelResourceLocation("chargeblade:chargeblade_axe", "inventory"));
        //ModelLoader.setCustomModelResourceLocation(ModItems.shield, 0, new ModelResourceLocation("chargeblade:chargeblade_shield", "inventory"));
        ModelBakery.registerItemVariants(ModItems.sword, new ResourceLocation(ChargeBlade.MODID, "chargeblade_axe"), new ResourceLocation(ChargeBlade.MODID, "chargeblade_sword"));
        ModelLoader.setCustomMeshDefinition(ModItems.sword, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                NBTTagCompound nbt = new NBTTagCompound();
                boolean axe = false;

                if (stack.hasTagCompound()) nbt = stack.getTagCompound();
                if (nbt.hasKey("axe")) axe = nbt.getBoolean("axe");

                if (axe)
                    return new ModelResourceLocation( "chargeblade:chargeblade_axe", "inventory");
                return new ModelResourceLocation( "chargeblade:chargeblade_sword", "inventory");
            }
        });


        ModelBakery.registerItemVariants(ModItems.shield, new ResourceLocation(ChargeBlade.MODID, "chargeblade_shield"));
        ModelLoader.setCustomMeshDefinition(ModItems.shield, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("chargeblade:chargeblade_shield", "inventory");
            }
        });
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
    }
}
