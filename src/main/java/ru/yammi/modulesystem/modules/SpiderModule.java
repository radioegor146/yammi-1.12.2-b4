package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class SpiderModule
        extends Module {

    public SpiderModule() {
        super("Spider", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.player.collidedHorizontally) {
            this.mc.player.motionY = 0.14;
        }
    }
}
