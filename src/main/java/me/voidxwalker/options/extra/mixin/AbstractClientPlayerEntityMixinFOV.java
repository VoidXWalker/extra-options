package me.voidxwalker.options.extra.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixinFOV {
    @Inject(method = "getSpeed",at = @At(value = "RETURN"),cancellable = true)
    public void extra_options_getSpeed(CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(MathHelper.lerp(0, 1.0F, cir.getReturnValue()));
    }
}
