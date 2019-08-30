package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.eventsystem.EventTarget;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import ru.yammi.config.BooleanValue;
import ru.yammi.helpers.ReflectionHelper;

public class WallHackModule
        extends Module {

    public WallHackModule() {
        super("WallHack", ModuleCategory.RENDER);
        this.getBooleanValues().add(new BooleanValue("Mobs"));
    }

    @EventTarget
    public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
        if (this.getState()) {
            RenderHelper.enableStandardItemLighting();
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            boolean bl = this.getBooleanValue("Mobs");
            this.mc.world.loadedEntityList.stream().forEach(entity -> {
                try {
                    if (entity != null && entity != this.mc.player) {
                        double d;
                        double d2;
                        float f;
                        double d3;
                        if (entity instanceof EntityPlayer) {
                            d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * ReflectionHelper.getPartialTicks();
                            d = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * ReflectionHelper.getPartialTicks();
                            d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * ReflectionHelper.getPartialTicks();
                            f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * ReflectionHelper.getPartialTicks();
                            this.mc.entityRenderer.disableLightmap();
                            this.mc.getRenderManager().renderEntity(entity, d3 - ReflectionHelper.getRenderPosX(), d - ReflectionHelper.getRenderPosY(), d2 - ReflectionHelper.getRenderPosZ(), f, ReflectionHelper.getPartialTicks(), false);
                            this.mc.entityRenderer.enableLightmap();
                        }
                        if (bl && entity instanceof EntityLivingBase) {
                            d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * ReflectionHelper.getPartialTicks();
                            d = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * ReflectionHelper.getPartialTicks();
                            d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * ReflectionHelper.getPartialTicks();
                            f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * ReflectionHelper.getPartialTicks();
                            this.mc.entityRenderer.disableLightmap();
                            this.mc.getRenderManager().renderEntity(entity, d3 - ReflectionHelper.getRenderPosX(), d - ReflectionHelper.getRenderPosY(), d2 - ReflectionHelper.getRenderPosZ(), f, ReflectionHelper.getPartialTicks(), false);
                            this.mc.entityRenderer.enableLightmap();
                        }
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            RenderHelper.disableStandardItemLighting();
        }
    }
}
