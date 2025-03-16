package com.apollo.engine;

public abstract class GameEngineEvent {

  /**
   * Denotes if this event should persist or only execute a single time.
   */
  private boolean persist;

  /**
   * Executes this event.
   */
  public abstract void execute();

  /**
   * The conditions required for this event's execution.
   * 
   * @return Denotes if the conditions were met.
   */
  public abstract boolean predicate();

  public GameEngineEvent(boolean persist) {
    this.persist = persist;
  }

  public boolean isPersistent() {
    return persist;
  }

  public void setPersistent(boolean persist) {
    this.persist = persist;
  }
}
