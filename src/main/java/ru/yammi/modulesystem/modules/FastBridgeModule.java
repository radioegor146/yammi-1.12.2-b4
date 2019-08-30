package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class FastBridgeModule
        extends Module {

    public FastBridgeModule() {
        super("FastBridge", ModuleCategory.MISC);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState()) {
            boolean bl;
            BlockPos blockPos = new BlockPos(this.mc.player).add(0, -1, 0);
            boolean bl2 = bl = this.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
            if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
                if (!bl) {
                    this.setjump(false);
                }
                this.setsneak(this.mc.player.onGround || bl);
                if (!this.mc.player.onGround) {
                    this.mc.player.motionX = 0.0;
                    this.mc.player.motionZ = 0.0;
                }
            } else {
                this.setsneak(bl);
            }
        }
    }

    public void setjump(boolean bl) {
        this.mc.player.movementInput.jump = bl;
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindJump.getKeyCode(), bl);
    }

    public void setsneak(boolean bl) {
        this.mc.player.movementInput.sneak = bl;
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), bl);
    }
}
