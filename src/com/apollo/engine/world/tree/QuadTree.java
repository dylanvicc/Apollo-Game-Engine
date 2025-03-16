package com.apollo.engine.world.tree;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.apollo.entity.Entity;
import com.apollo.entity.component.impl.PositionComponent;

public class QuadTree {

  /**
   * The maximum capacity of this tree.
   */
  private final int capacity;

  /**
   * The rectangular boundary.
   */
  private final Boundary boundary;

  /**
   * The current entities.
   */
  private final List<Entity> entities;

  /**
   * Denotes if this tree has been divided.
   */
  private boolean divided = false;

  /**
   * The north-eastern child.
   */
  private QuadTree northeast;

  /**
   * The north-western child.
   */
  private QuadTree northwest;

  /**
   * The south-eastern child.
   */
  private QuadTree southeast;

  /**
   * The south-western child.
   */
  private QuadTree southwest;

  public QuadTree(Boundary boundary, int capacity) {
    this.boundary = boundary;
    this.capacity = capacity;
    this.entities = new ArrayList<>();
  }

  public boolean insert(Entity entity) {

    final PositionComponent position = (PositionComponent) entity.component(PositionComponent.class);

    if (position == null)
      return false;

    final Point point = new Point(position.getX(), position.getY());

    if (!boundary.contains(point))
      return false;

    if (entities.size() < capacity) {
      entities.add(entity);
      return true;
    } else {
      if (!divided)
        subdivide();

      if (northeast.insert(entity) || northwest.insert(entity) || southeast.insert(entity) || southwest.insert(entity))
        return true;
    }

    return false;
  }

  /**
   * Subdivides this tree into four child quadrants. Invoked when capacity is
   * exceeded.
   */
  private void subdivide() {

    double x = boundary.x;
    double y = boundary.y;

    double width = boundary.width / 2;
    double height = boundary.height / 2;

    northeast = new QuadTree(new Boundary(x + width, y, width, height), capacity);
    northwest = new QuadTree(new Boundary(x, y, width, height), capacity);
    southeast = new QuadTree(new Boundary(x + width, y + height, width, height), capacity);
    southwest = new QuadTree(new Boundary(x, y + height, width, height), capacity);

    divided = true;
  }

  public List<Entity> query(Boundary range, List<Entity> found) {

    if (found == null)
      found = new ArrayList<>();

    if (!boundary.intersects(range)) {
      return found;
    } else {
      for (Entity entity : entities) {

        final PositionComponent position = (PositionComponent) entity.component(PositionComponent.class);

        if (position == null)
          continue;

        final Point point = new Point(position.getX(), position.getY());

        if (range.contains(point))
          found.add(entity);
      }

      if (divided) {
        northeast.query(range, found);
        northwest.query(range, found);
        southeast.query(range, found);
        southwest.query(range, found);
      }
    }

    return found;
  }
}