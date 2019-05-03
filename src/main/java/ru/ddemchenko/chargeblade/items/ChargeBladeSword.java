package ru.ddemchenko.chargeblade.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import ru.ddemchenko.chargeblade.ChargeBlade;
import ru.ddemchenko.chargeblade.Vars;
import ru.ddemchenko.chargeblade.config.ChargeBladeConfig;

import java.util.List;

public class ChargeBladeSword extends Item {
    public ChargeBladeSword(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(ChargeBlade.tab);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        setNbtTags(stack);

        NBTTagCompound nbt = stack.getTagCompound();
        boolean axe = nbt.getBoolean("axe");
        int flasks = nbt.getInteger("flasks"), warmth = nbt.getInteger("warmth");

        if (!axe) {
            if (warmth < Vars.maxWarmth) {
                target.attackEntityFrom(DamageSource.GENERIC, ChargeBladeConfig.damageSwordMode);
                nbt.setInteger("warmth", warmth + 1);
            }
            else {
                target.attackEntityFrom(DamageSource.GENERIC, 1);
            }
            stack.setTagCompound(nbt);
            return super.hitEntity(stack, target, attacker);
        }

        target.attackEntityFrom(DamageSource.GENERIC, flasks > 0 ? ChargeBladeConfig.damageAxeMode : 1);
        nbt.setInteger("flasks", Math.max(flasks - 1, 0));
        stack.setTagCompound(nbt);
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving.isSneaking()) {
            switchMode(stack);
        }
        return super.onEntitySwing(entityLiving, stack);
    }

    public static void switchMode(ItemStack stack) {
        setNbtTags(stack);
        NBTTagCompound nbt = stack.getTagCompound();
        nbt.setBoolean("axe", !nbt.getBoolean("axe"));
        stack.setTagCompound(nbt);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        if (worldIn.isRemote)
            return super.onItemRightClick(worldIn, player, hand);

        setNbtTags(player.getHeldItemMainhand());
        NBTTagCompound nbt = player.getHeldItemMainhand().getTagCompound();
        boolean axe = nbt.getBoolean("axe");
        int warmth = nbt.getInteger("warmth"), flasks = nbt.getInteger("flasks");


        if (axe && player.isSneaking()) {
            chargedRmb(player);
            return super.onItemRightClick(worldIn, player, hand);
        }
        if (axe && !player.isSneaking()) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600*flasks, ChargeBladeConfig.potionSPEEDlvl));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600*flasks, ChargeBladeConfig.potionREGENERATIONlvl));

            nbt.setInteger("flasks", 0);
            player.getHeldItemMainhand().setTagCompound(nbt);
            return super.onItemRightClick(worldIn, player, hand);
        }

        if (!axe && player.isSneaking()) {
            if (warmth >= 4)
                nbt.setInteger("flasks", 6);
            else if (warmth >= 2)
                nbt.setInteger("flasks", Math.min(6, 3 + flasks));
            nbt.setInteger("warmth", 0);
            player.getHeldItemMainhand().setTagCompound(nbt);
            return super.onItemRightClick(worldIn, player, hand);
        }

        if (!axe && !player.isSneaking()) {
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, ChargeBladeConfig.potionRESISTANCElvl));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 100));
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, ChargeBladeConfig.potionSLOWNESSlvl));
            return super.onItemRightClick(worldIn, player, hand);
        }

        return super.onItemRightClick(worldIn, player, hand);
    }

    private static void chargedRmb(EntityLivingBase player) {
        Vec3d vec = player.getLookVec();
        double pX = player.posX, pY = player.posY, pZ = player.posZ;
        pX += vec.x*2;
        pY += vec.y*2;
        pZ += vec.z*2;
        int flasks = 0;
        NBTTagCompound nbt = player.getHeldItemMainhand().getTagCompound();
        flasks = nbt.getInteger("flasks");
        for (int i = 0; i < flasks; i++) {
            player.world.createExplosion(null,pX + i * vec.x, pY + i * vec.y, pZ + i * vec.z, 0f, true);
            damageEntity(player,pX + i * vec.x, pY + i * vec.y, pZ + i * vec.z);
        }

        nbt.setInteger("flasks", 0);
        player.getHeldItemMainhand().setTagCompound(nbt);
    }



    private static void damageEntity(EntityLivingBase player, double pX, double pY, double pZ) {
        double radius = 3;
        AxisAlignedBB bb = new AxisAlignedBB(pX - radius, pY - radius, pZ - radius, pX + radius, pY + radius, pZ + radius);
        List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class, bb);
        for (EntityLivingBase entity : list) {
            if (!(entity instanceof EntityPlayer)) {
                entity.setHealth(entity.getHealth() - ChargeBladeConfig.damageDischarge);
                entity.attackEntityFrom(DamageSource.GENERIC, 1);
                entity.knockBack(player, 0.25f, -player.getLookVec().x, -player.getLookVec().z);
            }
        }
    }

     private static void setNbtTags(ItemStack stack) {
        NBTTagCompound nbt = new NBTTagCompound();
        boolean axe = false;
        int flasks = 0, warmth = 0;

        if (stack.hasTagCompound()) nbt = stack.getTagCompound();
        if (nbt.hasKey("axe")) axe = nbt.getBoolean("axe");
        if (nbt.hasKey("flasks")) flasks = nbt.getInteger("flasks");
        if (nbt.hasKey("warmth")) warmth = nbt.getInteger("warmth");

        nbt.setBoolean("axe", axe);
        nbt.setInteger("flasks", flasks);
        nbt.setInteger("warmth", warmth);
        stack.setTagCompound(nbt);
    }
}
