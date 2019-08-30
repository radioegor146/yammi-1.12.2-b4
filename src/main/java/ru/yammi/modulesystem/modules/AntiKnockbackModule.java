package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;

public class AntiKnockbackModule
        extends Module {

    public AntiKnockbackModule() {
        super("AntiKnockback", ModuleCategory.COMBAT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.player.hurtResistantTime > 0 && this.mc.player.hurtTime > 0) {
            this.mc.player.hurtResistantTime = 0;
            this.mc.player.hurtTime = 0;
            this.mc.player.motionX = 0.0;
            this.mc.player.motionY = 0.0;
            this.mc.player.motionZ = 0.0;
        }
    }
}
