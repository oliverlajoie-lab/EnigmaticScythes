package com.forge.enigmatic_scythes;

import com.forge.enigmatic_scythes.effect.ModEffects;
import com.forge.enigmatic_scythes.entity.ModEntities;
import com.forge.enigmatic_scythes.scythe.ModItems;
import com.forge.enigmatic_scythes.scythe.ModScythes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(EnigmaticScythesMain.MODID)
public class EnigmaticScythesMain {
    public static final String MODID = "enigmatic_scythes";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public EnigmaticScythesMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        registerModStuff(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private static void registerModStuff(IEventBus modBus) {
        ModScythes.register(modBus);
        ModItems.register(modBus);
        ModEntities.register(modBus);
        ModEffects.register(modBus);

        CREATIVE_MODE_TABS.register(modBus);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModScythes.SCYTHE_OF_CURSE);
            event.accept(ModScythes.ARACHNID_SCYTHE);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.UNSTABLE_GEM);
        }
    }
}
