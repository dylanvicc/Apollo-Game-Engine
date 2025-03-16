package com.apollo.engine.world;

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
   * @param x The X coordinate of the point of view.
   * @param y The Y coordinate of the point of view.
   * @return The entities that are currently in view.
   */
  public List<Entity> neighbors(int x, int y) {

    final QuadTree tree = new QuadTree(new Boundary(0, 0, 1000, 1000), MAXIMUM_ENTITIES_IN_VIEW);

    for (Entity entity : entities) {

      if (entity == null)
        continue;

      tree.insert(entity);
    }

    return tree.query(new Boundary(x, y, 100, 100), null);
  }

  public static World getSingleton() {
    return singleton;
  }
}