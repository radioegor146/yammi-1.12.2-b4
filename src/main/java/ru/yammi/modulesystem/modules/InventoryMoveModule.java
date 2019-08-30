package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InventoryMoveModule
        extends Module {

    public InventoryMoveModule() {
        super("InventoryMove", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.currentScreen != null && !(this.mc.currentScreen instanceof GuiChat)) {
            KeyBinding[] arrkeyBinding;
            for (KeyBinding keyBinding : arrkeyBinding = new KeyBinding[]{this.mc.gameSettings.keyBindForward, this.mc.gameSettings.keyBindBack, this.mc.gameSettings.keyBindLeft, this.mc.gameSettings.keyBindRight, this.mc.gameSettings.keyBindJump}) {
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), Keyboard.isKeyDown(keyBinding.getKeyCode()));
            }
        }
    }
}
