package me.voidxwalker.options.extra.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixinFOV {
    @Inject(method = "getFov",at = @At(value = "RETURN"),cancellable = true)
    private void extra_options_changeFOV(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        FluidState fluidState = camera.getSubmergedFluidState();
        if (!fluidState.isEmpty()) {
            cir.setReturnValue((cir.getReturnValue()*7D/6D));
        }
    }
}
