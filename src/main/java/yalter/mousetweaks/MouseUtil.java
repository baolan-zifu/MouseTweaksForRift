package yalter.mousetweaks;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class MouseUtil {
    public static boolean isMouseButtonDown(int button) {
        return GLFW.glfwGetMouseButton(Minecraft.getInstance().mainWindow.getHandle(), button) == 1;
    }
}
