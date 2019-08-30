package ru.yammi.modulesystem.modules;

import ru.yammi.config.BooleanValue;
import ru.yammi.gui.ClickGUIScreen;
import ru.yammi.config.SliderValue;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class ClickGUIModule
        extends Module {

    private ClickGUIScreen clickGUIScreen;

    public ClickGUIModule() {
        super("ClickGUI", ModuleCategory.MISC);
        this.setKeybind(54);
        this.getValues().add(new SliderValue("Red Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Green Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Blue Color", 0.0f, 1.0f, 0.1f));
        this.getBooleanValues().add(new BooleanValue("Particles"));
    }

    public ClickGUIScreen getClickGUIScreen() {
        return this.clickGUIScreen;
    }

    @Override
    public void onDisable() {
        if (this.mc.currentScreen != null && this.mc.currentScreen instanceof ClickGUIScreen) {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public void onEnable() {
        this.mc.displayGuiScreen(this.getClickGUIScreen());
    }

    public void setClickGUIScreen(ClickGUIScreen nqCPORZikVPseOK) {
        this.clickGUIScreen = nqCPORZikVPseOK;
    }
}
