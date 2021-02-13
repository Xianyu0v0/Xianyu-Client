package xyz.xianyu.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.gui.ScaledResolution;

public class EventRender2D
        implements Event {
    private ScaledResolution sr;
    private float pt;

    public EventRender2D(ScaledResolution sr, float pt) {
        this.sr = sr;
        this.pt = pt;
    }

    public ScaledResolution getSR() {
        return this.sr;
    }

    public float getPartialTicks() {
        return this.pt;
    }
    public float getPT() {
        return this.pt;
    }
}