package cn.starry.hub.utils.scoreboard.events;

import cn.starry.hub.utils.scoreboard.AssembleBoard;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AssembleBoardCreatedEvent extends Event {

    public static HandlerList handlerList = new HandlerList();

    private boolean cancelled = false;
    private final AssembleBoard board;

    /**
     * Assemble Board Created Event.
     *
     * @param board of player.
     */
    public AssembleBoardCreatedEvent(AssembleBoard board) {
        this.board = board;
    }

    public static HandlerList getHandlerList() {
        return AssembleBoardCreatedEvent.handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public AssembleBoard getBoard() {
        return this.board;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
