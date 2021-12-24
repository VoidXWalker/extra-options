package me.voidxwalker.options.extra.mixin;

import me.voidxwalker.options.extra.Option;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.AccessibilityScreen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AccessibilityScreen.class)
public class AccessibilityOptionsScreenMixin extends GameOptionsScreen {
    @Shadow @Mutable @Final private static net.minecraft.client.options.Option[] OPTIONS;

    static{
        OPTIONS =  new net.minecraft.client.options.Option[]{net.minecraft.client.options.Option.NARRATOR, net.minecraft.client.options.Option.SUBTITLES, net.minecraft.client.options.Option.TEXT_BACKGROUND_OPACITY, net.minecraft.client.options.Option.TEXT_BACKGROUND, net.minecraft.client.options.Option.CHAT_OPACITY, net.minecraft.client.options.Option.AUTO_JUMP, net.minecraft.client.options.Option.SNEAK_TOGGLED, net.minecraft.client.options.Option.SPRINT_TOGGLED,Option.extra_options_DISTORTION_EFFECT_SCALE, Option.extra_options_FOV_EFFECT_SCALE, Option.extra_options_MONOCHROME_LOGO};
    }

    public AccessibilityOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Redirect(method = "init",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/options/AccessibilityScreen;addButton(Lnet/minecraft/client/gui/widget/AbstractButtonWidget;)Lnet/minecraft/client/gui/widget/AbstractButtonWidget;"))
    public AbstractButtonWidget extra_options_addButton(AccessibilityScreen instance, AbstractButtonWidget abstractButtonWidget){
        if(abstractButtonWidget.x==this.width / 2 - 100&&abstractButtonWidget.y==this.height / 6 + 144) {
            return this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (14) / 2, 200, 20, I18n.translate("gui.done", new Object[0]), (buttonWidget) -> {
                this.minecraft.openScreen(this.parent);
            }));
        }
        return this.addButton(abstractButtonWidget);
    }

}
