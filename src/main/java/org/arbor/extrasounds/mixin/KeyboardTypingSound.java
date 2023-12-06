package org.arbor.extrasounds.mixin;

import org.arbor.extrasounds.SoundManager;
import org.arbor.extrasounds.sounds.SoundType;
import org.arbor.extrasounds.sounds.Sounds;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EditBox.class)
public class KeyboardTypingSound
{
    @Inject(at = @At("RETURN"), method = "charTyped")
    public void type(char chr, int modifiers, CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
            SoundManager.playSound(Sounds.KEYBOARD_TYPE, SoundType.TYPING);
    }
}
