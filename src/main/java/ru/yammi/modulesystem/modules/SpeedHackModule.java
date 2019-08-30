package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class SpeedHackModule
        extends Module {

    public SpeedHackModule() {
        super("SpeedHack", ModuleCategory.MOVEMENT);
        this.getValues().add(new SliderValue("Speed", 0.0f, 100.0f, 1.0f));
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.player.onGround) {
            if (!(this.mc.player.isSneaking() || this.mc.player.moveForward == 0.0f && this.mc.player.moveStrafing == 0.0f)) {
                float f;
                double d;
                if (this.mc.player.moveForward > 0.0f && !this.mc.player.collidedHorizontally) {
                    this.mc.player.setSprinting(true);
                }
                this.mc.player.motionX *= 5.0;
                this.mc.player.motionZ *= 5.0;
                double d2 = Math.sqrt(Math.pow(this.mc.player.motionX, 2.0) + Math.pow(this.mc.player.motionZ, 2.0));
                if (d2 > (d = (f = this.getFloatValue("Speed")) / 5.0 * 0.8)) {
                    this.mc.player.motionX = this.mc.player.motionX / d2 * d;
                    this.mc.player.motionZ = this.mc.player.motionZ / d2 * d;
                }
            } else {
                this.mc.player.motionX = 0.0;
                this.mc.player.motionZ = 0.0;
            }
        }
    }
}
