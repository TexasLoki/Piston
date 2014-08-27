package org.pistonmc.event;

public interface Cancellable {

    public boolean isCancelled();

    public void setCancelled(boolean cancelled);

}
