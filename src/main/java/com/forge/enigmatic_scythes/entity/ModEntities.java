package com.forge.enigmatic_scythes.entity;

import com.forge.enigmatic_scythes.EnigmaticScythesMain;
import com.forge.enigmatic_scythes.entity.projectile.CobwebProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EnigmaticScythesMain.MODID);

    public static final RegistryObject<EntityType<CobwebProjectile>> COBWEB_PROJECTILE = ENTITY_TYPES.register("cobweb_projectile", () ->
            EntityType.Builder.<CobwebProjectile>of(
                    CobwebProjectile::new,
                    MobCategory.MISC
            )
                    .sized(1.0f, 1.0f)
                    .build("cobweb_projectile"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}