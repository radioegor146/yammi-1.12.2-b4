package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class StepModule
        extends Module {

    public StepModule() {
        super("Step", ModuleCategory.MOVEMENT);
        this.getValues().add(new SliderValue("Height", 0.0f, 50.0f, 0.1f));
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        this.mc.player.stepHeight = this.getState() ? this.getFloatValue("Height") : 0.6f;
    }
}
