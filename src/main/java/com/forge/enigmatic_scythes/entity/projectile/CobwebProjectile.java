package com.forge.enigmatic_scythes.entity.projectile;

import com.forge.enigmatic_scythes.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class CobwebProjectile extends ThrowableItemProjectile {
    public CobwebProjectile(EntityType<? extends ThrowableItemProjectile> p_37432_, double p_37433_, double p_37434_, double p_37435_, Level p_37436_) {
        super(p_37432_, p_37433_, p_37434_, p_37435_, p_37436_);
    }

    public CobwebProjectile(EntityType<CobwebProjectile> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    int elapsed = 0;
    int elapsedGround = 0;
    int maxGroundTime = 100;
    int maxLifetime = 20;

    @Override
    protected Item getDefaultItem() {
        return Items.COBWEB;
    }

    @Override
    public void setItem(ItemStack p_37447_) {
        super.setItem(new ItemStack(Items.COBWEB));
    }

    @Override
    public void shootFromRotation(Entity p_37252_, float p_37253_, float p_37254_, float p_37255_, float p_37256_, float p_37257_) {
        super.shootFromRotation(p_37252_, p_37253_, p_37254_, p_37255_, p_37256_, p_37257_);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity target = result.getEntity();

        if (target instanceof LivingEntity livingEntity && target != this.getOwner()) {
            DamageSource damageSource = this.level().damageSources().indirectMagic(this, this.getOwner());

            livingEntity.invulnerableTime = 0;

            target.hurt(damageSource, 10.0F);
            target.setSecondsOnFire(5);
            livingEntity.addEffect(new MobEffectInstance(ModEffects.WEBBED.get(), 100, 1, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1, false, false));

            this.level().playSound(null, target.blockPosition(), SoundEvents.TRIPWIRE_CLICK_ON, SoundSource.AMBIENT, 2.0f, 1.0f);

            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        this.setDeltaMovement(Vec3.ZERO);
        this.getPersistentData().putBoolean("isOnGround", true);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getPersistentData().getBoolean("isOnGround")) {
            elapsedGround++;

            if (elapsedGround >= maxGroundTime) {
                this.discard();
            }
        } else {
            elapsed++;

            if (elapsed >= maxLifetime) {
                this.discard();
            }
        }
    }

    @Override
    protected float getGravity() {
        return 0.0f;
    }
}