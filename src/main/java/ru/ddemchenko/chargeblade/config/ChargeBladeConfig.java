package ru.ddemchenko.chargeblade.config;

import net.minecraftforge.common.config.Configuration;
import ru.ddemchenko.chargeblade.proxy.CommonProxy;

public class ChargeBladeConfig {

    public static final String CATEGORY_NAME_BUFFS = "Buffs",CATEGORY_NAME_DAMAGE = "Damage";

    public static int potionSPEEDlvl;
    public static int potionREGENERATIONlvl;
    public static int potionRESISTANCElvl;
    public static int potionSLOWNESSlvl;
    public static int damageSwordMode;
    public static int damageAxeMode;
    public static int damageDischarge;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initBuffConfig(cfg);
            initDamageConfig(cfg);
        } catch (Exception e1) {
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initBuffConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_NAME_BUFFS, "Buff configuration");
        potionSPEEDlvl = cfg.getInt("speedLvl", CATEGORY_NAME_BUFFS, 1,0,10,"Speed lvl");
        potionREGENERATIONlvl = cfg.getInt("regenLvl", CATEGORY_NAME_BUFFS, 0,0,10,"Regeneration lvl");
        potionRESISTANCElvl = cfg.getInt("resistanceLvl", CATEGORY_NAME_BUFFS, 4,0,4,"Resistance lvl");
        potionSLOWNESSlvl = cfg.getInt("slownessLvl", CATEGORY_NAME_BUFFS, 5,0,10,"Slowness lvl");
    }

    private static void initDamageConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_NAME_DAMAGE, "Damage configuration");
        damageSwordMode = cfg.getInt("swordDamage", CATEGORY_NAME_DAMAGE, 6, 0, 100, "Sword mode damage");
        damageAxeMode = cfg.getInt("axeDamage", CATEGORY_NAME_DAMAGE, 7, 0, 100, "Axe mode damage");
        damageDischarge = cfg.getInt("dischargeDamage", CATEGORY_NAME_DAMAGE, 9, 0, 100, "Discharge damage");
    }
}
