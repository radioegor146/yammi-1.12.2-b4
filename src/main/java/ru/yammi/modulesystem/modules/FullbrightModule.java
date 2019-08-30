package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.eventsystem.EventTarget;

public class FullbrightModule
        extends Module {

    public FullbrightModule() {
        super("Fullbright", ModuleCategory.RENDER);
    }

    @EventTarget
    public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
        this.mc.gameSettings.gammaSetting = this.getState() ? 200.0f : 0.5f;
    }
}
