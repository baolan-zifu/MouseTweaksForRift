package yalter.mousetweaks.rift.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yalter.mousetweaks.rift.IMixinMouseHelper;

@Mixin(MouseHelper.class)
public class MixinMouseHelper implements IMixinMouseHelper {
    private int dwheel = 0;

    @Override
    public int getAndResetDWheel() {
        int ret = dwheel;
        dwheel = 0;
        return ret;
    }
    @Inject(method = "scrollCallback", at = @At("RETURN"))
    private void onSetScrollCallback(long window, double xoffset, double yoffset, CallbackInfo ci) {
        if (window == Minecraft.getInstance().mainWindow.getHandle()) {
            dwheel = (int) (yoffset * 120.0 * Minecraft.getInstance().gameSettings.mouseWheelSensitivity);
        }
    }
}
