package yalter.mousetweaks.rift;

import org.dimdev.rift.listener.client.ClientTickable;
import yalter.mousetweaks.Constants;
import yalter.mousetweaks.Main;
import yalter.mousetweaks.OnTickMethod;

public class MouseTweaksRift implements ClientTickable {
    private boolean initialized = false;

    private void init() {
        if (!initialized) {
            Main.initialize(Constants.EntryPoint.RIFT);
            initialized = true;
        }
    }

    @Override
    public void clientTick() {
        init();
        if (Main.onTickMethod == OnTickMethod.RIFT)
            Main.onUpdateInGame();
    }
}
