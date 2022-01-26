package me.voidxwalker.options.extra;

public interface GameOptionsAccess {
    boolean extra_options_getMonochromeLogo();
    float extra_options_getDistortionEffectScale();
    float extra_options_getFOVEffectScale();
    void extra_options_setDistortionEffectScale(float extra_options_distortionEffectScale);
    void extra_options_setFOVEffectScale(float extra_options_fovEffectScale);
    void extra_options_setMonochromeLogo(boolean extra_options_monochromeLogo);
}
