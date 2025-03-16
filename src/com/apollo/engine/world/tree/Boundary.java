package com.apollo.engine.world.tree;

import java.awt.Point;

public class Boundary {

  /**
   * The X coordinate of this area.
   */
  public double x;

  /**
   * The Y coordinate of this area.
   */
  public double y;

  /**
   * The width of this area.
   */
  public double width;

  /**
   * The height of this area.
   */
  public double height;

  public Boundary(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public boolean contains(Point point) {
    return (point.getX() >= x && point.getX() <= x + width && point.getY() >= y && point.getY() <= y + height);
  }

  public boolean intersects(Boundary boundary) {
    return !(boundary.x > x + width || boundary.x + boundary.width < x || boundary.y > y + height
        || boundary.y + boundary.height < y);
  }
}