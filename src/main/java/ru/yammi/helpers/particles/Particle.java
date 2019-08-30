package ru.yammi.helpers.particles;

import java.util.Random;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

public class Particle {

    private float alpha;
    private Vector2f pos;
    private static final Random random = new Random();
    private float size;
    private Vector2f velocity;

    public Particle(Vector2f vector2f, float f, float f2, float f3) {
        this.velocity = vector2f;
        this.pos = new Vector2f(f, f2);
        this.size = f3;
    }

    public double distance(float f, float f2, float f3, float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }

    public static Particle generateParticle() {
        Vector2f vector2f = new Vector2f((float) (Math.random() * 2.0 - 1.0), (float) (Math.random() * 2.0 - 1.0));
        float f = random.nextInt(Display.getWidth());
        float f2 = random.nextInt(Display.getHeight());
        float f3 = (float) (Math.random() * 4.0) + 1.0f;
        return new Particle(vector2f, f, f2, f3);
    }

    public float getAlpha() {
        return this.alpha;
    }

    public float getDistanceTo(Particle jEMXLMISzubihMw2) {
        return this.getDistanceTo(jEMXLMISzubihMw2.getX(), jEMXLMISzubihMw2.getY());
    }

    public float getDistanceTo(float f, float f2) {
        return (float) this.distance(this.getX(), this.getY(), f, f2);
    }

    public float getSize() {
        return this.size;
    }

    public Vector2f getVelocity() {
        return this.velocity;
    }

    public float getX() {
        return this.pos.getX();
    }

    public float getY() {
        return this.pos.getY();
    }

    public void setSize(float f) {
        this.size = f;
    }

    public void setVelocity(Vector2f vector2f) {
        this.velocity = vector2f;
    }

    public void setX(float f) {
        this.pos.setX(f);
    }

    public void setY(float f) {
        this.pos.setY(f);
    }

    public void tick(int n, float f) {
        this.pos.x += this.velocity.getX() * n * f;
        this.pos.y += this.velocity.getY() * n * f;
        if (this.alpha < 255.0f) {
            this.alpha += 0.05f * n;
        }
        if (this.pos.getX() > Display.getWidth()) {
            this.pos.setX(0.0f);
        }
        if (this.pos.getX() < 0.0f) {
            this.pos.setX(Display.getWidth());
        }
        if (this.pos.getY() > Display.getHeight()) {
            this.pos.setY(0.0f);
        }
        if (this.pos.getY() < 0.0f) {
            this.pos.setY(Display.getHeight());
        }
    }
}
