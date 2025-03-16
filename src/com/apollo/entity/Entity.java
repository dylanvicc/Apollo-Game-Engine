package com.apollo.entity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.apollo.entity.component.EntityComponent;

public class Entity {

  /**
   * A hash table that supports full concurrency. Stores the key to value
   * relationship between an entity's components and their namely identifiers.
   */
  private final ConcurrentHashMap<String, EntityComponent> components = new ConcurrentHashMap<String, EntityComponent>();

  /**
   * Logs messages for a specific system or application component.
   */
  private static final Logger logger = Logger.getLogger(Entity.class.getName());

  /**
   * Returns an existing component.
   * 
   * @param clazz The class reference of the component to return.
   * @return The component that was returned.
   */
  public EntityComponent component(Class<? extends EntityComponent> clazz) {

    final EntityComponent component = components.get(clazz.getName());

    if (component == null)
      logger.log(Level.WARNING, clazz.getName() + " is not held by this entity.");

    return component;
  }

  /**
   * Removes an existing component.
   * 
   * @param clazz The class reference of the component to remove.
   * @return The component that was removed.
   */
  public EntityComponent remove(Class<? extends EntityComponent> clazz) {

    final EntityComponent component = components.remove(clazz.getName());

    if (component == null) {
      logger.log(Level.SEVERE, "Can not remove a null component.");
      return null;
    }

    if (component.unmounted() == false)
      logger.log(Level.WARNING, "Failed to unmount " + clazz.getName() + ".");

    return component;
  }

  /**
   * Adds a new component.
   * 
   * @param component The component to add.
   * @return The component that was added.
   */
  public EntityComponent add(EntityComponent component) {

    if (component == null) {
      logger.log(Level.SEVERE, "Can not add a null component.");
      return null;
    }

    final String name = component.getClass().getName();

    if (components.containsKey(name)) {
      logger.log(Level.WARNING, name + " is already held by this entity.");
      return null;
    }

    if (component.mounted() == false)
      logger.log(Level.WARNING, "Failed to mount " + name + ".");

    return components.put(name, component);
  }
}
