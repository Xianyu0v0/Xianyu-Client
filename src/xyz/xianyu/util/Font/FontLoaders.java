/*
 * Decompiled with CFR 0.150.
 */
package xyz.xianyu.util.Font;

import xyz.xianyu.util.Font.CFontRenderer;
import java.awt.Font;
import java.io.InputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FontLoaders {
	public static CFontRenderer Comfortaa15 = new CFontRenderer(FontLoaders.getComfortaa(15), true, true);
    public static CFontRenderer Comfortaa16 = new CFontRenderer(FontLoaders.getComfortaa(16), true, true);
    public static CFontRenderer Comfortaa18 = new CFontRenderer(FontLoaders.getComfortaa(18), true, true);
    public static CFontRenderer Comfortaa20 = new CFontRenderer(FontLoaders.getComfortaa(20), true, true);
    public static CFontRenderer Comfortaa22 = new CFontRenderer(FontLoaders.getComfortaa(22), true, true);
    public static CFontRenderer Comfortaa24 = new CFontRenderer(FontLoaders.getComfortaa(24), true, true);
    public static CFontRenderer Comfortaa26 = new CFontRenderer(FontLoaders.getComfortaa(26), true, true);
    public static CFontRenderer Comfortaa28 = new CFontRenderer(FontLoaders.getComfortaa(28), true, true);
    public static CFontRenderer GoogleSans16 = new CFontRenderer(FontLoaders.getGoogleSans(16), true, true);
    public static CFontRenderer GoogleSans18 = new CFontRenderer(FontLoaders.getGoogleSans(18), true, true);
    public static CFontRenderer GoogleSans20 = new CFontRenderer(FontLoaders.getGoogleSans(20), true, true);
    public static CFontRenderer GoogleSans22 = new CFontRenderer(FontLoaders.getGoogleSans(22), true, true);
    public static CFontRenderer GoogleSans24 = new CFontRenderer(FontLoaders.getGoogleSans(24), true, true);
    public static CFontRenderer GoogleSans26 = new CFontRenderer(FontLoaders.getGoogleSans(26), true, true);
    public static CFontRenderer GoogleSans28 = new CFontRenderer(FontLoaders.getGoogleSans(28), true, true);
    public static CFontRenderer GoogleSans35 = new CFontRenderer(FontLoaders.getGoogleSans(35), true, true);
    public static CFontRenderer GoogleSans45 = new CFontRenderer(FontLoaders.getGoogleSans(45), true, true);

    public static CFontRenderer Robotolight14 = new CFontRenderer(FontLoaders.getRobotoLight(14,false), true, true);
    public static CFontRenderer Robotolight16 = new CFontRenderer(FontLoaders.getRobotoLight(16,false), true, true);
    public static CFontRenderer Robotolight18 = new CFontRenderer(FontLoaders.getRobotoLight(18,false), true, true);
    public static CFontRenderer Robotolight20 = new CFontRenderer(FontLoaders.getRobotoLight(20,false), true, true);
    public static CFontRenderer Robotolight22 = new CFontRenderer(FontLoaders.getRobotoLight(22,false), true, true);

    public static CFontRenderer RobotoBold14 = new CFontRenderer(FontLoaders.getRobotoLight(14,true), true, true);
    public static CFontRenderer RobotoBold16 = new CFontRenderer(FontLoaders.getRobotoLight(16,true), true, true);
    public static CFontRenderer RobotoBold18 = new CFontRenderer(FontLoaders.getRobotoLight(18,true), true, true);
    public static CFontRenderer RobotoBold20 = new CFontRenderer(FontLoaders.getRobotoLight(20,true), true, true);
    public static CFontRenderer RobotoBold22 = new CFontRenderer(FontLoaders.getRobotoLight(22,true), true, true);

    public static CFontRenderer RobotoBoldLogo = new CFontRenderer(FontLoaders.getRobotoLight(38,true), true, true);
    public static CFontRenderer Robotolight30 = new CFontRenderer(FontLoaders.getRobotoLight(30,false), true, true);

    public static CFontRenderer kiona16 = new CFontRenderer(FontLoaders.getKiona(16), true, true);
    public static CFontRenderer kiona18 = new CFontRenderer(FontLoaders.getKiona(18), true, true);
    public static CFontRenderer kiona20 = new CFontRenderer(FontLoaders.getKiona(20), true, true);
    public static CFontRenderer kiona22 = new CFontRenderer(FontLoaders.getKiona(22), true, true);
    public static CFontRenderer kiona24 = new CFontRenderer(FontLoaders.getKiona(24), true, true);
    public static CFontRenderer kiona26 = new CFontRenderer(FontLoaders.getKiona(26), true, true);
    public static CFontRenderer kiona28 = new CFontRenderer(FontLoaders.getKiona(28), true, true);

    public static Font getFont(int size) {
        return new Font("default", 0, size);
    }


    public static Font getFont(String name, int size) {
        Font font;
        try {
            InputStream is2 = Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("ETB/" + name + ".ttf")).getInputStream();
            font = Font.createFont(0, is2);
            font = font.deriveFont(0, size);
        } catch (Exception ex2) {
            ex2.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getKiona(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("ETB/raleway.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static Font getRobotoLight(int size, boolean bold) {
        Font font;
        try {
            InputStream is;
            if(bold) {
                is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("client/fonts/Roboto-Bold.ttf")).getInputStream();
            }else{
                is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("client/fonts/Roboto-Light.ttf")).getInputStream();
            }
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }


    private static Font getComfortaa(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("Xianyu/FONT/Comfortaa.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }


    private static Font getGoogleSans(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("Xianyu/FONT/GoogleSans.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
}

