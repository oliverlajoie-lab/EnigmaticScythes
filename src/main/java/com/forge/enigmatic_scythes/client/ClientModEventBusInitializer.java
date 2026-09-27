package com.forge.enigmatic_scythes.client;

import com.forge.enigmatic_scythes.EnigmaticScythesMain;
import com.forge.enigmatic_scythes.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnigmaticScythesMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventBusInitializer {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.COBWEB_PROJECTILE.get(), CobwebProjectileRenderer::new);
    }
}