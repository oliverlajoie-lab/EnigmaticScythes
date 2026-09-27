package com.forge.enigmatic_scythes.client;

import com.forge.enigmatic_scythes.entity.projectile.CobwebProjectile;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class CobwebProjectileRenderer extends ThrownItemRenderer<CobwebProjectile> {
    public CobwebProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
}