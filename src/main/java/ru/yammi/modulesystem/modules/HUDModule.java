package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.modulesystem.ModuleManager;
import ru.yammi.eventsystem.events.Render2DEvent;
import ru.yammi.eventsystem.EventTarget;
import com.google.common.base.Strings;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import ru.yammi.Main;

public class HUDModule
        extends Module {

    public HUDModule() {
        super("HUD", ModuleCategory.MISC);
    }

    private void drawString(String string, int n, int n2, int n3) {
        Gui.drawRect(0, (n2 - 2), (2 + this.mc.fontRenderer.getStringWidth(string) + 3), (n2 + this.mc.fontRenderer.FONT_HEIGHT), Color.BLACK.getRGB());
        this.mc.fontRenderer.drawString(string, n, n2, n3);
    }

    @EventTarget
    public void onRender2D(Render2DEvent aFVmeSjveCmGfse) {
        if (this.getState() && this.mc.currentScreen == null) {
            String string3 = String.valueOf(new StringBuilder().append("Yammi b").append(Main.getInstance().getVersion()));
            Gui.drawRect(0, 0, (2 + this.mc.fontRenderer.getStringWidth(string3) + 3), (2 + this.mc.fontRenderer.FONT_HEIGHT), Color.BLACK.getRGB());
            int n = 0;
            int n2 = 2;
            for (int i = 0; i < string3.toCharArray().length; ++i) {
                int n3 = this.rainbow(n + n * 200000000L, 1.0f);
                char[] arrc = new char[]{string3.toCharArray()[i]};
                String string4 = new String(arrc);
                this.mc.fontRenderer.drawString(string4, n2, 2, n3);
                n2 += this.mc.fontRenderer.getStringWidth(string4);
                ++n;
            }
            this.drawString("Coded by InvHacks", 2, 14, this.rainbow(n + n * 200000000L, 1.0f));
            ArrayList<String> arrayList = new ArrayList();
            ModuleManager.getModules().stream().forEach(asaIBIYOhmSolWc -> {
                if (asaIBIYOhmSolWc.getState()) {
                    arrayList.add(String.valueOf(new StringBuilder().append(asaIBIYOhmSolWc.getName()).append(" \u00a7f(\u00a77").append(Keyboard.getKeyName(asaIBIYOhmSolWc.getKeybind())).append("\u00a7f)")));
                }
            });
            arrayList.sort((string, string2) -> this.mc.fontRenderer.getStringWidth(string2) - this.mc.fontRenderer.getStringWidth(string));
            long l = 0L;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            for (int i = 0; i < arrayList.size(); ++i) {
                String string5 = arrayList.get(i);
                if (Strings.isNullOrEmpty(string5)) {
                    continue;
                }
                int n8 = this.mc.fontRenderer.FONT_HEIGHT;
                int n9 = this.mc.fontRenderer.getStringWidth(string5);
                int n10 = scaledResolution.getScaledWidth() - 1 - n9;
                int n11 = 1 + (n8 + 2) * i;
                int n12 = this.rainbow(n4 + l * 200000000L, 1.0f);
                if (i == 0) {
                    Gui.drawRect((n10 - 2), (n11 - 1), (n10 + this.mc.fontRenderer.getStringWidth(string5)), n11, n12);
                }
                Gui.drawRect((n10 - 2), n11, (n10 + this.mc.fontRenderer.getStringWidth(string5)), (n11 + 11), 1140850688);
                Gui.drawRect((n10 - 3), (n11 - 1), (n10 - 2), (n11 + 11), n12);
                Gui.drawRect(n5, n7, (n5 + (n6 - this.mc.fontRenderer.getStringWidth(string5))), (n7 + 1), n12);
                n6 = this.mc.fontRenderer.getStringWidth(string5);
                n5 = n10 - 2;
                n7 = n11 + 10;
                Gui.drawRect((n10 + this.mc.fontRenderer.getStringWidth(string5)), (n11 - 1), (n10 + this.mc.fontRenderer.getStringWidth(string5) + 1), (n11 + 11), n12);
                if (i == arrayList.size() - 1) {
                    Gui.drawRect((n10 - 2), (n11 + 10), (n10 + this.mc.fontRenderer.getStringWidth(string5)), (n11 + 11), n12);
                }
                this.mc.fontRenderer.drawString(string5, n10, n11 + 1, n12);
                ++l;
                ++n4;
            }
            arrayList.clear();
        }
    }

    private int rainbow(long l, float f) {
        float f2 = (System.nanoTime() + l) / 2.0E10f % 1.0f;
        long l2 = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(f2 + 100000.0f, 1.0f, 1.0f)), 16);
        Color color = new Color((int) l2);
        return new Color(color.getRed() / 255.0f * f, color.getGreen() / 255.0f * f, color.getBlue() / 255.0f * f, color.getAlpha() / 255.0f).getRGB();
    }
}
