package com.darkmagician6.eventapi.events.callables;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;

/**
 * Abstract example implementation of the Cancellable interface.
 *
 * @author DarkMagician6
 * @since August 27, 2013
 */
public abstract class EventCancellable implements Event, Cancellable {

    private boolean cancelled;
    private boolean isPre;

    protected EventCancellable() {
    }

    public boolean isPre(){
        return isPre;
    }

    public boolean isPost(){
        return !isPre;
    }

    public void setPre(){
        isPre=true;
    }

    public void setPost(){
        isPre = false;
    }

    /**
     *
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     *
     */
    @Override
    public void setCancelled(boolean state) {
        cancelled = state;
    }

}
