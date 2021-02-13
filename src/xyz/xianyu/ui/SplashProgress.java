package xyz.xianyu.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import xyz.xianyu.util.Font.StartProgressUnicodeFontRenderer;

import java.awt.*;

public class SplashProgress {
    private static final int MAX = 6;
    private static int PROGRESS=0;
    private static String CURRENT = "";
    private static ResourceLocation splash;
    private static StartProgressUnicodeFontRenderer ufr;

    public static void update(){
        if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager()==null){
            return;
        }
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void setProgress(int givenProgress,String givenText){
        PROGRESS=givenProgress;
        CURRENT=givenText;
        update();
    }

    public static void drawSplash(TextureManager tm){
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledResolution.getScaleFactor();


        Framebuffer framebuffer= new Framebuffer(scaledResolution.getScaledWidth()*scaleFactor,scaledResolution.getScaledHeight()*scaleFactor,true);
        framebuffer.bindFramebuffer(false);


        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0,(double)scaledResolution.getScaledWidth_double(),scaledResolution.getScaledHeight_double(),0.0,1000.0D,3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0f,0.0f,-2000f);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if(splash==null){
            splash = new ResourceLocation("Xianyu/bg.png");
        }

        tm.bindTexture(splash);

        GlStateManager.resetColor();
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);

        Gui.drawScaledCustomSizeModalRect(0,0,0,0,1920,1080,ScaledResolution.getScaledWidth(),ScaledResolution.getScaledHeight(),1920,1080);
        drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(ScaledResolution.getScaledWidth()*scaleFactor,ScaledResolution.getScaledHeight()*scaleFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516,0.1f);

        Minecraft.getMinecraft().updateDisplay();

    }

    private static void drawProgress(){
        if(Minecraft.getMinecraft().gameSettings==null||Minecraft.getMinecraft().getTextureManager()==null){
            return;
        }
        if(ufr==null){
            ufr = StartProgressUnicodeFontRenderer.getFontOnPC("微软雅黑",20);
        }

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        double nProgress = (double)PROGRESS;
        double calc = (nProgress/MAX) * ScaledResolution.getScaledWidth();

        Gui.drawRect(0,ScaledResolution.getScaledHeight()-35,ScaledResolution.getScaledWidth(),ScaledResolution.getScaledHeight(),new Color(0,0,0,50).getRGB());

        GlStateManager.resetColor();
        resetTextureState();

        ufr.drawString(CURRENT,20,ScaledResolution.getScaledHeight()-25,0xFFFFFFFF);

        String step = PROGRESS + "/" + MAX;
        ufr.drawString(step,ScaledResolution.getScaledWidth()-20-ufr.getStringWidth(step),ScaledResolution.getScaledHeight()-25,0xe1e1e1FF);

        GlStateManager.resetColor();
        resetTextureState();
        Gui.drawRect(0,ScaledResolution.getScaledHeight()-2,calc,ScaledResolution.getScaledHeight(),new Color(149,201,144).getRGB());

        Gui.drawRect(0,ScaledResolution.getScaledHeight()-2,ScaledResolution.getScaledWidth(),ScaledResolution.getScaledHeight(),new Color(0,0,0,10).getRGB());
    }

    private static void resetTextureState(){
        GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
    }
}
