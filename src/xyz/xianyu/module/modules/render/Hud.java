package xyz.xianyu.module.modules.render;

import xyz.xianyu.module.Module;
import xyz.xianyu.settings.ModeSetting;

public class Hud extends Module {
    public Hud(){
        super("Hud","抬头显视",0,Category.RENDER);
        addSettings(Language);
    }

    public static ModeSetting Language = new ModeSetting("Language","语言","English","English","Chinese");

}
