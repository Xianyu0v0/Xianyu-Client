package xyz.xianyu.events;

import com.darkmagician6.eventapi.events.Event;
public class EventKey implements Event{
    public int code;

    public EventKey(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code=code;
    }
}
