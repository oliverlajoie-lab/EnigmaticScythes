package com.forge.enigmatic_scythes.scythe.scythes;

import com.forge.enigmatic_scythes.scythe.ModScythes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "enigmatic_scythes", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ScytheOfCurse extends SwordItem {
    public ScytheOfCurse(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    public static List<MobEffect> BUFF_MOB_EFFECTS = new ArrayList<>();
    public static List<MobEffect> DEBUFF_MOB_EFFECTS = new ArrayList<>();

    static {
        DEBUFF_MOB_EFFECTS.add(MobEffects.WEAKNESS);
        DEBUFF_MOB_EFFECTS.add(MobEffects.MOVEMENT_SLOWDOWN);
        DEBUFF_MOB_EFFECTS.add(MobEffects.HARM);
        DEBUFF_MOB_EFFECTS.add(MobEffects.LEVITATION);
        DEBUFF_MOB_EFFECTS.add(MobEffects.POISON);
        DEBUFF_MOB_EFFECTS.add(MobEffects.WITHER);
        DEBUFF_MOB_EFFECTS.add(MobEffects.BLINDNESS);

        BUFF_MOB_EFFECTS.add(MobEffects.REGENERATION);
        BUFF_MOB_EFFECTS.add(MobEffects.FIRE_RESISTANCE);
        BUFF_MOB_EFFECTS.add(MobEffects.MOVEMENT_SPEED);
        BUFF_MOB_EFFECTS.add(MobEffects.DAMAGE_BOOST);
        BUFF_MOB_EFFECTS.add(MobEffects.HEALTH_BOOST);
        BUFF_MOB_EFFECTS.add(MobEffects.ABSORPTION);
        BUFF_MOB_EFFECTS.add(MobEffects.HEAL);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        tooltip.add(Component.translatable("item.enigmatic_scythes.scythe_of_curse.tooltip"));

        super.appendHoverText(p_41421_, p_41422_, tooltip, p_41424_);
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            int debuffNum = (int) (Math.random() * 3);

            if (debuffNum == 2) {
                MobEffect randomEffect = DEBUFF_MOB_EFFECTS.get((int) (Math.random() * DEBUFF_MOB_EFFECTS.size()));

                level.playSound(null, player.blockPosition(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                player.addEffect(new MobEffectInstance(randomEffect, 100, 3, true, true));
            } else {
                MobEffect randomEffect = BUFF_MOB_EFFECTS.get((int) (Math.random() * BUFF_MOB_EFFECTS.size()));

                level.playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                player.addEffect(new MobEffectInstance(randomEffect, 500, 3, true, true));
            }

            player.getCooldowns().addCooldown(this, 500);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @SubscribeEvent
    public static void onHitEnemy(LivingHurtEvent event) {
        Entity entity = event.getEntity();

        if (entity.isAlive() && entity instanceof LivingEntity livingEntity) {
            if (event.getSource().getEntity() instanceof Player player && player.getMainHandItem().is(ModScythes.SCYTHE_OF_CURSE.get()) && !player.getCooldowns().isOnCooldown(ModScythes.SCYTHE_OF_CURSE.get())) {
                int debuffNum = (int) (Math.random() * 3);

                if (debuffNum == 2) {
                    MobEffect randomEffect = DEBUFF_MOB_EFFECTS.get((int) (Math.random() * DEBUFF_MOB_EFFECTS.size()));

                    livingEntity.addEffect(new MobEffectInstance(randomEffect, 500, 3, true, true));

                    Level level = player.level();

                    level.playSound(null, entity.blockPosition(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                } else {
                    MobEffect randomEffect = BUFF_MOB_EFFECTS.get((int) (Math.random() * BUFF_MOB_EFFECTS.size()));

                    Level level = player.level();

                    level.playSound(null, entity.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                    livingEntity.addEffect(new MobEffectInstance(randomEffect, 250, 3, true, true));
                }

                player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 100);
            }
        }
    }
}