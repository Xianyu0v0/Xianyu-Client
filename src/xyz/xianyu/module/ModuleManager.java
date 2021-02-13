package xyz.xianyu.module;

import com.darkmagician6.eventapi.EventManager;
import xyz.xianyu.events.EventKey;
import xyz.xianyu.module.modules.movement.*;
import xyz.xianyu.module.modules.render.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {
    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
    public ModuleManager(){
        modules.clear();
        //Movement
        modules.add(new Sprint());
        //Render
        modules.add(new Hud());
        //Other
    }

    public static Module getModuleByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls) continue;
            return m;
        }
        return null;
    }

    /*public static void onEvent(Event e){
        for(Module m : modules){
            if(!m.toggled)
                continue;
            m.onEvent(e);
        }
    }*/

    public static List<Module> getModsByCategory(Module.Category m) {
        List<Module> findList = new ArrayList<>();
        for(Module mod:modules){
            if(mod.category == m){
                findList.add(mod);
            }
        }
        return findList;
    }
    public static List<Module> getModType(Module.Category t) {
        ArrayList<Module> output = new ArrayList<>();
        for (Module m : modules) {
            if (m.category != t)
                continue;
            output.add(m);
        }
        return output;
    }

    public static List<Module> getModulesByCategory(Module.Category c){
        List<Module> modules = new ArrayList<>();

        for(Module m : ModuleManager.modules) {
            if(m.category==c) {
                modules.add(m);
            }
        }
        return modules;
    }

    public static boolean isModuleEnabled(String module){
        for(Module m : modules){
            if(m.getEnglishName().equalsIgnoreCase(module)){
                return m.isEnabled();
            }
        }
        return false;
    }

    public static void keyPress(int key){
        //onEvent(new EventKey(key));
        EventManager.call(new EventKey(key));
        for(Module m : modules){
            if(m.getKey() == key){
                m.toggle();
            }
        }
    }
}
