package ru.yammi.gui.elements;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.modulesystem.ModuleManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class CategoryGuiElement {

    private ModuleCategory category;
    private boolean dragging = false;
    private List<ModuleGuiElement> guiList = new ArrayList();
    private int lastX = 0;
    private int lastY = 0;
    private Minecraft mc = Minecraft.getMinecraft();
    private static int tabID = 0;
    private int x = 0;
    private int y = 0;

    public CategoryGuiElement(ModuleCategory xzSCDzduhxVFqcw) {
        this.category = xzSCDzduhxVFqcw;
        this.x = 100 * tabID;
        ++tabID;
        ModuleManager.getModules().stream().filter((asaIBIYOhmSolWc) -> !(asaIBIYOhmSolWc.getCategory() != this.category)).forEachOrdered((asaIBIYOhmSolWc) -> {
            this.guiList.add(new ModuleGuiElement(asaIBIYOhmSolWc));
        });
        this.guiList.sort((hFuvwQNzRIuSpIC2, hFuvwQNzRIuSpIC3) -> this.mc.fontRenderer.getStringWidth(hFuvwQNzRIuSpIC3.getModule().getName()) - this.mc.fontRenderer.getStringWidth(hFuvwQNzRIuSpIC2.getModule().getName()));
    }

    public void doRender() {
        int n = new Color(128, 47, 161).getRGB();
        Module asaIBIYOhmSolWc = ModuleManager.getModule("ClickGUI");
        float f = asaIBIYOhmSolWc.getFloatValue("Red Color");
        float f2 = asaIBIYOhmSolWc.getFloatValue("Green Color");
        float f3 = asaIBIYOhmSolWc.getFloatValue("Blue Color");
        float[] arrf = new float[]{f, f2, f3, 1.0f};
        if (f >= 0.1f || f2 >= 0.1f || f3 >= 0.1f) {
            this.drawRect(this.x, this.y, this.x + 75, this.y + 15, arrf);
        } else {
            this.drawRect(this.x, this.y, this.x + 75, this.y + 15, n);
        }
        int n2 = this.x + (75 - this.mc.fontRenderer.getStringWidth(this.category.name())) / 2;
        this.mc.fontRenderer.drawString(this.category.name(), n2, this.y + 4, -1);
        int n3 = 0;
        for (ModuleGuiElement hFuvwQNzRIuSpIC2 : this.guiList) {
            hFuvwQNzRIuSpIC2.doRender(this.x, this.y + 15 + n3 * 20);
            ++n3;
        }
    }

    public void drawRect(int n, int n2, int n3, int n4, float[] arrf) {
        int n5;
        if (n < n3) {
            n5 = n;
            n = n3;
            n3 = n5;
        }
        if (n2 < n4) {
            n5 = n2;
            n2 = n4;
            n4 = n5;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(arrf[0], arrf[1], arrf[2], arrf[3]);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(n, n4, 0.0).endVertex();
        bufferBuilder.pos(n3, n4, 0.0).endVertex();
        bufferBuilder.pos(n3, n2, 0.0).endVertex();
        bufferBuilder.pos(n, n2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void drawRect(int n, int n2, int n3, int n4, int n5) {
        Gui.drawRect(n, n2, n3, n4, n5);
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public void mouseClickMove(int n, int n2, int n3, long l) {
        if (this.isDragging()) {
            this.x = this.x + n - this.lastX;
            this.y = this.y + n2 - this.lastY;
            this.lastX = n;
            this.lastY = n2;
        }
    }

    public void mouseClicked(int n, int n2, int n3) {
        int n4 = this.x;
        int n5 = this.y;
        int n6 = this.x + 75;
        int n7 = this.y + 15;
        if (n >= n4 && n <= n6 && n2 >= n5 && n2 <= n7) {
            this.setDragging(true);
            this.lastX = n;
            this.lastY = n2;
        }
    }

    public void mouseReleased(int n, int n2, int n3) {
        if (this.isDragging()) {
            this.setDragging(false);
        }
    }

    public void setDragging(boolean bl) {
        this.dragging = bl;
    }
}
