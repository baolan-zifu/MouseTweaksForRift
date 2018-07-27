package yalter.mousetweaks.rift.mixin;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import yalter.mousetweaks.rift.IMixinGuiContainer;

@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer implements IMixinGuiContainer {
    @Shadow private boolean ignoreMouseUp;
    @Shadow protected boolean dragSplitting;
    @Shadow private int dragSplittingButton;

    @Override
    public void setIgnoreMouseUp(boolean ignoreMouseUp) {
        this.ignoreMouseUp = ignoreMouseUp;
    }

    @Override
    public boolean getDragSplitting() {
        return dragSplitting;
    }

    @Override
    public void setDragSplitting(boolean dragSplitting) {
        this.dragSplitting = dragSplitting;
    }

    @Override
    public int getDragSplittingButton() {
        return dragSplittingButton;
    }

    @Override
    public Slot getSlotAt(double x, double y) {
        return shadow$func_195360_a(x, y);
    }

    @Override
    public void mouseClick(Slot slot, int slotId, int mouseButton, ClickType type) {
        shadow$handleMouseClick(slot, slotId, mouseButton, type);
    }

    @Shadow
    private Slot shadow$func_195360_a(double x, double y) { return null; }

    @Shadow
    protected abstract void shadow$handleMouseClick(Slot slot, int slotId, int mouseButton, ClickType type);
}
