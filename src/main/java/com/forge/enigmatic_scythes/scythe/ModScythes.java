package com.forge.enigmatic_scythes.scythe;

import com.forge.enigmatic_scythes.EnigmaticScythesMain;
import com.forge.enigmatic_scythes.scythe.scythes.ArachnidScythe;
import com.forge.enigmatic_scythes.scythe.scythes.ScytheOfCurse;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModScythes {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EnigmaticScythesMain.MODID);

    public static final RegistryObject<ScytheOfCurse> SCYTHE_OF_CURSE = ITEMS.register("scythe_of_curse", () -> new ScytheOfCurse(Tiers.NETHERITE,
            3,
            -2.6f,
            new Item.Properties()));

    public static final RegistryObject<ArachnidScythe> ARACHNID_SCYTHE = ITEMS.register("arachnid_scythe", () -> new ArachnidScythe(Tiers.NETHERITE,
            3,
            -2.6f,
            new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}