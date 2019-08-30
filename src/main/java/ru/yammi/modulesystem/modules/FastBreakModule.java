package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import ru.yammi.helpers.ReflectionHelper;

public class FastBreakModule
        extends Module {

    public FastBreakModule() {
        super("FastBreak", ModuleCategory.MISC);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState() && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            try {
                BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
                float f = ReflectionHelper.getField(PlayerControllerMP.class, "curBlockDamageMP", "field_78770_f", "e").getFloat(this.mc.playerController);
                float f2 = this.mc.world.getBlockState(blockPos).getPlayerRelativeBlockHardness(this.mc.player, this.mc.world, blockPos);
                float f3 = f + f2;
                if (f3 >= 1.0f) {
                    return;
                }
                ReflectionHelper.getField(PlayerControllerMP.class, "blockHitDelay", "field_78781_i", "g").setInt(this.mc.playerController, 0);
                ReflectionHelper.getField(PlayerControllerMP.class, "curBlockDamageMP", "field_78770_f", "e").setFloat(this.mc.playerController, f3);
                this.mc.getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, this.mc.objectMouseOver.sideHit));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
