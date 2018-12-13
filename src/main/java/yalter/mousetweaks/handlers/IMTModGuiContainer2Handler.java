package yalter.mousetweaks.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ReportedException;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import yalter.mousetweaks.IGuiScreenHandler;
import yalter.mousetweaks.MouseButton;
import yalter.mousetweaks.api.IMTModGuiContainer2;
import yalter.mousetweaks.rift.IMixinGuiContainer;

import java.util.List;

public class IMTModGuiContainer2Handler implements IGuiScreenHandler {
	protected Minecraft mc;
	protected IMTModGuiContainer2 modGuiContainer;
	protected IMixinGuiContainer mixinGuiContainer;

	public IMTModGuiContainer2Handler(IMTModGuiContainer2 modGuiContainer) {
		this.mc = Minecraft.getInstance();
		this.modGuiContainer = modGuiContainer;
		try {
			this.mixinGuiContainer = (IMixinGuiContainer) modGuiContainer;
		} catch (ClassCastException e) {
			CrashReport crashreport = CrashReport.makeCrashReport(e, "Mod GuiContainer could not be cast to IMixinGuiContainer.");
			throw new ReportedException(crashreport);
		}
	}

	@Override
	public boolean isMouseTweaksDisabled() {
		return modGuiContainer.MT_isMouseTweaksDisabled();
	}

	@Override
	public boolean isWheelTweakDisabled() {
		return modGuiContainer.MT_isWheelTweakDisabled();
	}

	@Override
	public List<Slot> getSlots() {
		return modGuiContainer.MT_getContainer().inventorySlots;
	}

	@Override
	public Slot getSlotUnderMouse() {
		return modGuiContainer.MT_getSlotUnderMouse();
	}

	@Override
	public boolean disableRMBDraggingFunctionality() {
		return modGuiContainer.MT_disableRMBDraggingFunctionality();
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
		return modGuiContainer.MT_isCraftingOutput(slot);
	}

	@Override
	public boolean isIgnored(Slot slot) {
		return modGuiContainer.MT_isIgnored(slot);
	}
}
