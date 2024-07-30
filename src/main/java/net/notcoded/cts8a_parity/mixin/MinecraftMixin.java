package net.notcoded.cts8a_parity.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.notcoded.cts8a_parity.extensions.BlockHitResultExtensions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Nullable public HitResult hitResult;

    @Redirect(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z"))
    private boolean avoidContinueAttackingWhenBridging(BlockState instance) {
        return instance.isAir() || ((BlockHitResultExtensions)this.hitResult).isLedgeEdge();
    }

    @Redirect(method = "startAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z"))
    private boolean avoidStartAttackingWhenBridging(BlockState instance) {
        return instance.isAir() || ((BlockHitResultExtensions)this.hitResult).isLedgeEdge();
    }
}
