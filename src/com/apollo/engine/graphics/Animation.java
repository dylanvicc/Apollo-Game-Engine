package com.apollo.engine.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

public class Animation {

  /**
   * The individual frames that compose this sequence.
   */
  private final Image[] frames;

  /**
   * Creates a new instance of this class file.
   * 
   * @param frames The individual frames that compose this sequence.
   */
  public Animation(Image[] frames) {
    this.frames = frames;
  }

  /**
   * Renders a sequence of frames at specified pixel coordinate points. Enables
   * anti-aliasing and interpolation.
   * 
   * @param graphics Encapsulates state information for basic two-dimensional
   *                 graphics.
   * @param x        The X coordinate.
   * @param y        The Y coordinate.
   */
  public void play(Graphics graphics, int x, int y) {

    final Graphics2D _graphics = (Graphics2D) graphics;
    _graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    _graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    for (Image frame : frames) {

      if (frame == null)
        continue;

      _graphics.drawImage(frame, x, y, frame.getWidth(null), frame.getHeight(null), null);
    }
  }
}
