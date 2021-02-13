package xyz.xianyu.settings;

public class BooleanSetting extends Setting {
    public boolean enabled;

    public BooleanSetting(String English,String Chinese,boolean enabled){
        this.English = English;
        this.Chinese = Chinese;
        this.enabled = enabled;
    }
    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public void toggle(){
        enabled = !enabled;
    }

}
