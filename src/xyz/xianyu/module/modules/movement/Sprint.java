package xyz.xianyu.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import xyz.xianyu.events.EventUpdate;
import xyz.xianyu.module.Module;

public class Sprint extends Module {
    public Sprint(){
        super("Sprint","强制疾跑", Keyboard.KEY_N,Category.MOVEMENT);
    }

    @Override
    public void onDisable(){
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),false);
    }

    @EventTarget
    public void onEvent(EventUpdate e){
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),true);
    }
}
