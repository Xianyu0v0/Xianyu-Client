package xyz.xianyu.util;

import net.minecraft.client.Minecraft;
import xyz.xianyu.Client;
import xyz.xianyu.module.Module;
import xyz.xianyu.module.ModuleManager;
import xyz.xianyu.settings.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Config {
    public static File dir;
    public static int currentTab;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final String fileDir= mc.mcDataDir + "/" + Client.name;
    public static File fileFolder = new File(fileDir);

    public Config() {
        File fileFolder = new File(fileDir);
        if(!fileFolder.exists()) {
            fileFolder.mkdir();
        }
    }

    public static void saveMods(){
        File f = new File(fileDir + "/mods.txt");
        if(!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
            PrintWriter e = new PrintWriter(f);
            for(Module m : ModuleManager.modules) {
                if(m.isEnabled()) {
                    e.print(m.English + "\n");
                }
                if(!m.isEnabled()) {
                    if(m.English.equals("TabGUI")) {
                        e.print(m.English + "\n");
                    }
                    if(m.English.equals("Watermark")) {
                        e.print(m.English + "\n");
                    }
                }
            }
            e.close();
        }catch(Exception ignored) {
        }
    }

    public static void saveValue(){
        File f = new File(fileDir + "/values.txt");
        if(!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
            PrintWriter e = new PrintWriter(f);
            for(Module m : ModuleManager.modules) {
                for (Setting setting : m.settings) {
                    if(setting instanceof BooleanSetting ) {
                        BooleanSetting bool = (BooleanSetting) setting;
                        e.print(m.English+":"+setting.English+":"+bool.isEnabled()+"\n");
                    }
                    if(setting instanceof NumberSetting ) {
                        NumberSetting number = (NumberSetting) setting;
                        e.print(m.English+":"+setting.English+":"+ number.getValue()+"\n");
                    }
                    if(setting instanceof ModeSetting ) {
                        ModeSetting mode = (ModeSetting) setting;
                        e.print(m.English+":"+setting.English+":"+mode.getMode()+"\n");
                    }
                    if(setting instanceof KeybindSetting){
                        KeybindSetting key = (KeybindSetting) setting;
                        e.print(m.English+":"+setting.English+":"+key.code+"\n");
                    }
                }
            }
            e.close();
        }catch(Exception ignored) {
        }
    }
    public static void loadValues() {
        File f = new File(fileDir + "/values.txt");
        if(!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                Scanner s = new Scanner(f);
                ArrayList<String> list = new ArrayList<>();
                while (s.hasNext()){
                    list.add(s.next());
                }
                if(list.size() != 0) {
                    for (String value : list) {
                        for (Module m : ModuleManager.modules) {
                            String[] q = value.split(":");
                            if (q[0].equals(m.English)) {
                                for (Setting setting : m.settings) {
                                    if (setting instanceof BooleanSetting) {
                                        String[] q1 = value.split(":");
                                        BooleanSetting bool = (BooleanSetting) setting;
                                        if (q1[1].equals(setting.English)) {
                                            if (q1[2].equals("true")) {
                                                bool.enabled = true;
                                            }
                                            if (q1[2].equals("false")) {
                                                bool.enabled = false;
                                            }
                                        }
                                    }
                                    if (setting instanceof NumberSetting) {
                                        String[] q1 = value.split(":");
                                        NumberSetting number = (NumberSetting) setting;
                                        if (q1[1].equals(setting.English)) {
                                            Double fromstring = new Double(q1[2]);
                                            number.setValue(fromstring);
                                        }
                                    }
                                    if (setting instanceof ModeSetting) {
                                        String[] q1 = value.split(":");
                                        ModeSetting mode = (ModeSetting) setting;
                                        if (q1[1].equals(setting.English)) {
                                            for (int i = 0; i < mode.modes.size(); i++) {
                                                if (mode.getMode().equals(q1[2])) {

                                                } else {
                                                    mode.cycle();
                                                }
                                            }
                                        }
                                    }
                                    if (setting instanceof KeybindSetting) {
                                        String[] q1 = value.split(":");
                                        KeybindSetting key = (KeybindSetting) setting;
                                        if (q1[0].equals(m.English) && q1[1].equals(key.English)) {
                                            Double fromstring = new Double(q1[2]);
                                            key.code = fromstring.intValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
            }
        }
    }
    public static void loadMods()  {
        File f = new File(fileDir + "/mods.txt");
        if(!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ignored) {
            }
        }else {
            try {
                Scanner s = new Scanner(f);
                ArrayList<String> list = new ArrayList<String>();
                while (s.hasNext()){
                    list.add(s.next());
                }
                if(list.size() == 0) {
                    return;
                }else {
                    for(int p = 0; p < list.size();p++) {
                        for(Module m : ModuleManager.modules) {
                            if(m.English.equals(list.get(p))) {
                                m.toggle();
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
