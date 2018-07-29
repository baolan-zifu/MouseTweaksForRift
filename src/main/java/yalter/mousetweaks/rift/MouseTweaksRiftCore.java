package yalter.mousetweaks.rift;

import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.mixin.Mixins;

public class MouseTweaksRiftCore implements InitializationListener {
    @Override
    public void onInitialization() {
        Mixins.addConfiguration("mixins.mousetweaks.json");
    }
}
