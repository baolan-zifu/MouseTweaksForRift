package yalter.mousetweaks.rift;

import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;

public interface IMixinGuiContainer {
    void setIgnoreMouseUp(boolean ignoreMouseUp);

    boolean getDragSplitting();

    void setDragSplitting(boolean dragSplitting);

    int getDragSplittingButton();

    Slot getSlotAt(double x, double y);

    void mouseClick(Slot slot, int slotId, int mouseButton, ClickType type);
}
