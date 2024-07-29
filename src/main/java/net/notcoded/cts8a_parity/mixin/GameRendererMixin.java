package net.notcoded.cts8a_parity.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.notcoded.cts8a_parity.extensions.BlockHitResultExtensions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "pick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;hitResult:Lnet/minecraft/world/phys/HitResult;", shift = At.Shift.AFTER, ordinal = 2), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void bedrockBridging(float f, CallbackInfo ci, Entity entity, float g, Vec3 vec3, Vec3 vec32, Vec3 vec33, float h, AABB aABB, EntityHitResult entityHitResult, boolean bl, double d, HitResult hitResult) {
        // START NEW CODE (8a)
        if (entity.isOnGround() && vec3.y < -0.7) {
            Vec3 dcm15 = entity.getPosition(f).add(0.0, -0.1, 0.0);
            Vec3 dcm16 = dcm15.add(vec3.x, 0.0, vec3.z);
            hitResult = entity.level.clip(new ClipContext(dcm16, dcm15, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
            ((BlockHitResultExtensions)hitResult).setIsLedgeEdge();
        }
        // END NEW CODE (8a)
        this.minecraft.hitResult = hitResult;
    }
}
