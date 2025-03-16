package com.apollo.engine;

public class GameEngineException extends Exception {

  public GameEngineException() {
    super("An exception occurred within the game's engine.");
  }

  public GameEngineException(String message) {
    super(message);
  }

  public GameEngineException(String message, Throwable cause) {
    super(message, cause);
  }
}