package ru.yammi.helpers.particles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ParticleSystem {

    private static final float SPEED = 0.1f;
    private int dist = 100;
    private List<Particle> particleList = new ArrayList();

    public ParticleSystem(int n) {
        this.addParticles(n);
    }

    public void addParticles(int n) {
        for (int i = 0; i < n; ++i) {
            this.particleList.add(Particle.generateParticle());
        }
    }

    public double distance(float f, float f2, float f3, float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }

    private void drawLine(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        GL11.glColor4f(f5, f6, f7, f8);
        GL11.glLineWidth(0.5f);
        GL11.glBegin(1);
        GL11.glVertex2f(f, f2);
        GL11.glVertex2f(f3, f4);
        GL11.glEnd();
    }

    public void render() {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        for (Particle jEMXLMISzubihMw2 : this.particleList) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, (jEMXLMISzubihMw2.getAlpha() / 255.0f));
            GL11.glPointSize(jEMXLMISzubihMw2.getSize());
            GL11.glBegin(0);
            GL11.glVertex2f(jEMXLMISzubihMw2.getX(), jEMXLMISzubihMw2.getY());
            GL11.glEnd();
            int n = Mouse.getEventX() * Minecraft.getMinecraft().currentScreen.width / Minecraft.getMinecraft().displayWidth;
            int n2 = Minecraft.getMinecraft().currentScreen.height - Mouse.getEventY() * Minecraft.getMinecraft().currentScreen.height / Minecraft.getMinecraft().displayHeight - 1;
            float f = 0.0f;
            Particle jEMXLMISzubihMw3 = null;
            for (Particle jEMXLMISzubihMw4 : this.particleList) {
                float f2 = jEMXLMISzubihMw2.getDistanceTo(jEMXLMISzubihMw4);
                if (f2 > this.dist || this.distance(n, n2, jEMXLMISzubihMw2.getX(), jEMXLMISzubihMw2.getY()) > this.dist && this.distance(n, n2, jEMXLMISzubihMw4.getX(), jEMXLMISzubihMw4.getY()) > this.dist || f > 0.0f && f2 > f) {
                    continue;
                }
                f = f2;
                jEMXLMISzubihMw3 = jEMXLMISzubihMw4;
            }
            if (jEMXLMISzubihMw3 == null) {
                continue;
            }
            float f3 = Math.min(1.0f, Math.min(1.0f, 1.0f - f / this.dist));
            this.drawLine(jEMXLMISzubihMw2.getX(), jEMXLMISzubihMw2.getY(), jEMXLMISzubihMw3.getX(), jEMXLMISzubihMw3.getY(), 1.0f, 1.0f, 1.0f, f3);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public void tick(int n) {
        if (Mouse.isButtonDown(0)) {
            this.addParticles(1);
        }
        this.particleList.forEach((jEMXLMISzubihMw2) -> {
            jEMXLMISzubihMw2.tick(n, 0.1f);
        });
    }
}
