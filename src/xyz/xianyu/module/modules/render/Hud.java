package xyz.xianyu.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import xyz.xianyu.events.EventRender2D;
import xyz.xianyu.module.Module;
import xyz.xianyu.module.ModuleManager;
import xyz.xianyu.settings.BooleanSetting;
import xyz.xianyu.settings.ModeSetting;
import xyz.xianyu.util.ColorUtil;
import xyz.xianyu.util.Font.FontLoaders;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Hud extends Module {
    public Hud(){
        super("Hud","抬头显视",0,Category.RENDER);
        addSettings(Language,ArrayListMode,ArrayList,RGB,Armor,FastChat,RealBobbing);
    }

    public static ModeSetting Language = new ModeSetting("Language","语言","English","English","Chinese");
    public static ModeSetting ArrayListMode = new ModeSetting("ArrayListMode","启用的模组模式","Normal","Normal","Flux","Outline");
    public static BooleanSetting ArrayList=new BooleanSetting("ArrayList","显示启用的模组",true);
    public static BooleanSetting RGB = new BooleanSetting("RGB","颜色彩虹",true);
    public static BooleanSetting Armor = new BooleanSetting("ShowArmor","渲染盔甲",true);
    public static BooleanSetting FastChat = new BooleanSetting("FastChat","聊天栏无背景",false);
    public static BooleanSetting RealBobbing = new BooleanSetting("RealBobbing","真实视角摇晃",false);
    @EventTarget
    public void onRender(EventRender2D e){
        if(ArrayList.isEnabled()){
            FontRenderer fr = mc.fontRendererObj;
            ModuleManager.modules.sort(Comparator.comparingInt(m -> FontLoaders.Comfortaa18.getStringWidth(((Module)m).getNameAndSuffix())).reversed());
            int count = 0;
            for(Module m : ModuleManager.modules){
                if(!m.toggled) {
                    continue;
                }
                float offset = count*(fr.FONT_HEIGHT+6);
                if(Language.getMode().equals("Chinese")) {
                    if(ArrayListMode.getMode().equals("Normal")) {
                        Gui.drawRect(ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix()) - 10,offset,ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix()) - 8,6+ fr.FONT_HEIGHT+offset, RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        Gui.drawRect(ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix()) - 8,offset,ScaledResolution.getScaledWidth(),6+ fr.FONT_HEIGHT+offset,0x90000000);
                        fr.drawStringWithShadow(m.getNameAndSuffix(),ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix())-4,4+offset,-1);
                    }
                    if(ArrayListMode.getMode().equals("Flux")) {
                        Gui.drawRect(ScaledResolution.getScaledWidth(),count*(fr.FONT_HEIGHT+3),ScaledResolution.getScaledWidth() - 2,6+ fr.FONT_HEIGHT+count*(fr.FONT_HEIGHT+3), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        if(Language.getMode().equals("Chinese")){
                            mc.fontRendererObj.drawStringWithShadow(m.getNameAndSuffix(), ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix()) - 4, 4 + count * (fr.FONT_HEIGHT + 3), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150) : -1);
                        }else{
                            if(!m.getSuffix().equals("")){
                                FontLoaders.Comfortaa15.drawStringWithShadow(m.getNameAndSuffix(), ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix()) - 4, 4 + count * (fr.FONT_HEIGHT + 3), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150) : -1);
                            }else{
                                FontLoaders.Comfortaa15.drawStringWithShadow(m.getNameAndSuffix(), ScaledResolution.getScaledWidth() - fr.getStringWidth(m.getNameAndSuffix()) - 4, 4 + count * (fr.FONT_HEIGHT + 3), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150) : -1);
                            }
                        }
                    }
                    if(ArrayListMode.getMode().equals("Outline")){

                        Gui.drawRect(ScaledResolution.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getNameAndSuffix()) - 9,count*(mc.fontRendererObj.FONT_HEIGHT+4)-1,ScaledResolution.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getNameAndSuffix()) - 8,11+count*(mc.fontRendererObj.FONT_HEIGHT+4), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        Gui.drawRect(ScaledResolution.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getNameAndSuffix()) - 9,11+count*(mc.fontRendererObj.FONT_HEIGHT+4),ScaledResolution.getScaledWidth(),13+count*(mc.fontRendererObj.FONT_HEIGHT+4), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        Gui.drawRect(ScaledResolution.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getNameAndSuffix()) - 8,count*(mc.fontRendererObj.FONT_HEIGHT+4)-1,ScaledResolution.getScaledWidth(),12+count*(mc.fontRendererObj.FONT_HEIGHT+4),new Color(64,64,64).getRGB());
                        mc.fontRendererObj.drawStringWithShadow(m.getNameAndSuffix(),ScaledResolution.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getNameAndSuffix())-4,2+count*(mc.fontRendererObj.FONT_HEIGHT+4),ColorUtil.getRainbow(4,0.8f,1,count * 150));
                    }
                }else {
                    if(ArrayListMode.getMode().equals("Normal")) {
                        Gui.drawRect(ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 10,offset,ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 8,6+ FontLoaders.Comfortaa18.getHeight()+offset+3, RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        Gui.drawRect(ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 8,offset,ScaledResolution.getScaledWidth(),6+ FontLoaders.Comfortaa18.getHeight()+offset+3,0x90000000);
                        FontLoaders.Comfortaa18.drawStringWithShadow(Language.getMode().equals("Chinese") ? m.getNameAndSuffix(): m.getNameAndSuffix(),ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(Language.getMode().equals("Chinese") ? m.getNameAndSuffix(): m.getNameAndSuffix())-4,4+offset,-1);
                    }
                    if(ArrayListMode.getMode().equals("Flux")) {
                        //Gui.drawRect(ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.English) - 5,offset,ScaledResolution.getScaledWidth(),6+offset,0x90000000);
                        Gui.drawRect(ScaledResolution.getScaledWidth(),count*(FontLoaders.Comfortaa18.getHeight()+3),ScaledResolution.getScaledWidth() - 2,6+ FontLoaders.Comfortaa18.getHeight()+count*(FontLoaders.Comfortaa18.getHeight()+3), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        FontLoaders.Comfortaa18.drawStringWithShadow(Language.getMode().equals("Chinese") ? m.getNameAndSuffix(): m.getNameAndSuffix(),ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(Language.getMode().equals("Chinese") ? m.getNameAndSuffix(): m.getNameAndSuffix())-4,4+count*(FontLoaders.Comfortaa18.getHeight()+3), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):-1);
                    }
                    if(ArrayListMode.getMode().equals("Outline")) {
                        Gui.drawRect(ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 8,count*(FontLoaders.Comfortaa18.getHeight()+4)+1,ScaledResolution.getScaledWidth(),11+count*(FontLoaders.Comfortaa18.getHeight()+4),new Color(64,64,64).getRGB());
                        FontLoaders.Comfortaa18.drawStringWithShadow(Language.getMode().equals("Chinese") ? m.getNameAndSuffix(): m.getNameAndSuffix(),ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(Language.getMode().equals("Chinese") ? m.getNameAndSuffix(): m.getNameAndSuffix())-4,2+count*(FontLoaders.Comfortaa18.getHeight()+4),ColorUtil.getRainbow(4,0.8f,1,count * 150));
                        Gui.drawRect(ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 9,count*(FontLoaders.Comfortaa18.getHeight()+4)+1,ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 8,11+count*(FontLoaders.Comfortaa18.getHeight()+4), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                        Gui.drawRect(ScaledResolution.getScaledWidth() - FontLoaders.Comfortaa18.getStringWidth(m.getNameAndSuffix()) - 9,11+count*(FontLoaders.Comfortaa18.getHeight()+4),ScaledResolution.getScaledWidth(),12+count*(FontLoaders.Comfortaa18.getHeight()+4), RGB.isEnabled() ? ColorUtil.getRainbow(4,0.8f,1,count * 150):0xff0090ff);
                    }
                }
                count++;
            }
        }
        if(Armor.isEnabled()){
            drawArmor((e).getSR());
        }
    }

    private void drawArmor(ScaledResolution sr) {
        if (mc.thePlayer.capabilities.isCreativeMode) {
            return;
        }
        GL11.glPushMatrix();
        int divide = 0;
        RenderItem ir = new RenderItem(mc.getTextureManager(), mc.modelManager);
        java.util.ArrayList<ItemStack> stuff = new ArrayList<ItemStack>();
        int split = 15;
        for (int index = 3; index >= 0; --index) {
            ItemStack armor = mc.thePlayer.inventory.armorInventory[index];
            if (armor == null) continue;
            stuff.add(armor);
        }
        for (ItemStack everything : stuff) {
            boolean half = ++divide > 2;
            int x = half ? sr.getScaledWidth() / 2 + 93 : sr.getScaledWidth() / 2 - 110 - 16;
            int y = split + sr.getScaledHeight() - (half ? 76 : 48) - (mc.ingameGUI.getChatGUI().getChatOpen() ? 12 : 0);
            if (mc.theWorld != null) {
                RenderHelper.disableStandardItemLighting();
                ir.renderItemIntoGUI(everything, x, y);
                ir.renderItemOverlays(mc.fontRendererObj, everything, x, y);
                RenderHelper.enableGUIStandardItemLighting();
                split += 15;
            }
            int damage = everything.getMaxDamage() - everything.getItemDamage();
            GlStateManager.enableAlpha();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.disableLighting();
            GlStateManager.clear(256);
            mc.fontRendererObj.drawStringWithShadow(String.valueOf(damage), x + (half ? 18 : -18), y + 5, -1);
        }
        GL11.glPopMatrix();
    }
}
