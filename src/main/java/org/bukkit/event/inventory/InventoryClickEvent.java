package org.bukkit.event.inventory;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player clicks on an inventory slot in their inventory
 */
public class InventoryClickEvent extends InventoryEvent implements Cancellable {
    private Result result;
    private boolean rightClick;
    private boolean shiftClick;
    private int rawSlot;
    private ItemStack current;

    public InventoryClickEvent(InventoryView transaction, int slot, boolean right, boolean shift) {
        super(Type.INVENTORY_CLICK, transaction);
        this.rawSlot = slot;
        this.rightClick = right;
        this.shiftClick = shift;
        this.result = Result.DEFAULT;
    }

    public int getRawSlot() {
        return rawSlot;
    }

    /**
     * @return True if the click is a right-click.
     */
    public boolean isRightClick() {
        return rightClick;
    }

    /**
     * @return True if the click is a left-click.
     */
    public boolean isLeftClick() {
        return !rightClick;
    }

    /**
     * Shift can be combined with right-click or left-click as a modifier.
     *
     * @return True if the click is a shift-click
     */
    public boolean isShiftClick() {
        return shiftClick;
    }

    /**
     * Returns the current item in the clicked slot.
     *
     * @return The slot item
     */
    public ItemStack getCurrentItem() {
        return rawSlot == -999 ? current : getView().getItem(rawSlot);
    }

    /**
     * Set the current item in the slot.
     * @param item The new slot item.
     */
    public void setCurrentItem(ItemStack item) {
        if (rawSlot == -999) current = item;
        else getView().setItem(rawSlot, item);
    }

    public Result getResult() {
        return result;
    }

    public boolean isCancelled() {
        return result == Result.DENY;
    }

    public void setCancelled(boolean cancel) {
        result = cancel ? Result.DENY : Result.ALLOW;
    }
}
