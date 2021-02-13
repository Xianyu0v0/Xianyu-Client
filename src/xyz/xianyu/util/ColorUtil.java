package xyz.xianyu.util;

import java.awt.*;

public class ColorUtil {
    private static float hue = 0.0F;
    private static float hue2 = 0.0F;
    public static int getRainbow(float seconds,float saturation,float brightness){
        float hue = (System.currentTimeMillis() % (int)(seconds * 1000) / (float)(seconds * 1000));
        int color = Color.HSBtoRGB(hue,saturation,brightness);
        return color;
    }

    public static int getRainbow(float seconds,float saturation,float brightness,long a){
        float hue = ((System.currentTimeMillis() + a) % (int)(seconds * 1000) / (float)(seconds * 1000));
        int color = Color.HSBtoRGB(hue,saturation,brightness);
        return color;
    }

    public static Color getColorValue(float saturation, float brightness, float value) {
        if (hue > 255.0F) {
            hue = 0.0F;
        }
        Color color = Color.getHSBColor(hue2 / 255.0F, saturation, brightness);
        hue2 += value;
        return color;
    }
}

