package com.forge.enigmatic_scythes.effect.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class Webbed extends MobEffect {
    public Webbed(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int particleChance = (int) (Math.random() * 3);

        if (particleChance == 2) {
            Level level = entity.level();

            if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                int randX = (int) (Math.random() * 4) - 2;
                int randY = (int) (Math.random() * 4) - 2;
                int randZ = (int) (Math.random() * 4) - 2;

                int offsetX = entity.blockPosition().getX() + randX;
                int offsetY = entity.blockPosition().getY() + randY;
                int offsetZ = entity.blockPosition().getZ() + randZ;

                serverLevel.sendParticles(ParticleTypes.POOF, offsetX, offsetY, offsetZ, 1, 0, 0, 0, 0.05);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}