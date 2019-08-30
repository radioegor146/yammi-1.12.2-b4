package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class FastLadderModule
        extends Module {

    public FastLadderModule() {
        super("FastLadder", ModuleCategory.MISC);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.player.isOnLadder()) {
            this.mc.player.motionY = 0.338;
        }
    }
}
