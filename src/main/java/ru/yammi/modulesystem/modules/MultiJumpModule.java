package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class MultiJumpModule
        extends Module {

    public MultiJumpModule() {
        super("MultiJump", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.gameSettings.keyBindJump.isKeyDown()) {
            this.mc.player.onGround = true;
            this.mc.player.setJumping(false);
        }
    }
}
