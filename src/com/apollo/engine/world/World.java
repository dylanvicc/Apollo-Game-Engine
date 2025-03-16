package com.apollo.engine.world;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import com.apollo.engine.world.tree.Boundary;
import com.apollo.engine.world.tree.QuadTree;
import com.apollo.entity.Entity;

public class World {

  /**
   * A singleton reference point to restrict multiple instantiations of this class
   * file.
   */
  private static final World singleton = new World();

  /**
   * The entities that are currently in the virtual world.
   */
  public Entity[] entities = new Entity[Integer.MAX_VALUE];

  /**
   * The maximum number of entities rendered in view at any point in time.
   */
  public static final int MAXIMUM_ENTITIES_IN_VIEW = 100;

  /**
   * Returns the entities that are currently within a user's point of view.
   * 
   * @param point The coordinates of the view.
   * @param plane The size of the coordinate plane.
   * @param size  The size of the user's view.
   * @return The entities that are currently in view.
   */
  public List<Entity> neighbors(Point point, Dimension plane, Dimension size) {

    final QuadTree tree = new QuadTree(new Boundary(0, 0, plane.getWidth(), plane.getHeight()),
        MAXIMUM_ENTITIES_IN_VIEW);

    for (Entity entity : entities) {

      if (entity == null)
        continue;

      tree.insert(entity);
    }

    return tree.query(new Boundary(point.getX(), point.getY(), size.getWidth(), size.getHeight()), null);
  }

  public static World getSingleton() {
    return singleton;
  }
}