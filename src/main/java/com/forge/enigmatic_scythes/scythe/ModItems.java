package com.forge.enigmatic_scythes.scythe;

import com.forge.enigmatic_scythes.EnigmaticScythesMain;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EnigmaticScythesMain.MODID);

    public static final RegistryObject<Item> UNSTABLE_GEM = ITEMS.register("unstable_gem", () -> new Item(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}