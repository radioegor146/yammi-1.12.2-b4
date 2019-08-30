package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class NoFallModule
        extends Module {

    public NoFallModule() {
        super("NoFall", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.player.fallDistance > 2.0f) {
            this.mc.player.connection.sendPacket(new CPacketPlayer(true));
        }
    }
}
