package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import ru.yammi.config.SliderValue;

public class NukerModule
        extends Module {

    private float ticks = 0.0f;

    public NukerModule() {
        super("Nuker", ModuleCategory.MISC);
        this.getValues().add(new SliderValue("Radius", 0.0f, 5.0f, 0.1f));
        this.getValues().add(new SliderValue("Delay", 0.0f, 20.0f, 1.0f));
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState()) {
            this.ticks += 1.0f;
            if (this.ticks >= this.getFloatValue("Delay")) {
                this.ticks = 0.0f;
                float radius = this.getFloatValue("Radius");
                new Thread() {
                    @Override
                    public void run() {
                        for (float f = radius; f >= -radius; f -= 1.0f) {
                            for (float f2 = radius; f2 >= -radius; f2 -= 1.0f) {
                                for (float f3 = -radius; f3 <= radius; f3 += 1.0f) {
                                    int n = (int) (Minecraft.getMinecraft().player.posX + f);
                                    int n2 = (int) (Minecraft.getMinecraft().player.posY + f3);
                                    int n3 = (int) (Minecraft.getMinecraft().player.posZ + f2);
                                    IBlockState iBlockState = Minecraft.getMinecraft().world.getBlockState(new BlockPos(n, n2, n3));
                                    if (iBlockState.getMaterial() == Material.AIR) {
                                        continue;
                                    }
                                    Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(n, n2, n3), EnumFacing.DOWN));
                                    Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(n, n2, n3), EnumFacing.DOWN));
                                }
                            }
                        }
                        this.stop();
                    }
                }.start();
            }
        }
    }
}
