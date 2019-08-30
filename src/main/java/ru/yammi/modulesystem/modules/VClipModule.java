package ru.yammi.modulesystem.modules;

import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.helpers.MSTimer;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;

public class VClipModule
        extends Module {

    private MSTimer msTimer = new MSTimer(20L);

    public VClipModule() {
        super("VClip", ModuleCategory.MOVEMENT);
        this.getBooleanValues().add(new BooleanValue("Down"));
        this.getValues().add(new SliderValue("Motion Y", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Teleport Y", 0.0f, 5.0f, 0.1f));
    }

    private boolean isBelowBlock() {
        float f = 1.0f;
        for (float f2 = -f; f2 <= f; f2 += 1.0f) {
            int n = (int) Minecraft.getMinecraft().player.posX;
            int n2 = (int) (Minecraft.getMinecraft().player.posY + f2);
            int n3 = (int) Minecraft.getMinecraft().player.posZ;
            IBlockState iBlockState = Minecraft.getMinecraft().world.getBlockState(new BlockPos(n, n2, n3));
            if (iBlockState.getMaterial() == Material.AIR) {
                continue;
            }
            return true;
        }
        return false;
    }

    private boolean isOnBlock() {
        float f = 1.0f;
        for (float f2 = -f; f2 <= f; f2 += 1.0f) {
            int n = (int) Minecraft.getMinecraft().player.posX;
            int n2 = (int) (Minecraft.getMinecraft().player.posY - f2);
            int n3 = (int) Minecraft.getMinecraft().player.posZ;
            IBlockState iBlockState = Minecraft.getMinecraft().world.getBlockState(new BlockPos(n, n2, n3));
            if (iBlockState.getMaterial() == Material.AIR) {
                continue;
            }
            return true;
        }
        return false;
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.msTimer.checkMS()) {
            boolean bl = this.getBooleanValue("Down");
            float f = this.getFloatValue("Motion Y");
            double d = this.getFloatValue("Teleport Y");
            if (!bl) {
                float f2;
                if (this.isBelowBlock()) {
                    this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + d, this.mc.player.posZ);
                    this.mc.getConnection().sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + d, this.mc.player.posZ, true));
                }
                this.mc.player.capabilities.isFlying = false;
                this.mc.player.motionX = 0.0;
                this.mc.player.motionY = 0.0;
                this.mc.player.motionZ = 0.0;
                this.mc.player.jumpMovementFactor = f2 = f;
                this.mc.player.motionY += f2;
            } else {
                if (this.isOnBlock()) {
                    this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY - d, this.mc.player.posZ);
                    this.mc.getConnection().sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY - d, this.mc.player.posZ, true));
                }
                this.mc.player.capabilities.isFlying = false;
                this.mc.player.motionX = 0.0;
                this.mc.player.motionY = 0.0;
                this.mc.player.motionZ = 0.0;
                float f3 = f;
                this.mc.player.jumpMovementFactor = -f;
                this.mc.player.motionY -= f3;
            }
        }
    }
}
