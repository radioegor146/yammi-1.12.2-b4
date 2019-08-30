package ru.yammi.config;

public class BooleanValue {

    private String name;
    private boolean state;

    public BooleanValue(String string) {
        this.name = string;
        this.setState(false);
    }

    public String getName() {
        return this.name;
    }

    public boolean isState() {
        return this.state;
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setState(boolean bl) {
        this.state = bl;
    }
}
