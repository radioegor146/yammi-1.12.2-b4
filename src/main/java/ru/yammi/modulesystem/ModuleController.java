package ru.yammi.modulesystem;

import ru.yammi.eventsystem.events.Render2DEvent;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import ru.yammi.helpers.ConfigHelper;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.modules.XRayBlockModelRenderer;

public class ModuleController {

    private long keyTime = 0L;
    private Minecraft mc = Minecraft.getMinecraft();

    public int drawBlock(int n, int n3, int n4) {
        Item item = Item.getItemById(n);
        if (item != null) {
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(item), n3, n4);
        }
        if (this.keyTime <= System.currentTimeMillis() && Mouse.isButtonDown(0)) {
            int n5 = Mouse.getX() / 2;
            int n6 = this.mc.currentScreen.height - Mouse.getY() / 2;
            if (n5 >= n3 && n5 <= n3 + 16 && n6 >= n4 - 2 && n6 <= n4 + 16) {
                if (Mouse.isButtonDown(0)) {
                    XRayBlockModelRenderer.xrayBlocks.removeIf(n2 -> n2 == n);
                    ConfigHelper.store();
                }
                this.keyTime = System.currentTimeMillis() + 250L;
            }
        }
        return 16;
    }

    private boolean isKeyDown(int n) {
        if (Keyboard.isKeyDown(n) && this.keyTime <= System.currentTimeMillis()) {
            this.keyTime = System.currentTimeMillis() + 250L;
            return true;
        }
        return false;
    }

    @EventTarget
    public void onRender2D(Render2DEvent aFVmeSjveCmGfse) {
        if (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiChat) {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            int n = scaledResolution.getScaledWidth() - 16;
            int n2 = scaledResolution.getScaledHeight() - 32;
            int[] arrn = new int[XRayBlockModelRenderer.xrayBlocks.size()];
            for (int i = 0; i < arrn.length; ++i) {
                arrn[i] = XRayBlockModelRenderer.xrayBlocks.get(i);
            }
            for (int n3 : arrn) {
                n -= this.drawBlock(n3, n, n2);
                if ((--n) >= scaledResolution.getScaledWidth() * 0.6) {
                    continue;
                }
                n2 -= 17;
                n = scaledResolution.getScaledWidth() - 6;
            }
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        Field field;
        try {
            field = ReflectionHelper.getField(RenderGlobal.class, "damagedBlocks", "field_72738_E", "x");
            Map map = (Map) field.get(this.mc.renderGlobal);
            if (map.isEmpty()) {
                map.put(0, new DestroyBlockProgress(this.mc.player.getEntityId(), new BlockPos(0, 0, 0)));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (Minecraft.getMinecraft().currentScreen == null) {
            ModuleManager.getModules().stream().forEach(asaIBIYOhmSolWc -> {
                if (asaIBIYOhmSolWc.getKeybind() != 0 && this.isKeyDown(asaIBIYOhmSolWc.getKeybind())) {
                    asaIBIYOhmSolWc.setState(!asaIBIYOhmSolWc.getState());
                    boolean bl = asaIBIYOhmSolWc.getState();
                    if (bl) {
                        asaIBIYOhmSolWc.onEnable();
                    } else {
                        asaIBIYOhmSolWc.onDisable();
                    }
                }
            });
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && this.isKeyDown(58)) {
                int n = Block.getStateId(this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()));
                if (!XRayBlockModelRenderer.xrayBlocks.contains(n)) {
                    XRayBlockModelRenderer.xrayBlocks.add(n);
                } else {
                    XRayBlockModelRenderer.xrayBlocks.removeIf(n2 -> n2 == n);
                }
                ConfigHelper.store();
            }
        }
    }
}
