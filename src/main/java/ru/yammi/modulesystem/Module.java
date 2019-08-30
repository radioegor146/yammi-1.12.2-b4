package ru.yammi.modulesystem;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;

public class Module {

    private List booleanValues = new ArrayList();
    private ModuleCategory category;
    private int keybind = 0;
    protected Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private boolean state;
    private List values = new ArrayList();

    public Module(String string, ModuleCategory xzSCDzduhxVFqcw) {
        this.name = string;
        this.category = xzSCDzduhxVFqcw;
    }

    public boolean getBooleanValue(String string) {
        for (BooleanValue tbIURGtAXLuntKY2 : this.getBooleanValues()) {
            if (!tbIURGtAXLuntKY2.getName().equals(string)) {
                continue;
            }
            return tbIURGtAXLuntKY2.isState();
        }
        return false;
    }

    public List<BooleanValue> getBooleanValues() {
        return this.booleanValues;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public float getFloatValue(String string) {
        return this.getValue(string).getValue();
    }

    public int getKeybind() {
        return this.keybind;
    }

    public String getName() {
        return this.name;
    }

    public boolean getState() {
        return this.state;
    }

    public SliderValue getValue(String string) {
        for (SliderValue dBUsaySotRtyDdU : this.getValues()) {
            if (!dBUsaySotRtyDdU.getName().equals(string)) {
                continue;
            }
            return dBUsaySotRtyDdU;
        }
        return null;
    }

    public List<SliderValue> getValues() {
        return this.values;
    }

    public void onDisable() {
    }

    public void onEnable() {
    }

    public void setCategory(ModuleCategory xzSCDzduhxVFqcw) {
        this.category = xzSCDzduhxVFqcw;
    }

    public void setKeybind(int n) {
        this.keybind = n;
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setState(boolean bl) {
        this.state = bl;
    }
}
