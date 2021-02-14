package xyz.xianyu.module;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import xyz.xianyu.module.modules.render.Hud;
import xyz.xianyu.settings.KeybindSetting;
import xyz.xianyu.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Module {

    public String English;
    public String Chinese;
    public String suffix;
    public boolean toggled;
    public KeybindSetting keyCode = new KeybindSetting(0);
    public Category category;
    public static Minecraft mc = Minecraft.getMinecraft();

    public boolean expanded;
    public int index;

    public List<Setting> settings = new ArrayList<Setting>();
    protected boolean isEnabled;

    public Module(String English,String Chinese,int key,Category c)
    {
        this.English = English;
        this.Chinese = Chinese;
        keyCode.code = key;
        this.category = c;
        this.suffix="";
        this.addSettings(keyCode);
    }

    public void setSuffix(String suffix){
        this.suffix=suffix;
    }

    public void addSettings(Setting... settings){
        this.settings.addAll(Arrays.asList(settings));
        this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
    }

    public boolean isEnabled(){
        return toggled;
    }

    public String getName(){
        if(Hud.Language.getMode().equals("Chinese")){
            return Chinese;
        }
        if(Hud.Language.getMode().equals("English")){
            return English;
        }
        return "";
    }

    public String getSuffix(){
        if(!suffix.equals("")) {
            return EnumChatFormatting.GRAY + this.suffix;
        }
        return "";
    }

    public String getNameAndSuffix(){
        if(!getSuffix().equals("")) {
            return getName() + "  " + getSuffix();
        }
        return getName();
    }


    public void onToggle() {

    }

    public String getEnglishName() {
        return this.English;
    }

    public String getChineseName() {
        return this.Chinese;
    }

    public int getKey()
    {
        return keyCode.code;
    }

    public void toggle(){
        toggled = !toggled;
        if(toggled){
            if(mc.thePlayer!=null){
                mc.thePlayer.playSound("random.click",1.0f,0.6f);
            }
            onEnable();
            EventManager.register(this);
        }else{
            if(mc.thePlayer!=null) {
                mc.thePlayer.playSound("random.click", 1.0f, 0.5f);
            }
            onDisable();
            EventManager.unregister(this);
        }
    }

    public void onEnable(){
    }

    public void onDisable()
    {
    }

    public enum Category{
        MOVEMENT("Movement","移动"),
        RENDER("Render","视觉");


        public String English;
        public String Chinese;
        public int moduleIndex;

        Category(String English,String Chinese){
            this.English=English;
            this.Chinese = Chinese;
        }
    }

}
