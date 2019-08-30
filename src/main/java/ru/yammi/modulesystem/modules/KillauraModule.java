package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;

public class KillauraModule
        extends Module {

    private float ticks = 0.0f;

    public KillauraModule() {
        super("Killaura", ModuleCategory.COMBAT);
        this.getValues().add(new SliderValue("Radius", 0.0f, 6.5f, 0.1f));
        this.getValues().add(new SliderValue("Delay", 0.0f, 20.0f, 1.0f));
        this.getBooleanValues().add(new BooleanValue("Mobs"));
    }

    private List<Entity> collectEntities() {
        ArrayList arrayList = new ArrayList();
        this.mc.world.loadedEntityList.forEach(entity -> {
            float f = this.getFloatValue("Radius");
            if (entity != null && entity != this.mc.player) {
                boolean bl;
                if (entity instanceof EntityPlayer && this.mc.player.getDistance(entity) <= f) {
                    arrayList.add(entity);
                }
                if ((bl = this.getBooleanValue("Mobs")) && entity instanceof EntityLivingBase && this.mc.player.getDistance(entity) <= f) {
                    arrayList.add(entity);
                }
            }
        });
        return arrayList;
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState()) {
            this.ticks += 1.0f;
            float f = this.getFloatValue("Delay");
            if (this.ticks >= f) {
                this.collectEntities().stream().forEach(entity -> {
                    this.mc.player.swingArm(EnumHand.MAIN_HAND);
                    this.mc.getConnection().sendPacket(new CPacketUseEntity(entity));
                });
                this.ticks = 0.0f;
            }
        }
    }
}
