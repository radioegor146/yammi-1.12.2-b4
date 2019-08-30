package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;

public class TriggerBotModule
        extends Module {

    public TriggerBotModule() {
        super("TriggerBot", ModuleCategory.COMBAT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        Entity entity;
        if (this.getState() && this.mc.objectMouseOver.typeOfHit != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && (entity = this.mc.objectMouseOver.entityHit) instanceof EntityPlayer && entity != this.mc.player) {
            EntityPlayer entityPlayer = (EntityPlayer) entity;
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.player.connection.sendPacket(new CPacketUseEntity(entityPlayer));
        }
    }
}
