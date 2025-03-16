package com.apollo.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public abstract class GameEngine extends Canvas implements Runnable {

  /**
   * The default rate in milliseconds that content is rendered on the screen.
   */
  public static final int DEFAULT_RENDER_RATE = 16;

  /**
   * Schedules commands to run after a given delay. Processes engine logic and
   * graphics. Allocates pool size based on the system's available processor.
   */
  private final ScheduledExecutorService executor = Executors
      .newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

  /**
   * The user's window.
   */
  private final JFrame frame = new JFrame();

  /**
   * Renders the graphics on the user's window.
   * 
   * @param graphics Encapsulates state information for basic two-dimensional
   *                 graphics.
   */
  public abstract void render(Graphics graphics);

  /**
   * Thread safe collection for logical events to execute within the engine prior
   * to rendering. Events may either execute once or execute indefinitely.
   */
  public List<GameEngineEvent> events = Collections.synchronizedList(new LinkedList<GameEngineEvent>());

  public void start(String title, Dimension dimension, int rate) {
    frame.setTitle(title);
    frame.setSize(dimension);
    frame.add(this);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    executor.scheduleAtFixedRate(this, 0, rate, TimeUnit.MILLISECONDS);
  }

  public void stop(boolean interrupt) {
    executor.shutdown();
    try {
      if (executor.awaitTermination(1, TimeUnit.SECONDS) == false)
        executor.shutdownNow();
    } catch (InterruptedException exception) {
      executor.shutdownNow();
    }
  }

  public void submit(GameEngineEvent event) {
    events.add(event);
  }

  @Override
  public void run() {

    final Iterator<GameEngineEvent> $iterator = events.iterator();

    while ($iterator.hasNext()) {

      final GameEngineEvent event = $iterator.next();

      if (event == null)
        continue;

      /*
       * Determines if the conditions are met for this event's execution.
       */
      if (event.predicate())
        event.execute();

      /*
       * Determines the persistence of this event. Removes this event if applicable.
       */
      if (event.isPersistent() == false)
        $iterator.remove();
    }

    try {

      if (getBufferStrategy() == null)
        createBufferStrategy(3);

      /*
       * Organizes complex memory on this window.
       */
      final BufferStrategy strategy = getBufferStrategy();

      /*
       * Encapsulates graphical state information.
       */
      final Graphics graphics = strategy.getDrawGraphics();

      try {
        render(graphics);
      } finally {
        graphics.dispose();
      }

      strategy.show();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
