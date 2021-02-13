package xyz.xianyu.ui.ClickGui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import xyz.xianyu.Client;
import xyz.xianyu.module.Module;
import xyz.xianyu.module.ModuleManager;
import xyz.xianyu.module.modules.render.Hud;
import xyz.xianyu.settings.*;
import xyz.xianyu.util.Font.CFontRenderer;
import xyz.xianyu.util.Font.FontLoaders;
import xyz.xianyu.util.RenderUtil;

public class ClickUI extends GuiScreen implements GuiYesNoCallback {
    public static Module.Category currentModuleType = Module.Category.RENDER;
    public static Module currentModule = ModuleManager.getModsByCategory(currentModuleType).size() != 0
            ? ModuleManager.getModsByCategory(currentModuleType).get(0)
            : null;
    public static float startX = 100, startY = 100;
    public int moduleStart = 0;
    public static boolean Bindhovered=false;
    public int valueStart = 0;
    boolean previousmouse = true;
    boolean previousmouseRight = true;
    boolean mouse;
    public int opacityx = 255;
    public float moveX = 0, moveY = 0;
    public CFontRenderer LogoFont=new CFontRenderer(FontLoaders.getRobotoLight(35,true),true,true);
    public String key;
    public int keycode;
    public boolean hovered=false;
    public int backcounter=0;

    TimeHelper AnimationTimer = new TimeHelper();

    int finheight;
    int animheight = 0;


    @Override
    protected void keyTyped(char typedChar, int k) throws IOException {
        if(k!= Keyboard.KEY_CAPITAL&&k!=Keyboard.KEY_BACK&&k!=Keyboard.KEY_LMENU&&k!=Keyboard.KEY_ESCAPE&&k!=Keyboard.KEY_LCONTROL&&k!=Keyboard.KEY_RCONTROL&&k!=Keyboard.KEY_RMENU&&k!=Keyboard.KEY_RMETA&&k!=Keyboard.KEY_APPS
                &&k!=Keyboard.KEY_LSHIFT&&k!=Keyboard.KEY_RSHIFT&&k!=Keyboard.KEY_UP&&k!=Keyboard.KEY_LEFT&&k!=Keyboard.KEY_DOWN&&k!=Keyboard.KEY_RIGHT&&k!=Keyboard.KEY_INSERT&&k!=Keyboard.KEY_HOME&&k!=Keyboard.KEY_PRIOR&&k!=Keyboard.KEY_NEXT
                &&k!=Keyboard.KEY_END&&k!=Keyboard.KEY_TAB&&k!=Keyboard.KEY_F1&&k!=Keyboard.KEY_F2&&k!=Keyboard.KEY_F3&&k!=Keyboard.KEY_F4&&k!=Keyboard.KEY_F5&&k!=Keyboard.KEY_F6&&k!=Keyboard.KEY_F7&&k!=Keyboard.KEY_F8&&k!=Keyboard.KEY_F9&&k!=Keyboard.KEY_F10&&k!=Keyboard.KEY_F11&&k!=Keyboard.KEY_F12
                &&k!=Keyboard.KEY_SYSRQ&&k!=Keyboard.KEY_SCROLL&&k!=Keyboard.KEY_PAUSE&&k!=Keyboard.KEY_NUMLOCK&&k!=Keyboard.KEY_RETURN&&k!=Keyboard.KEY_DELETE&&k!=Keyboard.KEY_LWIN&&k!=Keyboard.KEY_RWIN) {

            key = String.valueOf(typedChar);
        }
        keycode=k;
        super.keyTyped(typedChar, k);
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (isHovered(startX-40, startY, startX + 320, startY + 25, mouseX, mouseY) && Mouse.isButtonDown(0)) {
            if (moveX == 0 && moveY == 0) {
                moveX = mouseX - startX;
                moveY = mouseY - startY;
            } else {
                startX = mouseX - moveX;
                startY = mouseY - moveY;
            }
            this.previousmouse = true;
        } else if (moveX != 0 || moveY != 0) {
            moveX = 0;
            moveY = 0;
        }
        RenderUtil.drawRect(startX-40, startY, startX + 60, startY + 245,
                new Color(255, 255, 255, 255).getRGB());
        RenderUtil.drawRect(startX + 60, startY, startX + 200, startY + 245,
                new Color(246, 246, 246, 255).getRGB());
        RenderUtil.drawRect(startX + 200, startY, startX + 320, startY + 245,
                new Color(255, 255, 255, 255).getRGB());
        LogoFont.drawString(Client.name,startX-30,startY+10,new Color(0,134,255, 255).getRGB());
        FontLoaders.Robotolight16.drawString(Client.version,startX+30,startY+27,new Color(139,139,139, 255).getRGB());
        for (int i = 0; i < Module.Category.values().length; i++) {
            Module.Category[] iterator = Module.Category.values();
            if (iterator[i] == currentModuleType) {
                finheight = i*40;
                //RenderUtil.drawGradientSideways(startX-40,startY+50+animheight,startX+60,startY+75+animheight,new Color(0,80,255, 255).getRGB(),new Color(0,150,255, 255).getRGB());
                RenderUtil.drawGradientSideways(startX-40,startY+50+animheight,startX+60,startY+75+animheight,new Color(196,196,196).getRGB(),new Color(196,196,196).getRGB());
                if(animheight<finheight){
                    if(finheight - animheight<40) {
                        animheight+=2;
                    }else{
                        animheight+=4;
                    }
                }else if(animheight>finheight){
                    if(animheight - finheight<40) {
                        animheight-=2;
                    }else{
                        animheight-=4;
                    }
                }
                if(animheight==finheight){
                    if(Hud.Language.getMode().equals("English")){
                        FontLoaders.RobotoBold20.drawString(iterator[i].English,startX-10,startY+60+i*40,new Color(255,255,255).getRGB());
                    }else{
                        mc.fontRendererObj.drawString(iterator[i].Chinese,(int)startX-10,(int)startY+60+i*40,new Color(255,255,255).getRGB());
                    }
                }else{
                    if(Hud.Language.getMode().equals("English")){
                        FontLoaders.RobotoBold20.drawString(iterator[i].English,startX-10,startY+60+i*40,new Color(196,196,196).getRGB());
                    }else{
                        mc.fontRendererObj.drawString(iterator[i].Chinese,(int)startX-10,(int)startY+60+i*40,new Color(196,196,196).getRGB());
                    }
                }
            }else{
                if(Hud.Language.getMode().equals("English")) {
                    FontLoaders.RobotoBold20.drawString(iterator[i].English, startX - 10, startY + 60 + i * 40, new Color(196, 196, 196).getRGB());
                }else{
                    mc.fontRendererObj.drawString(iterator[i].Chinese, (int)startX - 10, (int)startY + 60 + i * 40, new Color(196, 196, 196).getRGB());
                }
            }
            try {
                if (this.isCategoryHovered(startX - 40, startY + 50 + i * 40, startX + 60, startY + 75 + i * 40, mouseX,
                        mouseY) && Mouse.isButtonDown((int) 0)) {
                    currentModuleType = iterator[i];
                    currentModule = ModuleManager.getModsByCategory(currentModuleType).size() != 0
                            ? ModuleManager.getModsByCategory(currentModuleType).get(0)
                            : null;
                    moduleStart = 0;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        int m = Mouse.getDWheel();
        if (this.isCategoryHovered(startX + 60, startY, startX + 200, startY + 235, mouseX, mouseY)) {
            if (m < 0 && moduleStart < ModuleManager.getModsByCategory(currentModuleType).size() - 1) {
                moduleStart++;
            }
            if (m > 0 && moduleStart > 0) {
                moduleStart--;
            }
        }
        if (this.isCategoryHovered(startX + 200, startY, startX + 420, startY + 235, mouseX, mouseY)) {
            if (m < 0 && valueStart < currentModule.settings.size() - 1) {
                valueStart++;
            }
            if (m > 0 && valueStart > 0) {
                valueStart--;
            }
        }
        if(Hud.Language.getMode().equals("English")) {
            FontLoaders.kiona16.drawString(currentModule == null ? currentModuleType.English : currentModuleType.English + "/" + currentModule.getEnglishName(), startX + 70, startY + 15, new Color(0, 0, 0).getRGB());
        }else{
            mc.fontRendererObj.drawString(
                    currentModule == null ? currentModuleType.Chinese
                            : currentModuleType.Chinese + "/" + currentModule.getChineseName(),
                    (int)startX + 70, (int)startY + 15, new Color(0, 0, 0).getRGB());
        }

        if (currentModule != null) {
            float mY = startY + 30;
            for (int i = 0; i < ModuleManager.getModsByCategory(currentModuleType).size(); i++) {
                Module module = ModuleManager.getModsByCategory(currentModuleType).get(i);
                if (mY > startY + 220)
                    break;
                if (i < moduleStart) {
                    continue;
                }
                if(Hud.Language.getMode().equals("English")) {
                    FontLoaders.Robotolight18.drawString(module.getEnglishName(), startX + 90, mY + 5,
                            new Color(107, 107, 107, 255).getRGB(), false);
                }else{
                    mc.fontRendererObj.drawString(module.getChineseName(), startX + 90, mY + 5,
                            new Color(107, 107, 107, 255).getRGB(), false);
                }

                if (!module.isEnabled()) {
                    RenderUtil.circle(startX+75,mY+10,2,new Color(174,174,174).getRGB());

                } else {
                    RenderUtil.circle(startX + 75, mY + 10, 2,
                            new Color(0, 255, 0, 255).getRGB());
                }
                if (isSettingsButtonHovered(startX + 75, mY,
                        startX + 100 + (FontLoaders.Robotolight18.getStringWidth(module.getEnglishName())),
                        mY + 8 + FontLoaders.Robotolight18.getStringHeight(""), mouseX, mouseY)) {
                    if (!this.previousmouse && Mouse.isButtonDown((int) 0)) {
                        module.toggle();
                        previousmouse = true;
                    }
                    if (!this.previousmouse && Mouse.isButtonDown((int) 1)) {
                        previousmouse = true;
                    }
                }

                if (!Mouse.isButtonDown((int) 0)) {
                    this.previousmouse = false;
                }
                if (isSettingsButtonHovered(startX + 90, mY,
                        startX + 100 + (FontLoaders.kiona20.getStringWidth(module.getEnglishName())),
                        mY + 8 + FontLoaders.kiona20.getStringHeight(""), mouseX, mouseY) && Mouse.isButtonDown((int) 1)) {
                    currentModule = module;
                    valueStart = 0;
                }
                mY += 25;
            }
            mY = startY + 30;
            if(currentModule.settings.size()<1){
                RenderUtil.drawRect(0,0,0,0,-1);
                FontLoaders.RobotoBold20.drawString("NoSettingsHere",startX+210,startY+10,new Color(178,178,178).getRGB());

            }
            for (int i = 0; i < currentModule.settings.size(); i++) {
                if (mY > startY + 220)
                    break;
                if (i < valueStart) {
                    continue;
                }
                xyz.xianyu.util.Font.CFontRenderer font = FontLoaders.Robotolight16;
                Setting value = currentModule.settings.get(i);

                if (value instanceof NumberSetting) {
                    float x = startX + 220;
                    double render = 68.0f * (((Number)((NumberSetting) value).getValue()).floatValue() - ((Number)((NumberSetting)value).getMinimum()).floatValue()) / (((Number)((NumberSetting)value).getMaximum()).floatValue() - ((Number)((NumberSetting)value).getMinimum()).floatValue());
                    RenderUtil.drawRect((float) x - 11, mY + 7, (float) ((double) x + 70), mY + 8,
                            (new Color(213, 213, 213, 255)).getRGB());
                    RenderUtil.drawRect((float) x - 11, mY + 7, (float) ((double) x + render + 0.5D), mY + 8,
                            (new Color(88, 182, 255, 255)).getRGB());
                    RenderUtil.circle((float) (x + render + 2D), mY+7.5f,3, new Color(0, 144, 255).getRGB());
                    if(Hud.Language.getMode().equals("English")) {
                        font.drawString(value.English + ": " + ((NumberSetting) value).getValue(), startX + 210, mY - 3, new Color(136, 136, 136).getRGB());
                    }else{
                        mc.fontRendererObj.drawString(value.Chinese + ": " + ((NumberSetting) value).getValue(), (int)startX + 210, (int)mY - 3, new Color(136, 136, 136).getRGB());
                    }
                    if (!Mouse.isButtonDown((int) 0)) {
                        this.previousmouse = false;
                    }
                    if (this.isButtonHovered(x, mY - 4, x + 100, mY + 9, mouseX, mouseY)
                            && Mouse.isButtonDown((int) 0)) {
                        if (!this.previousmouse && Mouse.isButtonDown((int) 0)) {
                            render = ((NumberSetting) value).getMinimum();
                            double max = ((NumberSetting) value).getMaximum();
                            double inc = ((NumberSetting) value).getIncrement();
                            double valAbs = (double) mouseX - ((double) x + 1.0D);
                            double perc = valAbs / 68.0D;
                            perc = Math.min(Math.max(0.0D, perc), 1.0D);
                            double valRel = (max - render) * perc;
                            double val = render + valRel;
                            val = (double) Math.round(val * (1.0D / inc)) / (1.0D / inc);
                            ((NumberSetting) value).setValue(Double.valueOf(val));
                        }
                        if (!Mouse.isButtonDown((int) 0)) {
                            this.previousmouse = false;
                        }
                    }
                    mY += 30;
                }
                if (value instanceof BooleanSetting) {
                    float x = startX + 220;
                    if(Hud.Language.getMode().equals("English")) {
                        font.drawString(value.English, startX + 210, mY, new Color(136, 136, 136).getRGB());
                    }else{
                        mc.fontRendererObj.drawString(value.Chinese, (int)startX + 210, (int)mY, new Color(136, 136, 136).getRGB());
                    }
                    RenderUtil.drawRect(1,1,1,1,new Color(136,136,136).getRGB());
                    RenderUtil.drawRect(1,1,1,1,-1);
                    if ((boolean) ((BooleanSetting) value).isEnabled()) {
                        RenderUtil.drawImage1(new ResourceLocation("client/icons/option/True.png"),x + 60, mY-1,11,11);
                    } else {
                        RenderUtil.drawImage1(new ResourceLocation("client/icons/option/False.png"),x + 60, mY-1,11,11);
                    }
                    RenderUtil.drawRect(1,1,1,1,new Color(136,136,136).getRGB());
                    if (this.isCheckBoxHovered(x + 55, mY, x + 76, mY + 9, mouseX, mouseY)) {
                        if (!this.previousmouse && Mouse.isButtonDown((int) 0)) {
                            mc.thePlayer.playSound("random.click",1,1);
                            this.previousmouse = true;
                            this.mouse = true;
                        }

                        if (this.mouse) {
                            ((BooleanSetting) value).toggle();
                            this.mouse = false;
                        }
                    }
                    if (!Mouse.isButtonDown((int) 0)) {
                        this.previousmouse = false;
                    }
                    mY += 30;
                }
                if (value instanceof ModeSetting) {
                    RenderUtil.drawRect(1,1,1,1,-1);
                    float x = startX + 220;
                    if(Hud.Language.getMode().equals("English")) {
                        font.drawString(value.English, startX + 210, mY - 5, new Color(136, 136, 136).getRGB());
                    }else{
                        mc.fontRendererObj.drawString(value.Chinese, (int)startX + 210, (int)mY - 5, new Color(136, 136, 136).getRGB());
                    }
                    RenderUtil.drawRect(x-10, mY+6, x + 75, mY + 22,
                            new Color(217, 217, 217, 255).getRGB());
                    if(Hud.Language.getMode().equals("English")) {
                        FontLoaders.Robotolight18.drawString(((ModeSetting) value).getMode(),
                                (float) (x + 30 - font.getStringWidth(((ModeSetting) value).getMode()) / 2), mY + 10, Color.blue.getRGB());
                    }else{
                        mc.fontRendererObj.drawString(((ModeSetting) value).getMode(), (int) (x + 30 - font.getStringWidth(((ModeSetting) value).getMode()) / 2), (int)mY + 10, Color.blue.getRGB());
                    }
                    if (this.isStringHovered(x-10, mY + 6, x + 75, mY + 22, mouseX, mouseY)) {
                        if (Mouse.isButtonDown((int) 0) && !this.previousmouse) {
                            mc.thePlayer.playSound("random.click",1,1);
                            ((ModeSetting)value).cycle();
                            this.previousmouse = true;
                        }
                        if (!Mouse.isButtonDown((int) 0)) {
                            this.previousmouse = false;
                        }

                        if (Mouse.isButtonDown((int) 1) && !this.previousmouseRight) {
                            mc.thePlayer.playSound("random.click",1,1);
                            ((ModeSetting)value).backcycle();
                            this.previousmouseRight = true;
                        }
                        if (!Mouse.isButtonDown((int) 1)) {
                            this.previousmouseRight = false;
                        }

                    }
                    mY += 30;
                }
                if(value instanceof KeybindSetting){
                    float x = startX + 220.0f;
                    if (Hud.Language.getMode().equals("English")) {
                        FontLoaders.GoogleSans18.drawString(value.English+":", startX + 210.0f, mY + 2.0f, new Color(136, 136, 136).getRGB());
                        FontLoaders.GoogleSans18.drawString(Keyboard.getKeyName(((KeybindSetting) value).getKeyCode()), x + 44.0f - (float) (FontLoaders.GoogleSans18.getStringWidth(Keyboard.getKeyName(((KeybindSetting) value).getKeyCode())) / 2), mY + 2.0f, new Color(136, 136, 136).getRGB());
                    }
                    if (Hud.Language.getMode().equals("Chinese")) {
                        mc.fontRendererObj.drawString(value.Chinese+":", startX + 210.0f, mY + 2.0f, new Color(136, 136, 136).getRGB());
                        mc.fontRendererObj.drawString(Keyboard.getKeyName(((KeybindSetting) value).getKeyCode()), x + 44.0f - (float) (mc.fontRendererObj.getStringWidth(Keyboard.getKeyName(((KeybindSetting) value).getKeyCode())) / 2), mY + 2.0f, new Color(136, 136, 136).getRGB());
                    }
                    boolean hovered = isSettingsButtonHovered(x + 20,mY,x+90,mY+10,mouseX,mouseY);
                    if(hovered&&Mouse.isButtonDown(0)){
                        Bindhovered=true;
                    }
                    if(hovered&&Mouse.isButtonDown(1)){
                        ((KeybindSetting) value).setKeyCode(0);
                    }
                    if(!hovered&&Mouse.isButtonDown(0)){
                        Bindhovered=false;
                    }
                    if(Bindhovered){
                        if(keycode!=0){
                            ((KeybindSetting) value).setKeyCode(keycode);
                            keycode = 0;
                            Bindhovered=false;
                        }
                    }
                    if(!Bindhovered){
                        if(keycode!=0){
                            keycode=0;
                        }
                    }
                    mY +=30;
                }
            }
        }

    }

    public boolean isStringHovered(float f, float y, float g, float y2, int mouseX, int mouseY) {
        if (mouseX >= f && mouseX <= g && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

    public boolean isSettingsButtonHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

    public boolean isButtonHovered(float f, float y, float g, float y2, int mouseX, int mouseY) {
        if (mouseX >= f && mouseX <= g && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

    public boolean isCheckBoxHovered(float f, float y, float g, float y2, int mouseX, int mouseY) {
        if (mouseX >= f && mouseX <= g && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

    public boolean isCategoryHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

    public class TimeHelper {
        private long lastMs;

        public boolean isDelayComplete(long delay) {
            if (System.currentTimeMillis() - this.lastMs > delay) {
                return true;
            }
            return false;
        }

        public void reset() {
            this.lastMs = System.currentTimeMillis();
        }

        public long getLastMs() {
            return this.lastMs;
        }

        public void setLastMs(int i) {
            this.lastMs = System.currentTimeMillis() + (long)i;
        }




    }


}
