package com.forge.enigmatic_scythes.effect;

import com.forge.enigmatic_scythes.EnigmaticScythesMain;
import com.forge.enigmatic_scythes.effect.effects.Webbed;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EnigmaticScythesMain.MODID);

    public static final RegistryObject<MobEffect> WEBBED = MOB_EFFECTS.register("webbed", () -> new Webbed(MobEffectCategory.HARMFUL, 0xFFFFFFFF)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                    UUID.randomUUID().toString(),
                    -0.3d,
                    AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}