package xyz.xianyu.module.modules.render;

import org.lwjgl.input.Keyboard;
import xyz.xianyu.module.Module;
import xyz.xianyu.ui.ClickGui.ClickUI;

public class ClickGui extends Module {
    public ClickGui(){
        super("ClickGui","点击界面", Keyboard.KEY_RSHIFT,Category.RENDER);
    }

    @Override
    public void onEnable(){
        mc.displayGuiScreen(new ClickUI());
        toggle();
    }
}
