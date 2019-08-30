package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import org.lwjgl.opengl.GL11;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.config.SliderValue;

public class ChestESPModule
        extends Module {

    public ChestESPModule() {
        super("ChestESP", ModuleCategory.RENDER);
        this.getValues().add(new SliderValue("Red Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Green Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Blue Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Line Width", 0.0f, 5.0f, 0.1f));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventTarget
    public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
        if (!this.getState()) {
            return;
        }
        {
            try {
                for (Object e : this.mc.world.loadedTileEntityList) {
                    if (e == null || !(e instanceof TileEntityChest) && !(e instanceof TileEntityEnderChest)) {
                        continue;
                    }
                    GL11.glBlendFunc(770, 771);
                    GL11.glEnable(3042);
                    GL11.glEnable(2848);
                    GL11.glDisable(2896);
                    float f = this.getFloatValue("Line Width");
                    if (f >= 0.1f) {
                        GL11.glLineWidth(f);
                    } else {
                        GL11.glLineWidth(1.0f);
                    }
                    GL11.glDisable(3553);
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                    float f2 = this.getFloatValue("Red Color");
                    float f3 = this.getFloatValue("Green Color");
                    float f4 = this.getFloatValue("Blue Color");
                    if (f2 >= 0.1f || f3 >= 0.1f || f4 >= 0.1f) {
                        GL11.glColor3f(f2, f3, f4);
                    } else {
                        GL11.glColor3f(1.0f, 0.0f, 0.0f);
                    }
                    double d = ReflectionHelper.getRenderPosX();
                    double d2 = ReflectionHelper.getRenderPosY();
                    double d3 = ReflectionHelper.getRenderPosZ();
                    TileEntity tileEntity = (TileEntity) e;
                    double d4 = tileEntity.getPos().getX() - d;
                    double d5 = tileEntity.getPos().getY() - d2;
                    double d6 = tileEntity.getPos().getZ() - d3;
                    double d7 = 0.0;
                    double d8 = 0.0;
                    double d9 = 0.0;
                    GL11.glTranslated(d4, d5, d6);
                    GL11.glBegin(1);
                    GL11.glVertex3d(d7, d8, d9);
                    GL11.glVertex3d(d7, (d8 + 1.0), d9);
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), d9);
                    GL11.glVertex3d((d7 + 1.0), d8, d9);
                    GL11.glVertex3d((d7 + 1.0), d8, d9);
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), d9);
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
                    GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d(d7, (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d(d7, d8, (d9 + 1.0));
                    GL11.glVertex3d(d7, d8, (d9 + 1.0));
                    GL11.glVertex3d(d7, (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d(d7, (d8 + 1.0), d9);
                    GL11.glVertex3d(d7, d8, d9);
                    GL11.glVertex3d(d7, d8, d9);
                    GL11.glVertex3d((d7 + 1.0), d8, d9);
                    GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
                    GL11.glVertex3d(d7, d8, (d9 + 1.0));
                    GL11.glVertex3d(d7, (d8 + 1.0), d9);
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), d9);
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d(d7, (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d(d7, d8, d9);
                    GL11.glVertex3d(d7, d8, (d9 + 1.0));
                    GL11.glVertex3d(d7, (d8 + 1.0), d9);
                    GL11.glVertex3d(d7, (d8 + 1.0), (d9 + 1.0));
                    GL11.glVertex3d((d7 + 1.0), d8, d9);
                    GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), d9);
                    GL11.glVertex3d((d7 + 1.0), (d8 + 1.0), (d9 + 1.0));
                    GL11.glEnd();
                    GL11.glTranslated((-d4), (-d5), (-d6));
                    GL11.glEnable(3553);
                    GL11.glEnable(2929);
                    GL11.glEnable(2896);
                    GL11.glDepthMask(true);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
