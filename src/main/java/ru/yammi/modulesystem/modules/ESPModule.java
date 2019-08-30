package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import ru.yammi.config.BooleanValue;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.config.SliderValue;

public class ESPModule
        extends Module {

    public ESPModule() {
        super("ESP", ModuleCategory.RENDER);
        this.getBooleanValues().add(new BooleanValue("Players"));
        this.getBooleanValues().add(new BooleanValue("Mobs"));
        this.getValues().add(new SliderValue("Red Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Green Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Blue Color", 0.0f, 1.0f, 0.1f));
        this.getValues().add(new SliderValue("Line Width", 0.0f, 5.0f, 0.1f));
    }

    private void doRenderEntity(Entity entity) {
        try {
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
            double d4 = entity.posX - ReflectionHelper.getRenderPosX() - 0.5;
            double d5 = entity.posY - ReflectionHelper.getRenderPosY();
            double d6 = entity.posZ - ReflectionHelper.getRenderPosZ() - 0.5;
            double d7 = 0.0;
            double d8 = 0.0;
            double d9 = 0.0;
            GL11.glTranslated(d4, d5, d6);
            GL11.glBegin(1);
            GL11.glVertex3d(d7, d8, d9);
            GL11.glVertex3d(d7, (d8 + 2.0), d9);
            GL11.glVertex3d(d7, (d8 + 2.0), d9);
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), d9);
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), d9);
            GL11.glVertex3d((d7 + 1.0), d8, d9);
            GL11.glVertex3d((d7 + 1.0), d8, d9);
            GL11.glVertex3d(d7, d8, d9);
            GL11.glVertex3d((d7 + 1.0), d8, d9);
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), d9);
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), d9);
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), d8, d9);
            GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d(d7, (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d(d7, (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d(d7, d8, (d9 + 1.0));
            GL11.glVertex3d(d7, d8, (d9 + 1.0));
            GL11.glVertex3d((d7 + 1.0), d8, (d9 + 1.0));
            GL11.glVertex3d(d7, d8, (d9 + 1.0));
            GL11.glVertex3d(d7, (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d(d7, (d8 + 2.0), (d9 + 1.0));
            GL11.glVertex3d(d7, (d8 + 2.0), d9);
            GL11.glVertex3d(d7, (d8 + 2.0), d9);
            GL11.glVertex3d(d7, d8, d9);
            GL11.glVertex3d(d7, d8, d9);
            GL11.glVertex3d(d7, d8, (d9 + 1.0));
            GL11.glEnd();
            GL11.glTranslated((-d4), (-d5), (-d6));
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glEnable(2896);
            GL11.glDepthMask(true);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @EventTarget
    public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
        if (this.getState()) {
            this.mc.world.loadedEntityList.stream().forEach(entity -> {
                if (entity != null && entity != this.mc.player) {
                    boolean bl = this.getBooleanValue("Players");
                    boolean bl2 = this.getBooleanValue("Mobs");
                    if (bl && entity instanceof EntityPlayer) {
                        this.doRenderEntity(entity);
                    }
                    if (bl2 && entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
                        this.doRenderEntity(entity);
                    }
                }
            });
        }
    }
}
