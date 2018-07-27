package yalter.mousetweaks.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ReportedException;
import net.minecraft.inventory.*;
import yalter.mousetweaks.IGuiScreenHandler;
import yalter.mousetweaks.MouseButton;
import yalter.mousetweaks.api.MouseTweaksDisableWheelTweak;
import yalter.mousetweaks.api.MouseTweaksIgnore;
import yalter.mousetweaks.rift.IMixinGuiContainer;

import java.util.List;

public class GuiContainerHandler implements IGuiScreenHandler {
	protected Minecraft mc;
	protected GuiContainer guiContainer;
	protected IMixinGuiContainer mixinGuiContainer;

	public GuiContainerHandler(GuiContainer guiContainer) {
		this.mc = Minecraft.getMinecraft();
		this.guiContainer = guiContainer;
		try {
			this.mixinGuiContainer = (IMixinGuiContainer) guiContainer;
		} catch (ClassCastException e) {
			CrashReport crashreport = CrashReport.makeCrashReport(e, "GuiContainer could not be cast to IMixinGuiContainer.");
			throw new ReportedException(crashreport);
		}
	}

	private int getDisplayWidth() {
		return mc.mainWindow.getWidth();
	}

	private int getDisplayHeight() {
		return mc.mainWindow.getHeight();
	}

	private double getRequiredMouseX() {
		return (mc.mouseHelper.func_198024_e() * mc.mainWindow.func_198107_o()) / mc.mainWindow.func_198105_m();
	}

	private double getRequiredMouseY() {
		return (mc.mouseHelper.func_198026_f() * mc.mainWindow.func_198087_p()) / mc.mainWindow.func_198083_n();
	}

	@Override
	public boolean isMouseTweaksDisabled() {
		return guiContainer.getClass().isAnnotationPresent(MouseTweaksIgnore.class)
			|| (mixinGuiContainer == null);
	}

	@Override
	public boolean isWheelTweakDisabled() {
		return guiContainer.getClass().isAnnotationPresent(MouseTweaksDisableWheelTweak.class);
	}

	@Override
	public List<Slot> getSlots() {
		return guiContainer.inventorySlots.inventorySlots;
	}

	@Override
	public Slot getSlotUnderMouse() {
		return mixinGuiContainer.getSlotAt(getRequiredMouseX(), getRequiredMouseY());
	}

	@Override
	public boolean disableRMBDraggingFunctionality() {
		mixinGuiContainer.setIgnoreMouseUp(true);

		if (mixinGuiContainer.getDragSplitting()) {
			if (mixinGuiContainer.getDragSplittingButton() == 1) {
				mixinGuiContainer.setDragSplitting(false);
				return true;
			}
		}

		return false;
	}

	@Override
	public void clickSlot(Slot slot, MouseButton mouseButton, boolean shiftPressed) {
		mixinGuiContainer.mouseClick(slot,
		                             slot.slotNumber,
		                             mouseButton.getValue(),
		                             shiftPressed ? ClickType.QUICK_MOVE : ClickType.PICKUP);
	}

	@Override
	public boolean isCraftingOutput(Slot slot) {
		return (slot instanceof SlotCrafting
			|| slot instanceof SlotFurnaceOutput
			|| slot instanceof SlotMerchantResult
			|| (guiContainer.inventorySlots instanceof ContainerRepair && slot.slotNumber == 2));
	}

	@Override
	public boolean isIgnored(Slot slot) {
		return false;
	}
}
