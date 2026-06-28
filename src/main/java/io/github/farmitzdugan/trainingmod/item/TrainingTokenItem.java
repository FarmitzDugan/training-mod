package io.github.farmitzdugan.trainingmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class TrainingTokenItem extends Item {
    private static final int TRAINING_DURATION_TICKS = 10 * 20;
    private static final int TRAINING_COOLDOWN_TICKS = 20 * 20;

    public TrainingTokenItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(stack)) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {
            player.addEffect(
                    new MobEffectInstance(
                            MobEffects.SPEED,
                            TRAINING_DURATION_TICKS,
                            0
                    )
            );

            player.getCooldowns().addCooldown(stack, TRAINING_COOLDOWN_TICKS);

            player.sendSystemMessage(
                    Component.translatable("message.training-mod.training_started")
            );
        }

        return InteractionResult.SUCCESS;
    }
}