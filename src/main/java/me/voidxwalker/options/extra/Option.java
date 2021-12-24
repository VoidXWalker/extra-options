package me.voidxwalker.options.extra;

import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.DoubleOption;

public abstract class Option extends net.minecraft.client.options.Option {

    public static final BooleanOption extra_options_MONOCHROME_LOGO;
    public static final DoubleOption extra_options_FOV_EFFECT_SCALE = new DoubleOption("FOV Effects", 0.0D, 1.0D, 0.0F, (gameOptions) -> Math.pow(((GameOptionsAccess)gameOptions).extra_options_getFOVEffectScale(), 2.0D), (gameOptions, fovEffectScale) -> ((GameOptionsAccess)gameOptions).extra_options_setFOVEffectScale( (float)Math.sqrt(fovEffectScale)), (gameOptions, option) -> option.getDisplayPrefix()+((int)(option.getRatio(option.get(gameOptions)) * 100.0D) + "%"));
    public static final DoubleOption extra_options_DISTORTION_EFFECT_SCALE = new DoubleOption("Distortion Effects", 0.0D, 1.0D, 0.0F, (gameOptions) -> (double)((GameOptionsAccess)gameOptions).extra_options_getDistortionEffectScale(), (gameOptions, double_) -> ((GameOptionsAccess)gameOptions).extra_options_setDistortionEffectScale(double_.floatValue()), (gameOptions, doubleOption) -> doubleOption.getDisplayPrefix()+((int)(doubleOption.getRatio(doubleOption.get(gameOptions)) * 100.0D) + "%"));

    public Option(String key) {
        super(key);
    }

    static {
        extra_options_MONOCHROME_LOGO = new BooleanOption("Monochrome Logo", (gameOptions) -> ((GameOptionsAccess)gameOptions).extra_options_getMonochromeLogo(), (gameOptions, boolean_) -> ((GameOptionsAccess)gameOptions).extra_options_setMonochromeLogo(boolean_));
    }
}
