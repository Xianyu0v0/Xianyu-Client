package xyz.xianyu;

import org.lwjgl.opengl.Display;
import xyz.xianyu.Alt.AltManager;
import xyz.xianyu.module.ModuleManager;
import xyz.xianyu.util.Font.FontLoaders;

public class Client {
    public final static String name = "XianyuClient",version = "Alpha1",dev = "ImXianyu";
    private static FontLoaders fontManager;
    private static ModuleManager moduleManager;
    public static void startUp(){
        AltManager.init();
        AltManager.setupAlts();
        System.out.println("["+name+"] Starting Xianyu Client...");
        Display.setTitle("Minecraft 1.8.9 | XianyuClient");
        fontManager = new FontLoaders();
        moduleManager = new ModuleManager();
        //TODO:Add Config Manager.
    }
}