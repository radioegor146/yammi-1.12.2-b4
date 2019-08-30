package ru.yammi.config;

public class SliderValue {

    private boolean hovered = false;
    private float maxValue;
    private float minValue;
    private float modifyValue;
    private int mouseX = 0;
    private String name;
    private int sliderX = 0;
    private float value;

    public SliderValue(String string, float f, float f2, float f3) {
        this.setName(string);
        this.setMinValue(f);
        this.setMaxValue(f2);
        this.setModifyValue(f3);
        this.setValue(this.getMinValue());
    }

    public float getMaxValue() {
        return this.maxValue;
    }

    public float getMinValue() {
        return this.minValue;
    }

    public float getModifyValue() {
        return this.modifyValue;
    }

    public int getMouseX() {
        return this.mouseX;
    }

    public String getName() {
        return this.name;
    }

    public int getSliderX() {
        return this.sliderX;
    }

    public float getValue() {
        return this.value;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    public void setHovered(boolean bl) {
        this.hovered = bl;
    }

    public void setMaxValue(float f) {
        this.maxValue = f;
    }

    public void setMinValue(float f) {
        this.minValue = f;
    }

    public void setModifyValue(float f) {
        this.modifyValue = f;
    }

    public void setMouseX(int n) {
        this.mouseX = n;
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setSliderX(int n) {
        this.sliderX = n;
    }

    public void setValue(float f) {
        this.value = f;
    }
}
