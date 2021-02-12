package xyz.xianyu.util;

import java.util.function.*;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.client.*;
import java.awt.*;
import java.util.List;

import net.minecraft.client.resources.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.entity.*;

import org.lwjgl.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import java.nio.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import xyz.xianyu.util.math.*;
import xyz.xianyu.util.gl.*;

public class RenderUtil
{
    private static final List<Integer> csBuffer;
    private static final Consumer<Integer> ENABLE_CLIENT_STATE;
    private static final Consumer<Integer> DISABLE_CLIENT_STATE;
    private static Frustum frustrum;
    public static int deltaTime;
    public static Minecraft mc;
    public static final double TWICE_PI = 6.283185307179586;
    public static PlayerSkinTextureCache playerSkinTextureCache;
    
    static {
        RenderUtil.frustrum = new Frustum();
        RenderUtil.mc = Minecraft.getMinecraft();
        csBuffer = new ArrayList<Integer>();
        ENABLE_CLIENT_STATE = GL11::glEnableClientState;
        DISABLE_CLIENT_STATE = GL11::glEnableClientState;
    }

    public static void drawArc(float n, float n2, double n3, final int n4, final int n5, final double n6, final int n7) {
        n3 *= 2.0;
        n *= 2.0f;
        n2 *= 2.0f;
        final float n8 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n4 & 0xFF) / 255.0f;
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glLineWidth((float)n7);
        GL11.glEnable(2848);
        GL11.glColor4f(n9, n10, n11, n8);
        GL11.glBegin(3);
        int n12 = n5;
        while (n12 <= n6) {
            GL11.glVertex2d(n + Math.sin(n12 * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(n12 * 3.141592653589793 / 180.0) * n3);
            ++n12;
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    
    public static float[] RGBA(int color){
        if((color & -67108864)==0){
            color |=-16777216;
        }
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;
        float alpha = (float)(color >>24 & 255) / 255.0F;
        return new float[] {red,green,blue,alpha};
    }
    public static int[] rgba(int color){
        int red = (color>>16) & 0xFF;
        int green = (color>>8)&0xFF;
        int blue = color & 0xFF;
        int alpha = color>>24;
        return new int[]{red,green,blue,alpha};
    }
    public static void drawFullCircle(float cx, float cy, float r, final int c) {
        r *= 2.0f;
        cx *= 2.0f;
        cy *= 2.0f;
        final float theta = 0.19634953f;
        final float p = (float)Math.cos(theta);
        final float s = (float)Math.sin(theta);
        float x = r;
        float y = 0.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glBegin(2);
        glColor(c);
        for (int ii = 0; ii < 32; ++ii) {
            GL11.glVertex2f(x + cx, y + cy);
            GL11.glVertex2f(cx, cy);
            final float t = x;
            x = p * x - s * y;
            y = s * t + p * y;
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }
    
    
    public static String replaceColor(String str) {
        str = str.replaceAll("��1", "��0");
        str = str.replaceAll("��2", "��0");
        str = str.replaceAll("��3", "��0");
        str = str.replaceAll("��4", "��0");
        str = str.replaceAll("��5", "��0");
        str = str.replaceAll("��6", "��0");
        str = str.replaceAll("��7", "��0");
        str = str.replaceAll("��8", "��0");
        str = str.replaceAll("��9", "��0");
        str = str.replaceAll("��0", "��0");
        str = str.replaceAll("��a", "��0");
        str = str.replaceAll("��b", "��0");
        str = str.replaceAll("��c", "��0");
        str = str.replaceAll("��d", "��0");
        str = str.replaceAll("��e", "��0");
        str = str.replaceAll("��f", "��0");
        str = str.replaceAll("��r", "��0");
        str = str.replaceAll("��A", "��0");
        str = str.replaceAll("��B", "��0");
        str = str.replaceAll("��C", "��0");
        str = str.replaceAll("��D", "��0");
        str = str.replaceAll("��E", "��0");
        str = str.replaceAll("��F", "��0");
        str = str.replaceAll("��R", "��0");
        str = str.replaceAll("��r", "��0");
        return str;
    }
    
    public static void drawCenteredGradient(final double x, final double y, final double x2, final double y2, final int col1, final int col2) {
        final float width = (float)Math.abs(x - x2);
        final float height = (float)Math.abs(y - y2);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        final int bright = 50;
        final float var1 = 1.0f;
        final double left = Math.max(x, x2);
        final double right = Math.min(x, x2);
        for (double i = Math.min(x, x2); i < Math.min(x, x2) + width / 2.0f; ++i) {
            drawBorderRect(i, y, x2, y2, Color.BLACK.getRGB(), 2.0);
        }
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public static void refreshPlayerSkinTextureCache() {
        playerSkinTextureCache = new PlayerSkinTextureCache(mc.getSkinManager(), mc.getSessionService());
    }
    public static void drawPlayerHead(GameProfile gameProfile, int x, int y, int size) {
        ResourceLocation resourceLocation = playerSkinTextureCache.getSkinTexture(gameProfile);
        RenderUtil.drawPlayerHead(resourceLocation, x, y, size);
    }

    public static void drawPlayerHead(String username, int x, int y, int size) {
        ResourceLocation resourceLocation = playerSkinTextureCache.getSkinTexture(username);
        RenderUtil.drawPlayerHead(resourceLocation, x, y, size);
    }

    public static void drawPlayerHead(UUID uuid, int x, int y, int size) {
        ResourceLocation resourceLocation = playerSkinTextureCache.getSkinTexture(uuid);
        RenderUtil.drawPlayerHead(resourceLocation, x, y, size);
    }

    public static void drawPlayerHead(ResourceLocation resourceLocation, int x, int y, int size) {
        if (resourceLocation == null) {
            resourceLocation = DefaultPlayerSkin.getDefaultSkin(UUID.randomUUID());
        }
        GlStateManager.enableAlpha();
        mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawScaledCustomSizeModalRect(x, y, 8.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
        Gui.drawScaledCustomSizeModalRect(x, y, 40.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
    }

    public static void drawPlayerHead(String playerName, int x, int y, int width, int height) {
        for (Object player : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
            if (player instanceof EntityPlayer) {
                EntityPlayer ply = (EntityPlayer) player;
                if (playerName.equalsIgnoreCase(ply.getName())) {
                    GameProfile profile = new GameProfile(ply.getUniqueID(), ply.getName());
                    NetworkPlayerInfo networkplayerinfo1 = new NetworkPlayerInfo(profile);
                    new ScaledResolution(Minecraft.getMinecraft());
                    GL11.glDisable((int) 2929);
                    GL11.glEnable((int) 3042);
                    GL11.glDepthMask((boolean) false);
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                    GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
                    Minecraft.getMinecraft().getTextureManager().bindTexture(networkplayerinfo1.getLocationSkin());
                    Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
                    GL11.glDepthMask((boolean) true);
                    GL11.glDisable((int) 3042);
                    GL11.glEnable((int) 2929);
                }
            }
        }
    }

    public static void drawPlayerHead(EntityPlayer playerln, int x, int y, int size) {
        List var5 = GuiPlayerTabOverlay.field_175252_a.sortedCopy(RenderUtil.mc.thePlayer.sendQueue.getPlayerInfoMap());
        for (Object aVar5 : var5) {
            NetworkPlayerInfo var24 = (NetworkPlayerInfo)aVar5;
            if (RenderUtil.mc.theWorld.getPlayerEntityByUUID(var24.getGameProfile().getId()) != playerln) continue;
            mc.getTextureManager().bindTexture(var24.getLocationSkin());
            Gui.drawScaledCustomSizeModalRect(x, y, 8.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
            if (playerln.isWearing(EnumPlayerModelParts.HAT)) {
                Gui.drawScaledCustomSizeModalRect(x, y, 40.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
            }
            GlStateManager.bindTexture(0);
            break;
        }
    }
    
 
    public static double getAnimationState(double animation, final double finalState, final double speed) {
        final double add = RenderUtil.deltaTime / 1000.0f * speed;
        if (animation < finalState) {
            if (animation + add >= finalState) {
                return finalState;
            }
            return animation += add;
        }
        else {
            if (animation - add <= finalState) {
                return finalState;
            }
            return animation -= add;
        }
    }
    
    public static void drawHLine(final double x, final double y, final double x1, final double y1, final float width, final int color) {
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        GL11.glPushMatrix();
        GL11.glLineWidth(width);
        GL11.glBegin(3);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glLineWidth(1.0f);
        GL11.glPopMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawVLine(final float x, final float y, final float x1, final float y1, final float width, final int color) {
        if (width <= 0.0f) {
            return;
        }
        GL11.glPushMatrix();
        pre3D();
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        final int shade = GL11.glGetInteger(2900);
        GlStateManager.shadeModel(7425);
        GL11.glColor4f(var12, var13, var14, var11);
        final float line = GL11.glGetFloat(2849);
        GL11.glLineWidth(width);
        GL11.glBegin(3);
        GL11.glVertex3d((double)x, (double)y, 0.0);
        GL11.glVertex3d((double)x1, (double)y1, 0.0);
        GL11.glEnd();
        GlStateManager.shadeModel(shade);
        GL11.glLineWidth(line);
        post3D();
        GL11.glPopMatrix();
    }
    
    public static void drawGradient(final double x, final double y, final double x2, final double y2, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void drawBorderRect(final double x, final double y, final double x1, final double y1, final int color, final double lwidth) {
        drawHLine(x, y, x1, y, (float)lwidth, color);
        drawHLine(x1, y, x1, y1, (float)lwidth, color);
        drawHLine(x, y1, x1, y1, (float)lwidth, color);
        drawHLine(x, y1, x, y, (float)lwidth, color);
    }
    
    public static void renderCircle(final double x, final double y, final double z, final double radius, final float lineWidth, final Color color, final float opacity) {
        GlStateManager.pushMatrix();
        GL11.glLineWidth(lineWidth);
        GL11.glColor3f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue());
        GL11.glBegin(1);
        for (int i = 0; i <= 90; ++i) {
            final double angle = i * 6.283185307179586 / 45.0;
            GL11.glColor4f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), opacity);
            GL11.glVertex3d(x + radius * Math.cos(angle), y, z + radius * Math.sin(angle));
        }
        GL11.glEnd();
        GlStateManager.popMatrix();
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void renderEntity(final EntityPlayer e, int color, final int type) {
        if (e != null) {
            final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * RenderUtil.mc.timer.renderPartialTicks - RenderUtil.mc.getRenderManager().viewerPosX;
            final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * RenderUtil.mc.timer.renderPartialTicks - RenderUtil.mc.getRenderManager().viewerPosY;
            final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * RenderUtil.mc.timer.renderPartialTicks - RenderUtil.mc.getRenderManager().viewerPosZ;
            if (e instanceof EntityPlayer && ((EntityPlayer)e).hurtTime != 0) {
                color = Color.RED.getRGB();
            }
            if (type == 1) {
                GlStateManager.pushMatrix();
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glDisable(3553);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GL11.glLineWidth(2.0f);
                final float a = (color >> 24 & 0xFF) / 255.0f;
                final float r = (color >> 16 & 0xFF) / 255.0f;
                final float g = (color >> 8 & 0xFF) / 255.0f;
                final float b = (color & 0xFF) / 255.0f;
                GL11.glColor4f(r, g, b, a);
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderUtil.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderUtil.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderUtil.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderUtil.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderUtil.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderUtil.mc.getRenderManager().viewerPosZ)));
                dbb(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderUtil.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderUtil.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderUtil.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderUtil.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderUtil.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderUtil.mc.getRenderManager().viewerPosZ)), r, g, b);
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
                GL11.glDisable(3042);
                GlStateManager.popMatrix();
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
            else if (type == 2) {
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glLineWidth(2.0f);
                GL11.glDisable(3553);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                final float a = (color >> 24 & 0xFF) / 255.0f;
                final float r = (color >> 16 & 0xFF) / 255.0f;
                final float g = (color >> 8 & 0xFF) / 255.0f;
                final float b = (color & 0xFF) / 255.0f;
                GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderUtil.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderUtil.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderUtil.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderUtil.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderUtil.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderUtil.mc.getRenderManager().viewerPosZ)));
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
                GL11.glDisable(3042);
            }
            else if (type == 3) {
                GL11.glPushMatrix();
                GL11.glTranslated(x, y - 0.2, z);
                GL11.glScalef(0.03f, 0.03f, 0.03f);
                RenderUtil.mc.getRenderManager();
                GL11.glRotated((double)(-RenderManager.playerViewY), 0.0, 1.0, 0.0);
                GlStateManager.disableDepth();
                drawRect(-20.0, -1.0, -26.0, 75.0, Color.black.getRGB());
                drawRect(-21.0, 0.0, -25.0, 74.0, color);
                drawRect(20.0, -1.0, 26.0, 75.0, Color.black.getRGB());
                drawRect(21.0, 0.0, 25.0, 74.0, color);
                drawRect(-20.0, -1.0, 21.0, 5.0, Color.black.getRGB());
                drawRect(-21.0, 0.0, 24.0, 4.0, color);
                drawRect(-20.0, 70.0, 21.0, 75.0, Color.black.getRGB());
                drawRect(-21.0, 71.0, 25.0, 74.0, color);
                GlStateManager.enableDepth();
                GL11.glPopMatrix();
            }
        }
    }
    
    public static void dbb(final AxisAlignedBB abb, final float r, final float g, final float b) {
        final float a = 0.25f;
        final Tessellator ts = Tessellator.getInstance();
        final WorldRenderer vb = ts.getWorldRenderer();
        vb.begin(7, DefaultVertexFormats.field_181706_f);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.field_181706_f);
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.field_181706_f);
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.field_181706_f);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.field_181706_f);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.field_181706_f);
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
    }
    
    public static void drawFastRoundedRect(final float x0, final float y0, final float x1, final float y1, final float radius, final int color) {
        final int Semicircle = 18;
        final float f = 5.0f;
        final float f2 = (color >> 24 & 0xFF) / 255.0f;
        final float f3 = (color >> 16 & 0xFF) / 255.0f;
        final float f4 = (color >> 8 & 0xFF) / 255.0f;
        final float f5 = (color & 0xFF) / 255.0f;
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(f3, f4, f5, f2);
        GL11.glBegin(5);
        GL11.glVertex2f(x0 + radius, y0);
        GL11.glVertex2f(x0 + radius, y1);
        GL11.glVertex2f(x1 - radius, y0);
        GL11.glVertex2f(x1 - radius, y1);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(x0, y0 + radius);
        GL11.glVertex2f(x0 + radius, y0 + radius);
        GL11.glVertex2f(x0, y1 - radius);
        GL11.glVertex2f(x0 + radius, y1 - radius);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(x1, y0 + radius);
        GL11.glVertex2f(x1 - radius, y0 + radius);
        GL11.glVertex2f(x1, y1 - radius);
        GL11.glVertex2f(x1 - radius, y1 - radius);
        GL11.glEnd();
        GL11.glBegin(6);
        float f6 = x1 - radius;
        float f7 = y0 + radius;
        GL11.glVertex2f(f6, f7);
        int j;
        float f8;
        for (j = 0, j = 0; j <= 18; ++j) {
            f8 = j * 5.0f;
            GL11.glVertex2f((float)(f6 + radius * Math.cos(Math.toRadians(f8))), (float)(f7 - radius * Math.sin(Math.toRadians(f8))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f6 = x0 + radius;
        f7 = y0 + radius;
        GL11.glVertex2f(f6, f7);
        for (j = 0; j <= 18; ++j) {
            final float f9 = j * 5.0f;
            GL11.glVertex2f((float)(f6 - radius * Math.cos(Math.toRadians(f9))), (float)(f7 - radius * Math.sin(Math.toRadians(f9))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f6 = x0 + radius;
        f7 = y1 - radius;
        GL11.glVertex2f(f6, f7);
        for (j = 0; j <= 18; ++j) {
            final float f10 = j * 5.0f;
            GL11.glVertex2f((float)(f6 - radius * Math.cos(Math.toRadians(f10))), (float)(f7 + radius * Math.sin(Math.toRadians(f10))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f6 = x1 - radius;
        f7 = y1 - radius;
        GL11.glVertex2f(f6, f7);
        for (j = 0; j <= 18; ++j) {
            final float f11 = j * 5.0f;
            GL11.glVertex2f((float)(f6 + radius * Math.cos(Math.toRadians(f11))), (float)(f7 + radius * Math.sin(Math.toRadians(f11))));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void drawCircle(float cx, float cy, float r, final int num_segments, final int c) {
        GL11.glPushMatrix();
        cx *= 2.0f;
        cy *= 2.0f;
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        final float theta = (float)(6.2831852 / num_segments);
        final float p = (float)Math.cos(theta);
        final float s = (float)Math.sin(theta);
        float x;
        r = (x = r * 2.0f);
        float y = 0.0f;
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(2);
        for (int ii = 0; ii < num_segments; ++ii) {
            GL11.glVertex2f(x + cx, y + cy);
            final float t = x;
            x = p * x - s * y;
            y = s * t + p * y;
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawGradientSideways(final double left, final double top, final double right, final double bottom, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
        GL11.glColor4d(255.0, 255.0, 255.0, 255.0);
    }
    
    public static void drawMyTexturedModalRect(final float x, final float y, final int textureX, final int textureY, final float width, final float height, final float factor) {
        final float f3;
        final float f2 = f3 = 1.0f / factor;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldrenderer.pos(x, y + height, 0.0).tex(textureX * f3, (textureY + height) * f3).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((textureX + width) * f3, (textureY + height) * f3).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((textureX + width) * f3, textureY * f3).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(textureX * f3, textureY * f3).endVertex();
        tessellator.draw();
    }
    
    public static void drawHorizontalLine(final float x, final float y, final float x1, final float thickness, final int color) {
        drawRect(x, y, x1, y + thickness, color);
    }
    
    public static void drawVerticalLine(final float x, final float y, final float y1, final float thickness, final int color) {
        drawRect(x, y, x + thickness, y1, color);
    }
    
    public static void drawRect(final double x, final double y, final double x2, final double y2, final int color) {
        Gui.drawRect((int)x, (int)y, (int)x2, (int)y2, color);
    }
    
    public static int getColor(final int red, final int green, final int blue, final int alpha) {
        final byte color = 0;
        int color2 = color | alpha << 24;
        color2 |= red << 16;
        color2 |= green << 8;
        color2 |= blue;
        return color2;
    }
    
    public static void drawHollowBox(final float x, final float y, final float x1, final float y1, final float thickness, final int color) {
        drawHorizontalLine(x, y, x1, thickness, color);
        drawHorizontalLine(x, y1, x1, thickness, color);
        drawVerticalLine(x, y, y1, thickness, color);
        drawVerticalLine(x1 - thickness, y, y1, thickness, color);
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static boolean isInViewFrustrum(final AxisAlignedBB bb) {
        final Entity current = RenderUtil.mc.getRenderViewEntity();
        RenderUtil.frustrum.setPosition(current.posX, current.posY, current.posZ);
        return RenderUtil.frustrum.isBoundingBoxInFrustum(bb);
    }
    
    public static int getHealthColor(final EntityLivingBase player) {
        final float f = player.getHealth();
        final float f2 = player.getMaxHealth();
        final float f3 = Math.max(0.0f, Math.min(f, f2) / f2);
        return Color.HSBtoRGB(f3 / 3.0f, 1.0f, 1.0f) | 0xFF000000;
    }
    
    public static float[] getRGBAs(final int rgb) {
        return new float[] { (rgb >> 16 & 0xFF) / 255.0f, (rgb >> 8 & 0xFF) / 255.0f, (rgb & 0xFF) / 255.0f, (rgb >> 24 & 0xFF) / 255.0f };
    }
    
    public static int getRainbow(final int speed, final int offset) {
        float hue = (System.currentTimeMillis() + offset) % speed;
        hue /= speed;
        return Color.getHSBColor(hue, 0.75f, 1.0f).getRGB();
    }
    
    public static void rectangle(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double var5 = left;
            left = right;
            right = var5;
        }
        if (top < bottom) {
            final double var5 = top;
            top = bottom;
            bottom = var5;
        }
        final float var6 = (color >> 24 & 0xFF) / 255.0f;
        final float var7 = (color >> 16 & 0xFF) / 255.0f;
        final float var8 = (color >> 8 & 0xFF) / 255.0f;
        final float var9 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var7, var8, var9, var6);
        worldRenderer.begin(7, DefaultVertexFormats.field_181705_e);
        worldRenderer.pos(left, bottom, 0.0).endVertex();
        worldRenderer.pos(right, bottom, 0.0).endVertex();
        worldRenderer.pos(right, top, 0.0).endVertex();
        worldRenderer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void rectangleBordered(final double x, final double y, final double x1, final double y1, final double width, final int internalColor, final int borderColor) {
        rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x, y, x + width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x1 - width, y, x1, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static double[] convertTo2D(final double x, final double y, final double z) {
        final FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        final IntBuffer viewport = BufferUtils.createIntBuffer(16);
        final FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
        final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(2982, modelView);
        GL11.glGetFloat(2983, projection);
        GL11.glGetInteger(2978, viewport);
        final boolean result = GLU.gluProject((float)x, (float)y, (float)z, modelView, projection, viewport, screenCoords);
        double[] arrd3;
        if (result) {
            final double[] arrd2 = arrd3 = new double[] { screenCoords.get(0), Display.getHeight() - screenCoords.get(1), 0.0 };
            arrd2[2] = screenCoords.get(2);
        }
        else {
            arrd3 = null;
        }
        return arrd3;
    }
    
    public static void drawblock(final double a, final double a2, final double a3, final int a4, final int a5, final float a6) {
        final float a7 = (a4 >> 24 & 0xFF) / 255.0f;
        final float a8 = (a4 >> 16 & 0xFF) / 255.0f;
        final float a9 = (a4 >> 8 & 0xFF) / 255.0f;
        final float a10 = (a4 & 0xFF) / 255.0f;
        final float a11 = (a5 >> 24 & 0xFF) / 255.0f;
        final float a12 = (a5 >> 16 & 0xFF) / 255.0f;
        final float a13 = (a5 >> 8 & 0xFF) / 255.0f;
        final float a14 = (a5 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(a8, a9, a10, a7);
        drawOutlinedBoundingBox(new AxisAlignedBB(a, a2, a3, a + 1.0, a2 + 1.0, a3 + 1.0));
        GL11.glLineWidth(a6);
        GL11.glColor4f(a12, a13, a14, a11);
        drawOutlinedBoundingBox(new AxisAlignedBB(a, a2, a3, a + 1.0, a2 + 1.0, a3 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void pre3D() {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glHint(3154, 4354);
    }
    
    public static int width() {
        return new ScaledResolution(RenderUtil.mc).getScaledWidth();
    }
    
    public static int height() {
        return new ScaledResolution(RenderUtil.mc).getScaledHeight();
    }
    
    public static int getHexRGB(final int hex) {
        return 0xFF000000 | hex;
    }
    
    public static void drawEntityOnScreen(final int posX, final int posY, final int scale, final float mouseX, final float mouseY, final EntityLivingBase ent) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, 50.0f);
        GlStateManager.scale(-scale, scale, scale);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        final float f = ent.renderYawOffset;
        final float f2 = ent.rotationYaw;
        final float f3 = ent.rotationPitch;
        final float f4 = ent.prevRotationYawHead;
        final float f5 = ent.rotationYawHead;
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(mouseY / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        ent.renderYawOffset = (float)Math.atan(mouseX / 40.0f) * 20.0f;
        ent.rotationYaw = (float)Math.atan(mouseX / 40.0f) * 40.0f;
        ent.rotationPitch = -(float)Math.atan(mouseY / 40.0f) * 20.0f;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(ent, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f2;
        ent.rotationPitch = f3;
        ent.prevRotationYawHead = f4;
        ent.rotationYawHead = f5;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
    public static void drawBorderedRect(final float x, final float y, final float x2, final float y2, final float l1, final int col1, final int col2) {
        Gui.drawRect((int)x, (int)y, (int)x2, (int)y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void pre() {
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }
    
    public static void post() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glColor3d(1.0, 1.0, 1.0);
    }
    
    public static void startDrawing() {
        GL11.glEnable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
    }
    
    public static void stopDrawing() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }
    
    public static Color blend(final Color color1, final Color color2, final double ratio) {
        final float r = (float)ratio;
        final float ir = 1.0f - r;
        final float[] rgb1 = new float[3];
        final float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        final Color color3 = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);
        return color3;
    }

    
    public static void setupRender(final boolean start) {
        if (start) {
            GlStateManager.enableBlend();
            GL11.glEnable(2848);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GlStateManager.blendFunc(770, 771);
            GL11.glHint(3154, 4354);
        }
        else {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GL11.glDisable(2848);
            GlStateManager.enableDepth();
        }
        GlStateManager.depthMask(!start);
    }
    
    public static void drawImage(final ResourceLocation image, final int x, final int y, final int width, final int height) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawImage1(ResourceLocation image, double x, double y, double width, double height) {
        new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable((int) 2929);
        GL11.glEnable((int) 3042);
        GL11.glDepthMask((boolean) false);
        OpenGlHelper.glBlendFunc((int) 770, (int) 771, (int) 1, (int) 0);
        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        RenderUtil.drawModalRectWithCustomSizedTexture((float) x, (float) y, (float) 0.0f, (float) 0.0f, (float) width,
                (float) height, (float) width, (float) height);
        GL11.glDepthMask((boolean) true);
        GL11.glDisable((int) 3042);
        GL11.glEnable((int) 2929);
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width,
                                                           float height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.field_181707_g);
        worldrenderer.pos((double) x, (double) (y + height), 0.0D)
                .tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y + height), 0.0D)
                .tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) y, 0.0D)
                .tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
        worldrenderer.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
        tessellator.draw();
    }
    
    public static void layeredRect(final double x1, final double y1, final double x2, final double y2, final int outline, final int inline, final int background) {
        R2DUtils.drawRect(x1, y1, x2, y2, outline);
        R2DUtils.drawRect(x1 + 1.0, y1 + 1.0, x2 - 1.0, y2 - 1.0, inline);
        R2DUtils.drawRect(x1 + 2.0, y1 + 2.0, x2 - 2.0, y2 - 2.0, background);
    }
    
    public static void drawEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha, final float lineRed, final float lineGreen, final float lineBlue, final float lineAlpha, final float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(3, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.field_181706_f);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void glColor(final float alpha, final int redRGB, final int greenRGB, final int blueRGB) {
        final float red = 0.003921569f * redRGB;
        final float green = 0.003921569f * greenRGB;
        final float blue = 0.003921569f * blueRGB;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void glColor(final int hex) {
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void post3D() {
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static boolean isMouseOver1(final int mouseX, final int mouseY, final int x, final int y, final int width, final int height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
    
    public static boolean isMouseOver2(final int mouseX, final int mouseY, final int width, final int height, final int width2, final int height2) {
        return mouseX > width && mouseX < width2 && mouseY > height && mouseY < height2;
    }

    public static void drawRoundedRect(float x, float y, float x2, float y2, final float round, final int color) {
        x += (float) (round / 2.0f + 0.5);
        y += (float) (round / 2.0f + 0.5);
        x2 -= (float) (round / 2.0f + 0.5);
        y2 -= (float) (round / 2.0f + 0.5);
        Gui.drawRect((int) x, (int) y, (int) x2, (int) y2, color);
        circle(x2 - round / 2.0f, y + round / 2.0f, round, color);
        circle(x + round / 2.0f, y2 - round / 2.0f, round, color);
        circle(x + round / 2.0f, y + round / 2.0f, round, color);
        circle(x2 - round / 2.0f, y2 - round / 2.0f, round, color);
        Gui.drawRect((int) (x - round / 2.0f - 0.5f), (int) (y + round / 2.0f), (int) x2, (int) (y2 - round / 2.0f),
                color);
        Gui.drawRect((int) x, (int) (y + round / 2.0f), (int) (x2 + round / 2.0f + 0.5f), (int) (y2 - round / 2.0f),
                color);
        Gui.drawRect((int) (x + round / 2.0f), (int) (y - round / 2.0f - 0.5f), (int) (x2 - round / 2.0f),
                (int) (y2 - round / 2.0f), color);
        Gui.drawRect((int) (x + round / 2.0f), (int) y, (int) (x2 - round / 2.0f), (int) (y2 + round / 2.0f + 0.5f),
                color);
    }

    public static void drawRoundRect(double d, double e, double g, double h, int color) {
        RenderUtil.drawRect(d + 1.0, e, g - 1.0, h, color);
        RenderUtil.drawRect(d, e + 1.0, d + 1.0, h - 1.0, color);
        RenderUtil.drawRect(d + 1.0, e + 1.0, d + 0.5, e + 0.5, color);
        RenderUtil.drawRect(d + 1.0, e + 1.0, d + 0.5, e + 0.5, color);
        RenderUtil.drawRect(g - 1.0, e + 1.0, g - 0.5, e + 0.5, color);
        RenderUtil.drawRect(g - 1.0, e + 1.0, g, h - 1.0, color);
        RenderUtil.drawRect(d + 1.0, h - 1.0, d + 0.5, h - 0.5, color);
        RenderUtil.drawRect(g - 1.0, h - 1.0, g - 0.5, h - 0.5, color);
    }


    public static class R3DUtils
    {
        public static void startDrawing() {
            GL11.glEnable(3042);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            RenderUtil.mc.entityRenderer.setupCameraTransform(RenderUtil.mc.timer.renderPartialTicks, 0);
        }
        
        public static void stopDrawing() {
            GL11.glDisable(3042);
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glEnable(2929);
        }
        
        public static void drawOutlinedBox(final AxisAlignedBB box) {
            if (box == null) {
                return;
            }
            RenderUtil.mc.entityRenderer.setupCameraTransform(RenderUtil.mc.timer.renderPartialTicks, 0);
            GL11.glBegin(3);
            GL11.glVertex3d(box.minX, box.minY, box.minZ);
            GL11.glVertex3d(box.maxX, box.minY, box.minZ);
            GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
            GL11.glVertex3d(box.minX, box.minY, box.maxZ);
            GL11.glVertex3d(box.minX, box.minY, box.minZ);
            GL11.glEnd();
            GL11.glBegin(3);
            GL11.glVertex3d(box.minX, box.maxY, box.minZ);
            GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
            GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxY, box.minZ);
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex3d(box.minX, box.minY, box.minZ);
            GL11.glVertex3d(box.minX, box.maxY, box.minZ);
            GL11.glVertex3d(box.maxX, box.minY, box.minZ);
            GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
            GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
            GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
            GL11.glVertex3d(box.minX, box.minY, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
            GL11.glEnd();
        }
        
        public static void drawFilledBox(final AxisAlignedBB mask) {
            GL11.glBegin(4);
            GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
            GL11.glEnd();
            GL11.glBegin(4);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
            GL11.glEnd();
            GL11.glBegin(4);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
            GL11.glEnd();
            GL11.glBegin(4);
            GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
            GL11.glEnd();
            GL11.glBegin(4);
            GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
            GL11.glEnd();
            GL11.glBegin(4);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
            GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
            GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
            GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
            GL11.glEnd();
        }
        
        public static void drawOutlinedBoundingBox(final AxisAlignedBB aabb) {
            GL11.glBegin(3);
            GL11.glVertex3d(aabb.minX, aabb.minY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.maxZ);
            GL11.glVertex3d(aabb.minX, aabb.minY, aabb.maxZ);
            GL11.glVertex3d(aabb.minX, aabb.minY, aabb.minZ);
            GL11.glEnd();
            GL11.glBegin(3);
            GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.maxZ);
            GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.maxZ);
            GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.minZ);
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex3d(aabb.minX, aabb.minY, aabb.minZ);
            GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.minZ);
            GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.maxZ);
            GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.maxZ);
            GL11.glVertex3d(aabb.minX, aabb.minY, aabb.maxZ);
            GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.maxZ);
            GL11.glEnd();
        }
    }
    
    public static class R2DUtils
    {
        public static void enableGL2D() {
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glDepthMask(true);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
        }
        
        public static void disableGL2D() {
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glHint(3154, 4352);
            GL11.glHint(3155, 4352);
        }
        
        public static void draw2DCorner(final Entity e, final double posX, final double posY, final double posZ, final int color) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(posX, posY, posZ);
            GL11.glNormal3f(0.0f, 0.0f, 0.0f);
            GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.scale(-0.1, -0.1, 0.1);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GlStateManager.depthMask(true);
            drawRect(7.0, -20.0, 7.300000190734863, -17.5, color);
            drawRect(-7.300000190734863, -20.0, -7.0, -17.5, color);
            drawRect(4.0, -20.299999237060547, 7.300000190734863, -20.0, color);
            drawRect(-7.300000190734863, -20.299999237060547, -4.0, -20.0, color);
            drawRect(-7.0, 3.0, -4.0, 3.299999952316284, color);
            drawRect(4.0, 3.0, 7.0, 3.299999952316284, color);
            drawRect(-7.300000190734863, 0.8, -7.0, 3.299999952316284, color);
            drawRect(7.0, 0.5, 7.300000190734863, 3.299999952316284, color);
            drawRect(7.0, -20.0, 7.300000190734863, -17.5, color);
            drawRect(-7.300000190734863, -20.0, -7.0, -17.5, color);
            drawRect(4.0, -20.299999237060547, 7.300000190734863, -20.0, color);
            drawRect(-7.300000190734863, -20.299999237060547, -4.0, -20.0, color);
            drawRect(-7.0, 3.0, -4.0, 3.299999952316284, color);
            drawRect(4.0, 3.0, 7.0, 3.299999952316284, color);
            drawRect(-7.300000190734863, 0.8, -7.0, 3.299999952316284, color);
            drawRect(7.0, 0.5, 7.300000190734863, 3.299999952316284, color);
            GL11.glDisable(3042);
            GL11.glEnable(2929);
            GlStateManager.popMatrix();
        }
        
        public static void drawRoundedRect(float x, float y, float x1, float y1, final int borderC, final int insideC) {
            enableGL2D();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            drawVLine(x *= 2.0f, (y *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
            drawVLine((x1 *= 2.0f) - 1.0f, y + 1.0f, y1 - 2.0f, borderC);
            drawHLine(x + 2.0f, x1 - 3.0f, y, borderC);
            drawHLine(x + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
            drawHLine(x + 1.0f, x + 1.0f, y + 1.0f, borderC);
            drawHLine(x1 - 2.0f, x1 - 2.0f, y + 1.0f, borderC);
            drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
            drawHLine(x + 1.0f, x + 1.0f, y1 - 2.0f, borderC);
            drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            disableGL2D();
            Gui.drawRect(0, 0, 0, 0, 0);
        }
        
        public static void drawRect(final double x2, final double y2, final double x1, final double y1, final int color) {
            enableGL2D();
            glColor(color);
            drawRect(x2, y2, x1, y1);
            disableGL2D();
        }
        
        private static void drawRect(final double x2, final double y2, final double x1, final double y1) {
            GL11.glBegin(7);
            GL11.glVertex2d(x2, y1);
            GL11.glVertex2d(x1, y1);
            GL11.glVertex2d(x1, y2);
            GL11.glVertex2d(x2, y2);
            GL11.glEnd();
        }
        
        public static void glColor(final int hex) {
            final float alpha = (hex >> 24 & 0xFF) / 255.0f;
            final float red = (hex >> 16 & 0xFF) / 255.0f;
            final float green = (hex >> 8 & 0xFF) / 255.0f;
            final float blue = (hex & 0xFF) / 255.0f;
            GL11.glColor4f(red, green, blue, alpha);
        }
        
        public static void drawRect(final float x, final float y, final float x1, final float y1, final int color) {
            enableGL2D();
            glColor(color);
            drawRect(x, y, x1, y1);
            disableGL2D();
        }
        
        public static void drawBorderedRect(final float x, final float y, final float x1, final float y1, final float width, final int borderColor) {
            enableGL2D();
            glColor(borderColor);
            drawRect(x + width, y, x1 - width, y + width);
            drawRect(x, y, x + width, y1);
            drawRect(x1 - width, y, x1, y1);
            drawRect(x + width, y1 - width, x1 - width, y1);
            disableGL2D();
        }
        
        public static void drawBorderedRect(float x, float y, float x1, float y1, final int insideC, final int borderC) {
            enableGL2D();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            drawVLine(x *= 2.0f, y *= 2.0f, y1 *= 2.0f, borderC);
            drawVLine((x1 *= 2.0f) - 1.0f, y, y1, borderC);
            drawHLine(x, x1 - 1.0f, y, borderC);
            drawHLine(x, x1 - 2.0f, y1 - 1.0f, borderC);
            drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            disableGL2D();
        }
        
        public static void drawGradientRect(final float x, final float y, final float x1, final float y1, final int topColor, final int bottomColor) {
            enableGL2D();
            GL11.glShadeModel(7425);
            GL11.glBegin(7);
            glColor(topColor);
            GL11.glVertex2f(x, y1);
            GL11.glVertex2f(x1, y1);
            glColor(bottomColor);
            GL11.glVertex2f(x1, y);
            GL11.glVertex2f(x, y);
            GL11.glEnd();
            GL11.glShadeModel(7424);
            disableGL2D();
        }
        
        public static void drawHLine(float x, float y, final float x1, final int y1) {
            if (y < x) {
                final float var5 = x;
                x = y;
                y = var5;
            }
            drawRect(x, x1, y + 1.0f, x1 + 1.0f, y1);
        }
        
        public static void drawVLine(final float x, float y, float x1, final int y1) {
            if (x1 < y) {
                final float var5 = y;
                y = x1;
                x1 = var5;
            }
            drawRect(x, y + 1.0f, x + 1.0f, x1, y1);
        }
        
        public static void drawHLine(float x, float y, final float x1, final int y1, final int y2) {
            if (y < x) {
                final float var5 = x;
                x = y;
                y = var5;
            }
            drawGradientRect(x, x1, y + 1.0f, x1 + 1.0f, y1, y2);
        }
        
        public static void drawRect(final float x, final float y, final float x1, final float y1) {
            GL11.glBegin(7);
            GL11.glVertex2f(x, y1);
            GL11.glVertex2f(x1, y1);
            GL11.glVertex2f(x1, y);
            GL11.glVertex2f(x, y);
            GL11.glEnd();
        }
    }

	public static double interpolate(double newPos, double oldPos) {
        return oldPos + (newPos - oldPos) * (double)Minecraft.getMinecraft().timer.renderPartialTicks;
    }

	public static void circle(final float x, final float y, final float radius, final int fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void circle(final float x, final float y, final float radius, final Color fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void arc(final float x, final float y, final float start, final float end, final float radius,
                           final int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }
    public static void arc(final float x, final float y, final float start, final float end, final float radius,
            final Color color) {
arcEllipse(x, y, start, end, radius, radius, color);
}

public static void arcEllipse(final float x, final float y, float start, float end, final float w, final float h,
                   final int color) {
GlStateManager.color(0.0f, 0.0f, 0.0f);
GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
float temp = 0.0f;
if (start > end) {
temp = end;
end = start;
start = temp;
}
final float var11 = (color >> 24 & 0xFF) / 255.0f;
final float var12 = (color >> 16 & 0xFF) / 255.0f;
final float var13 = (color >> 8 & 0xFF) / 255.0f;
final float var14 = (color & 0xFF) / 255.0f;
final Tessellator var15 = Tessellator.getInstance();
var15.getWorldRenderer();
GlStateManager.enableBlend();
GlStateManager.disableTexture2D();
GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
GlStateManager.color(var12, var13, var14, var11);
if (var11 > 0.5f) {
GL11.glEnable(2848);
GL11.glLineWidth(2.0f);
GL11.glBegin(3);
for (float i = end; i >= start; i -= 4.0f) {
 final float ldx = (float) Math.cos(i * Math.PI / 180.0) * w * 1.001f;
 final float ldy = (float) Math.sin(i * Math.PI / 180.0) * h * 1.001f;
 GL11.glVertex2f(x + ldx, y + ldy);
}
GL11.glEnd();
GL11.glDisable(2848);
}
GL11.glBegin(6);
for (float i = end; i >= start; i -= 4.0f) {
final float ldx = (float) Math.cos(i * Math.PI / 180.0) * w;
final float ldy = (float) Math.sin(i * Math.PI / 180.0) * h;
GL11.glVertex2f(x + ldx, y + ldy);
}
GL11.glEnd();
GlStateManager.enableTexture2D();
GlStateManager.disableBlend();
}

public static void arcEllipse(final float x, final float y, float start, float end, final float w, final float h,
                   final Color color) {
GlStateManager.color(0.0f, 0.0f, 0.0f);
GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
float temp = 0.0f;
if (start > end) {
temp = end;
end = start;
start = temp;
}
final Tessellator var9 = Tessellator.getInstance();
var9.getWorldRenderer();
GlStateManager.enableBlend();
GlStateManager.disableTexture2D();
GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f,
 color.getAlpha() / 255.0f);
if (color.getAlpha() > 0.5f) {
GL11.glEnable(2848);
GL11.glLineWidth(2.0f);
GL11.glBegin(3);
for (float i = end; i >= start; i -= 4.0f) {
 final float ldx = (float) Math.cos(i * Math.PI / 180.0) * w * 1.001f;
 final float ldy = (float) Math.sin(i * Math.PI / 180.0) * h * 1.001f;
 GL11.glVertex2f(x + ldx, y + ldy);
}
GL11.glEnd();
GL11.glDisable(2848);
}
GL11.glBegin(6);
for (float i = end; i >= start; i -= 4.0f) {
final float ldx = (float) Math.cos(i * Math.PI / 180.0) * w;
final float ldy = (float) Math.sin(i * Math.PI / 180.0) * h;
GL11.glVertex2f(x + ldx, y + ldy);
}
GL11.glEnd();
GlStateManager.enableTexture2D();
GlStateManager.disableBlend();
}
	public static void drawFilledCircle(double x, double y, double r, int c, int id) {
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_POLYGON);
        if (id == 1) {
            GL11.glVertex2d(x, y);
            for (int i = 0; i <= 90; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        } else if (id == 2) {
            GL11.glVertex2d(x, y);
            for (int i = 90; i <= 180; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        } else if (id == 3) {
            GL11.glVertex2d(x, y);
            for (int i = 270; i <= 360; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        } else if (id == 4) {
            GL11.glVertex2d(x, y);
            for (int i = 180; i <= 270; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        } else {
            for (int i = 0; i <= 360; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                GL11.glVertex2f((float) (x - x2), (float) (y - y2));
            }
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

}