package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.util.math.MathHelper;
import ru.yammi.config.SliderValue;

public class LongJumpModule
        extends Module {

    public LongJumpModule() {
        super("LongJump", ModuleCategory.MOVEMENT);
        this.getValues().add(new SliderValue("Force", 0.0f, 100.0f, 1.0f));
    }

    public float getDirection() {
        float f = this.mc.player.rotationYaw;
        float f2 = this.mc.player.moveForward;
        float f3 = this.mc.player.moveStrafing;
        f += (f2 < 0.0f ? 180 : 0);
        if (f3 < 0.0f) {
            f += (f2 < 0.0f ? -45 : (f2 == 0.0f ? 90 : 45));
        }
        if (f3 > 0.0f) {
            f -= (f2 < 0.0f ? -45 : (f2 == 0.0f ? 90 : 45));
        }
        return f * 0.017453292f;
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.gameSettings.keyBindJump.isKeyDown()) {
            float f = this.getFloatValue("Force");
            f = 100.0f - f + 1.0f;
            this.mc.player.setSprinting(true);
            double d = Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ + 10.0 / (10.0 * f));
            this.mc.player.motionX = (-MathHelper.sin(this.getDirection())) * d;
            this.mc.player.motionZ = MathHelper.cos(this.getDirection()) * d;
            if (this.mc.player.onGround) {
                this.mc.player.jump();
                this.mc.player.motionY *= 0.94356256;
            } else if (this.mc.player.isAirBorne && !this.mc.player.onGround) {
                double d2 = Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ + 10.0 / (10.0 * f)) + (f / (f * 1000.0f));
                this.mc.player.motionX = (-MathHelper.sin(this.getDirection())) * d2;
                this.mc.player.motionZ = MathHelper.cos(this.getDirection()) * d2;
            }
        }
    }
}
