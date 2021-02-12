package xyz.xianyu.ui;

import javafx.scene.transform.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import xyz.xianyu.Alt.GuiAltManager;
import xyz.xianyu.Client;
import xyz.xianyu.util.RenderUtil;

import java.awt.*;

public class MainMenu extends GuiScreen {
    public float x;
    private Minecraft mc = Minecraft.getMinecraft();
    private int width;
    private int height;
    public static int opac=0;
    public static Translate translat;
    /*     */   private float currentX;
    private float targetX;
    /*     */   private float currentY;
    /*     */   private float targetY;
    static ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public static int y;
    public static boolean mouse;
    @Override
    public void initGui(){
        this.translat = new Translate(0.0f, -10.0f);
        RenderUtil.refreshPlayerSkinTextureCache();
    }

    @Override
    public void onGuiClosed(){
        opac=0;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        //RenderBackGround
        renderBg(mouseX,mouseY);
        //RenderClientName
        renderClientName();
        //RenderClientInformation
        renderInfo();
        //RenderMainButton
        RenderUtil.drawFastRoundedRect(sr.getScaledWidth()/2-55,sr.getScaledHeight()/2-50,sr.getScaledWidth()/2+55,sr.getScaledHeight()/2+65,1.0F,new Color(64,64,64,opac/2).getRGB());
        String[] buttons = new String[]{I18n.format("menu.singleplayer"),I18n.format("menu.multiplayer"),"AltManager",I18n.format("menu.options"),I18n.format("menu.quit")};
        y = sr.getScaledHeight()/2-28;
        for(String s : buttons){
            RenderUtil.drawFastRoundedRect(sr.getScaledWidth()/2-35,y-2,sr.getScaledWidth()/2+35,y+10,1.0f,new Color(64,64,64,opac).getRGB());
            GlStateManager.resetColor();
            mc.fontRendererObj.drawString(s,sr.getScaledWidth()/2-(mc.fontRendererObj.getStringWidth(s)/2),y,isHovered(sr.getScaledWidth()/2-35,y-2,sr.getScaledWidth()/2+35,y+10,mouseX,mouseY)?new Color(0,0,200,opac).getRGB():new Color(255,255,255,opac).getRGB());
            y+=15;
        }
        int y = sr.getScaledHeight()/2-28;
        if(Mouse.isButtonDown(0)&&!mouse){
            mouse=true;
            for(String name : buttons){
                if(isHovered(sr.getScaledWidth()/2-35,y-2,sr.getScaledWidth()/2+35,y+10,mouseX,mouseY)){
                    if (I18n.format("menu.singleplayer", new Object[0]).equals(name)) {
                        this.mc.displayGuiScreen(new GuiSelectWorld(this));
                    } else if (I18n.format("menu.multiplayer", new Object[0]).equals(name)) {
                        this.mc.displayGuiScreen(new GuiMultiplayer(this));
                    } else if ("AltManager".equals(name)) {
                        this.mc.displayGuiScreen((GuiScreen)new GuiAltManager());
                    } else if (I18n.format("menu.options", new Object[0]).equals(name)) {
                        mc.displayGuiScreen(new GuiOptions(this,mc.gameSettings));
                    }  else if (I18n.format("menu.quit", new Object[0]).equals(name)) {
                        mc.shutdown();
                    }
                }
                y+=15;
            }
        }
        if(!Mouse.isButtonDown(0)){
            mouse=false;
        }
        GlStateManager.resetColor();
    }

    public void renderClientName(){
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        if(opac<255&&opac+10<255){
            opac+=10;
        }
        mc.fontRendererObj.drawString(Client.name, sr.getScaledWidth()/2 - (mc.fontRendererObj.getStringWidth(Client.name)/2),sr.getScaledHeight()/2-85 ,new Color(255,255,255,opac).getRGB());
    }

    public void renderBg(int mouseX,int mouseY){
        ScaledResolution sr = new ScaledResolution(this.mc);
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();
        GlStateManager.pushMatrix();
        float xDiff = ((float)(mouseX - sr.getScaledWidth() / 2) - this.currentX) / (float)sr.getScaleFactor();
        float yDiff = ((float)(mouseY - sr.getScaledHeight() / 2) - this.currentY) / (float)sr.getScaleFactor();
        float slide = (float) this.translat.getY();
        this.currentX += xDiff * 0.3f;
        this.currentY += yDiff * 0.3f;
        GlStateManager.translate(this.currentX / 100.0f, this.currentY / 100.0f, 0.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("Xianyu/bg.png"));
        Gui.drawScaledCustomSizeModalRect(-10, -10, 0.0f, 0.0f, w + 20, h + 20, w + 20, h + 20, w + 20, h + 20);
        GlStateManager.bindTexture(0);
        GlStateManager.translate(-this.currentX / 100.0f, -this.currentY / 100.0f, 0.0f);
        GlStateManager.translate(this.currentX / 50.0f, this.currentY / 50.0f - slide, 0.0f);
        GlStateManager.enableBlend();
        //RenderUtil.drawImage(new ResourceLocation("SmokeAssets/ICON/Logo.png"), w / 2 - 64, h / 2 - 128, 128, 128);
        GlStateManager.disableBlend();
        GlStateManager.translate(-this.currentX / 50.0f, -this.currentY / 50.0f + slide, 0.0f);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.currentX / 15.0f, this.currentY / 15.0f, 0.0f);
        GlStateManager.translate(-this.currentX / 15.0f, -this.currentY / 15.0f, 0.0f);
        GlStateManager.popMatrix();
        GlStateManager.translate(this.currentX / 50.0f, this.currentY / 50.0f - slide, 0.0f);
        GlStateManager.popMatrix();
    }

    public void renderInfo(){
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        mc.fontRendererObj.drawString(Client.name+" "+Client.version+" With OptiFine_1.8.9_HD_U_L5",0,sr.getScaledHeight()-(mc.fontRendererObj.FONT_HEIGHT),-1);
        mc.fontRendererObj.drawString("Copyright Mojang AB. Do not distribute!", sr.getScaledWidth()-  mc.fontRendererObj.getStringWidth("Copyright Mojang AB. Do not distribute!") - 4, sr.getScaledHeight() - 10, -1);
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseX <= x2 && (float)mouseY >= y && (float)mouseY <= y2;
    }

    public void mouseClicked(int mouseX,int mouseY,int button){
        String[] buttons = new String[]{I18n.format("menu.singleplayer"),I18n.format("menu.multiplayer"),"AltManager",I18n.format("menu.options"),I18n.format("menu.quit")};
        int y = sr.getScaledHeight()/2-28;

    }

}
