package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.eventsystem.EventTarget;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import ru.yammi.helpers.GLHelper;
import ru.yammi.helpers.ItemStackRenderHelper;
import ru.yammi.helpers.ReflectionHelper;

public class NameTagsModule
        extends Module {

    public NameTagsModule() {
        super("NameTags", ModuleCategory.RENDER);
    }

    private float AngleDistance(float f, float f2) {
        float f3 = Math.abs(f - f2) % 360.0f;
        if (f3 > 180.0f) {
            f3 = 360.0f - f3;
        }
        return f3;
    }

    private String getHealth(EntityPlayer entityPlayer) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        double d = 2.0f * (entityPlayer.getAbsorptionAmount() / 4.0f);
        double d2 = (10.0 + d) * (entityPlayer.getHealth() / entityPlayer.getMaxHealth());
        d2 = Double.valueOf(decimalFormat.format(d2));
        int n = (int) d2;
        return d2 % 1.0 != 0.0 ? String.valueOf(d2) : String.valueOf(n);
    }

    private int getHealthColorHEX(EntityPlayer entityPlayer) {
        int n = Math.round(20.0f * (entityPlayer.getHealth() / entityPlayer.getMaxHealth()));
        int n2 = -1;
        if (n >= 20) {
            n2 = 0x4cc417;
        } else if (n >= 18) {
            n2 = 0x8afb17;
        } else if (n >= 16) {
            n2 = 0x98ff98;
        } else if (n >= 14) {
            n2 = 0xc3fdb8;
        } else if (n >= 12) {
            n2 = 0xfdd017;
        } else if (n >= 10) {
            n2 = 0xe9ab17;
        } else if (n >= 8) {
            n2 = 0xf88017;
        } else if (n >= 6) {
            n2 = 0xf88158;
        } else if (n >= 4) {
            n2 = 0xe55b3c;
        } else if (n >= 2) {
            n2 = 0xff0000;
        } else if (n >= 0) {
            n2 = 0xf70d1a;
        }
        return n2;
    }

    private String getPlayerName(EntityPlayer entityPlayer) {
        return entityPlayer.getDisplayName().getFormattedText();
    }

    private float getSize(EntityPlayer entityPlayer) {
        EntityPlayerSP entityPlayerSP = this.mc.player;
        float f = entityPlayerSP.getDistance(entityPlayer) / 6.0f;
        return f <= 2.0f ? 1.3f : f;
    }

    public float[] getYawAndPitch(Entity entity) {
        EntityPlayerSP entityPlayerSP = this.mc.player;
        double d = entity.posX - entityPlayerSP.posX;
        double d2 = entity.posZ - entityPlayerSP.posZ;
        double d3 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - this.mc.player.posY;
        double d4 = MathHelper.sqrt((d * d + d2 * d2));
        float f = (float) (Math.atan2(d2, d) * 180.0 / 3.141592653589793) - 90.0f;
        float f2 = (float) (Math.atan2(d3 * 1.0, d4) * 180.0 / 3.141592653589793);
        return new float[]{f, f2};
    }

    public boolean isFacingAtEntity(Entity entity, double d) {
        EntityPlayerSP entityPlayerSP = this.mc.player;
        float[] arrf = this.getYawAndPitch(entity);
        float f = arrf[0];
        float f2 = arrf[1];
        return this.AngleDistance(entityPlayerSP.rotationYaw, f) < (d /= 4.5) && this.AngleDistance(entityPlayerSP.rotationPitch, f2) < d;
    }

    @EventTarget
    public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
        if (this.getState()) {
            List<EntityPlayer> list = this.mc.world.playerEntities;
            list.stream().filter((entityPlayer) -> !(entityPlayer == null || entityPlayer == this.mc.player)).forEachOrdered((entityPlayer) -> {
                try {
                    float f = ReflectionHelper.getPartialTicks();
                    double d = ReflectionHelper.getRenderPosX();
                    double d2 = ReflectionHelper.getRenderPosY();
                    double d3 = ReflectionHelper.getRenderPosZ();
                    double d4 = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * f - d;
                    double d5 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * f - d2;
                    double d6 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * f - d3;
                    this.renderNametag(entityPlayer, d4, d5, d6);
                }catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }
    }

    public void renderArmor(EntityPlayer entityPlayer) {
        int n = 0;
        NonNullList<ItemStack> nonNullList2 = entityPlayer.inventory.armorInventory;
        n = nonNullList2.stream().filter((object) -> !(object == null)).map((_item) -> 8).reduce(n, (accumulator, _item) -> accumulator - _item);
        if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null) {
            n -= 8;
            ItemStack itemStack = entityPlayer.getHeldItem(EnumHand.MAIN_HAND).copy();
            if (itemStack.hasEffect() && (itemStack.getItem() instanceof ItemTool || itemStack.getItem() instanceof ItemArmor)) {
                Field object;
                object = ReflectionHelper.getField(ItemStack.class, "stackSize", "field_77994_a", "c");
                try {
                    object.setInt(itemStack, 1);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            ItemStackRenderHelper.renderItemStack(itemStack, n, -19);
            n += 16;
        }
        NonNullList nonNullList = entityPlayer.inventory.armorInventory;
        for (int i = 3; i >= 0; --i) {
            ItemStack itemStack = (ItemStack) nonNullList.get(i);
            if (itemStack == null) {
                continue;
            }
            ItemStack itemStack2 = itemStack;
            ItemStackRenderHelper.renderItemStack(itemStack2, n, -19);
            n += 16;
        }
    }

    public void renderNametag(EntityPlayer entityPlayer, double d, double d2, double d3) {
        double d4 = this.getSize(entityPlayer) * -0.0225;
        FontRenderer fontRenderer = this.mc.fontRenderer;
        GL11.glPushMatrix();
        boolean bl = true;
        ItemStackRenderHelper.start3DOGLConstants();
        GL11.glTranslated(((float) d), (((float) d2 + entityPlayer.height) + 0.5), ((float) d3));
        GL11.glRotatef((-this.mc.getRenderManager().playerViewY), 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
        GL11.glScaled(d4, d4, d4);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        int n = bl ? fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(String.valueOf(String.valueOf(this.getPlayerName(entityPlayer)))).append(" ").append(this.getHealth(entityPlayer)))) / 2 : fontRenderer.getStringWidth(this.getPlayerName(entityPlayer)) / 2;
        int n2 = 1879048192;
        int n3 = 1879048192;
        GLHelper.drawBorderedRect(-n - 2, -this.mc.fontRenderer.FONT_HEIGHT - 6, n + 2, (float) (this.mc.fontRenderer.FONT_HEIGHT + 0.5), 1.0f, -1879048192, 1879048192);
        GL11.glDisable(2929);
        if (!bl) {
            fontRenderer.drawStringWithShadow(this.getPlayerName(entityPlayer), n, 0.0f, 15790320);
        } else if (bl) {
            fontRenderer.drawStringWithShadow(this.getPlayerName(entityPlayer), ((-fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(String.valueOf(String.valueOf(this.getPlayerName(entityPlayer)))).append(" ").append(this.getHealth(entityPlayer))))) / 2), 0.0f, 15790320);
            fontRenderer.drawStringWithShadow(this.getHealth(entityPlayer), ((fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(String.valueOf(String.valueOf(this.getPlayerName(entityPlayer)))).append(" ").append(this.getHealth(entityPlayer)))) - fontRenderer.getStringWidth(this.getHealth(entityPlayer)) * 2) / 2), 0.0f, this.getHealthColorHEX(entityPlayer));
        }
        GL11.glEnable(2929);
        ItemStackRenderHelper.finish3DOGLConstants();
        GL11.glPopMatrix();
    }
}
