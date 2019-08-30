package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class AutoSprintModule
        extends Module {

    public AutoSprintModule() {
        super("AutoSprint", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.gameSettings.keyBindForward.isKeyDown()) {
            this.mc.player.setSprinting(true);
        }
    }
}
