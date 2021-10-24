package me.voidxwalker.options.extra.mixin;

import me.voidxwalker.options.extra.GameOptionsAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.BackgroundHelper;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.SplashScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.IntSupplier;

@Mixin(SplashScreen.class)
public abstract class SplashScreenMixin extends Overlay {
    @Mutable @Shadow @Final private static int field_25042;
    @Mutable @Shadow @Final private static int field_25041;
    @Unique private static final int extra_options_MOJANG_RED = BackgroundHelper.ColorMixer.getArgb(255, 239, 50, 61);
    @Unique private static final int extra_options_MONOCHROME_BLACK = BackgroundHelper.ColorMixer.getArgb(255, 0, 0, 0);
    @Unique private static final IntSupplier extra_options_BRAND_ARGB = () -> ((GameOptionsAccess)MinecraftClient.getInstance().options).extra_options_getMonochromeLogo()? extra_options_MONOCHROME_BLACK : extra_options_MOJANG_RED;
    @Inject(
            method = {"render"},
            at = @At(value = "HEAD")
    )
    public void extra_options_render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci){
        field_25042=extra_options_BRAND_ARGB.getAsInt();
        field_25041=extra_options_BRAND_ARGB.getAsInt();
    }
}
