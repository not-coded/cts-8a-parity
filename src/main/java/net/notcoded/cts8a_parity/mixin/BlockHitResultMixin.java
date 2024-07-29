package net.notcoded.cts8a_parity.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.notcoded.cts8a_parity.extensions.BlockHitResultExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockHitResult.class)
public class BlockHitResultMixin implements BlockHitResultExtensions {
    @Unique
    private boolean isLedgeEdge;

    @Inject(method = "<init>(ZLnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Z)V", at = @At("TAIL"))
    private void injectLedgeEdge(boolean bl, Vec3 vec3, Direction direction, BlockPos blockPos, boolean bl2, CallbackInfo ci) {
        this.isLedgeEdge = false;
    }

    @Override
    public void setIsLedgeEdge() {
        this.isLedgeEdge = true;
    }

    @Override
    public boolean isLedgeEdge() {
        return this.isLedgeEdge;
    }
}
