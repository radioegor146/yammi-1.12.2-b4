package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class GhostModule
        extends Module {

    public GhostModule() {
        super("Ghost", ModuleCategory.MISC);
    }

    @Override
    public void onDisable() {
        this.mc.player.noClip = false;
        this.mc.getConnection().sendPacket(new CPacketPlayer.PositionRotation(this.mc.player.posX, this.mc.player.getEntityBoundingBox().minY, this.mc.player.posZ, this.mc.player.cameraYaw, this.mc.player.cameraPitch, this.mc.player.onGround));
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState()) {
            float f;
            this.mc.player.noClip = true;
            this.mc.player.fallDistance = 0.0f;
            this.mc.player.onGround = true;
            this.mc.player.capabilities.isFlying = false;
            this.mc.player.motionX = 0.0;
            this.mc.player.motionY = 0.0;
            this.mc.player.motionZ = 0.0;
            this.mc.player.jumpMovementFactor = f = 0.2f;
            if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
                this.mc.player.motionY += f;
            }
            if (this.mc.gameSettings.keyBindSneak.isKeyDown()) {
                this.mc.player.motionY -= f;
            }
        }
    }
}
