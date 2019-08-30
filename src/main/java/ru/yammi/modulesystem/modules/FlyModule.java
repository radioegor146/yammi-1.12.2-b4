package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class FlyModule
        extends Module {

    public FlyModule() {
        super("Fly", ModuleCategory.MOVEMENT);
        this.getValues().add(new SliderValue("Speed", 0.0f, 10.0f, 0.1f));
    }

    @Override
    public void onDisable() {
        this.mc.player.capabilities.allowFlying = false;
        this.mc.player.capabilities.isFlying = false;
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState()) {
            this.mc.player.capabilities.allowFlying = true;
            this.mc.player.capabilities.isFlying = true;
            float f = this.getFloatValue("Speed");
            float f2 = f / 5.0f * 0.9f;
            this.mc.player.capabilities.setFlySpeed(0.35f * f2);
            this.mc.player.motionY = this.mc.gameSettings.keyBindJump.isKeyDown() ? (this.mc.player.motionY += 0.04) : (this.mc.player.motionY -= 0.005);
            if (this.mc.gameSettings.keyBindSneak.isKeyDown()) {
                this.mc.player.motionY -= 0.06;
            }
        }
    }
}
