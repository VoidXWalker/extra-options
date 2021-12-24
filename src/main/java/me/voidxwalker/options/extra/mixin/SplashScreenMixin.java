package me.voidxwalker.options.extra.mixin;

import me.voidxwalker.options.extra.BackgroundHelper;
import me.voidxwalker.options.extra.GameOptionsAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.SplashScreen;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.IntSupplier;

@Mixin(SplashScreen.class)
public abstract class SplashScreenMixin extends Overlay {
    @Shadow @Final private MinecraftClient client;
    @Unique private static final int extra_options_MOJANG_RED = BackgroundHelper.ColorMixer.getArgb(255, 239, 50, 61);
    @Unique private static final int extra_options_MONOCHROME_BLACK = BackgroundHelper.ColorMixer.getArgb(255, 0, 0, 0);
    @Unique private static final IntSupplier extra_options_BRAND_ARGB = () -> ((GameOptionsAccess)MinecraftClient.getInstance().options).extra_options_getMonochromeLogo()? extra_options_MONOCHROME_BLACK : extra_options_MOJANG_RED;
    @Inject(method = "render",at=@At(value="INVOKE",target = "Lnet/minecraft/client/util/Window;getScaledWidth()I" ,shift = At.Shift.AFTER,ordinal = 1))
    public void fill(int mouseX, int mouseY, float delta, CallbackInfo ci){
        int i = this.client.getWindow().getScaledWidth();
        int j = this.client.getWindow().getScaledHeight();
        fill(0, 0, i, j, extra_options_BRAND_ARGB.getAsInt());
    }

}
