package xyz.xianyu.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventUpdate extends EventCancellable {
    public double x,y,z;
    public float yaw,pitch;
    public boolean onGround;
    public EventUpdate(double x,double y,double z,float yaw,float pitch,boolean onGround){
        this.x=x;
        this.y=y;
        this.z=z;
        this.yaw=yaw;
        this.pitch=pitch;
        this.onGround=onGround;
    }
}
