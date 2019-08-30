package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class XRayModule
        extends Module {

    public XRayModule() {
        super("XRay", ModuleCategory.RENDER);
    }

    @Override
    public void onDisable() {
        this.mc.renderGlobal.loadRenderers();
    }

    @Override
    public void onEnable() {
        this.mc.renderGlobal.loadRenderers();
    }
}
