package xyz.xianyu;

import com.darkmagician6.eventapi.EventManager;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import xyz.xianyu.Alt.AltManager;
import xyz.xianyu.module.ModuleManager;
import xyz.xianyu.util.Config;
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
        EventManager.register(moduleManager);
        Config.loadMods();
        Config.loadValues();
    }

    public static void onStop(){
        Config.saveValue();
        Config.saveMods();
    }

}