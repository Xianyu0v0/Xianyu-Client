package xyz.xianyu;

import org.lwjgl.opengl.Display;
import xyz.xianyu.Alt.AltManager;

public class Client {
    public final static String name = "XianyuClient",version = "Alpha1",dev = "ImXianyu";
    public static void startUp(){
        AltManager.init();
        AltManager.setupAlts();
        System.out.println("["+name+"] Starting Xianyu Client...");
        Display.setTitle("Minecraft 1.8.9 | XianyuClient");
    }
}
