package xyz.xianyu.module.modules.render;


import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;
import xyz.xianyu.events.EventUpdate;
import xyz.xianyu.module.Module;
import xyz.xianyu.settings.BooleanSetting;
import xyz.xianyu.settings.ModeSetting;
import xyz.xianyu.settings.NumberSetting;


public class Animations extends Module {
	public final static NumberSetting x = new NumberSetting("X","X",0.0,-1.0,1.0,0.05);
	public final static NumberSetting y = new NumberSetting("Y","Y",0.15,-1.0,1.0,0.05);
	public static BooleanSetting EveryThingBlock = new BooleanSetting("EveryThingBlock","所有物品防砍",false);
	public static BooleanSetting EveryTimeBlock = new BooleanSetting("EveryTimeBlock","所有时间防砍",false);
	public final static NumberSetting z = new NumberSetting("Z","Z",0.0,-1.0,1.0,0.05);
	public final static ModeSetting Modes = new ModeSetting("Mode","防砍模式","Vanilla","Vanilla","1.7","Remix","Lunar","Screw","Swang","Swank","Swing","Swong","SwAing","Custom","Gay","Punch","Winter","rotate");
	public final static NumberSetting Speed = new NumberSetting("Speed","速度", 10.0, 1.0, 50.0, 1.0);
    public Animations() {
        super("OldAnimations","防砍动画", Keyboard.KEY_P, Category.RENDER);
        this.addSettings(x,y,z,Modes,Speed,EveryThingBlock,EveryTimeBlock);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(e.isPre()){
            attemptSwing();
        }
    }

    public static void attemptSwing() {
    	if (mc.thePlayer.getItemInUseCount() > 0) {
            boolean mouseDown = mc.gameSettings.keyBindAttack.isKeyDown() && mc.gameSettings.keyBindUseItem.isKeyDown();
            if(EveryTimeBlock.isEnabled()){
                if(mouseDown){
                    swingItem(mc.thePlayer);
                }
            }else{
                if (mouseDown && !mc.objectMouseOver.typeOfHit.equals(MovingObjectPosition.MovingObjectType.ENTITY) && mc.objectMouseOver.typeOfHit.equals(MovingObjectPosition.MovingObjectType.BLOCK)) {
                    swingItem(mc.thePlayer);
                }
            }
        }
    }

    public static void swingItem(EntityPlayerSP entityplayersp) {
        int swingAnimationEnd = entityplayersp.isPotionActive(Potion.digSpeed)
                ? (6 - (1 + entityplayersp.getActivePotionEffect(Potion.digSpeed).getAmplifier()))
                : (entityplayersp.isPotionActive(Potion.digSlowdown)
                ? (6 + (1 + entityplayersp.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2)
                : 6);
        if (!entityplayersp.isSwingInProgress || entityplayersp.swingProgressInt >= swingAnimationEnd / 2
                || entityplayersp.swingProgressInt < 0) {
            entityplayersp.swingProgressInt = -1;
            entityplayersp.isSwingInProgress = true;
            mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
        }
    }

}
