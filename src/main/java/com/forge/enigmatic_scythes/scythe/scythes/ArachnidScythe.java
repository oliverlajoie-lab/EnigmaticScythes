package com.forge.enigmatic_scythes.scythe.scythes;

import com.forge.enigmatic_scythes.entity.ModEntities;
import com.forge.enigmatic_scythes.entity.projectile.CobwebProjectile;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArachnidScythe extends SwordItem {
    public ArachnidScythe(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }
    
    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        tooltip.add(Component.translatable("item.enigmatic_scythes.arachnid_scythe.tooltip"));

        super.appendHoverText(p_41421_, p_41422_, tooltip, p_41424_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            player.getPersistentData().putBoolean("shootingWebs", true);
            player.getPersistentData().putInt("currentShot", 0);

            player.getCooldowns().addCooldown(this, 30);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity player, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, player, slotId, isSelected);

        if (!level.isClientSide) {
            long currentTick = level.getGameTime();

            if (currentTick % 2 == 0 && player.getPersistentData().getInt("currentShot") < 4 && player.getPersistentData().getBoolean("shootingWebs")) {
                player.getPersistentData().putInt("currentShot", player.getPersistentData().getInt("currentShot") + 1);

                level.playSound(null, player.blockPosition(), SoundEvents.SPIDER_AMBIENT, SoundSource.AMBIENT, 1.0f, 1.0f);
                level.playSound(null, player.blockPosition(), SoundEvents.SHULKER_SHOOT, SoundSource.AMBIENT, 1.0f, 1.0f);

                CobwebProjectile projectile = new CobwebProjectile(ModEntities.COBWEB_PROJECTILE.get(), player.getEyePosition().x, player.getEyeY(), player.getEyePosition().z, level);
                projectile.setPos(player.getEyePosition().x, player.getEyeY(), player.getEyePosition().z);
                projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
                projectile.setOwner(player);

                level.addFreshEntity(projectile);

                projectile.setSecondsOnFire(10);
            }

            if (player.getPersistentData().getInt("currentShot") == 3) {
                player.getPersistentData().remove("currentShot");
                player.getPersistentData().remove("shootingWebs");
            }
        }
    }
}