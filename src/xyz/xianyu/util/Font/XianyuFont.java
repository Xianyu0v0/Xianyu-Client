package xyz.xianyu.util.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import java.awt.*;

public class XianyuFont {
    UnicodeFont uf;
    public XianyuFont(int size) {
        java.awt.Font awt = new java.awt.Font("微软雅黑",0,size);
        uf = new UnicodeFont(awt)/*.addAsciiGlyphs()*/;
        uf.getEffects().add(new ColorEffect(Color.white));
        uf.addGlyphs("鄵");
        try {
            uf.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public int getHeight(String msg) {
        return uf.getHeight(msg);
    }

    public void drawString(String string,float x,float y,int color) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
        uf.addGlyphs(string);
        try {
            uf.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
        boolean blend = GL11.glIsEnabled(3042);
        boolean lighting = GL11.glIsEnabled(2896);
        boolean texture = GL11.glIsEnabled(3553);
        if (!blend) {
            GL11.glEnable(3042);
        }
        if (lighting) {
            GL11.glDisable(2896);
        }
        if (texture) {
            GL11.glDisable(3553);
        }
        uf.drawString(x, y, string,new org.newdawn.slick.Color(color));
        if (texture) {
            GL11.glEnable(3553);
        }
        if (lighting) {
            GL11.glEnable(2896);
        }
        if (!blend) {
            GL11.glDisable(3042);
        }
        GL11.glEnable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
    }


}