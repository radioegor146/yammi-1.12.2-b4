package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class StrafeModule
        extends Module {

    public StrafeModule() {
        super("Strafe", ModuleCategory.MOVEMENT);
        this.getValues().add(new SliderValue("Force", 0.0f, 1.0f, 1.0f));
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.player.hurtTime <= 0 && (this.mc.player.onGround || !this.mc.player.isInWater())) {
            float f = this.mc.player.rotationYaw;
            if (this.mc.player.moveForward < 0.0f) {
                f += 180.0f;
            }
            if (this.mc.player.moveStrafing > 0.0f) {
                f -= 90.0f * (this.mc.player.moveForward > 0.0f ? 0.68f : (this.mc.player.moveForward < 0.0f ? -0.5f : 1.0f));
            }
            if (this.mc.player.moveStrafing < 0.0f) {
                f += 90.0f * (this.mc.player.moveForward > 0.0f ? 0.68f : (this.mc.player.moveForward < 0.0f ? -0.5f : 1.0f));
            }
            double d = 0.221;
            if (this.mc.player.isSprinting()) {
                d *= 0.3190000119209289;
            }
            if (this.mc.player.isSneaking()) {
                d *= 0.3;
            }
            d = this.getFloatValue("Force");
            float f2 = (float) (((float) Math.cos((f + 90.0f) * 3.141592653589793 / 180.0)) * d);
            float f3 = (float) (((float) Math.sin((f + 90.0f) * 3.141592653589793 / 180.0)) * d);
            if (this.mc.gameSettings.keyBindForward.isKeyDown() || this.mc.gameSettings.keyBindLeft.isKeyDown() || this.mc.gameSettings.keyBindRight.isKeyDown() || this.mc.gameSettings.keyBindBack.isKeyDown()) {
                this.mc.player.motionX = f2;
                this.mc.player.motionZ = f3;
            }
        }
    }
}
