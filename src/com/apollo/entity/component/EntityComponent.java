package com.apollo.entity.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.apollo.entity.component.subscriber.EntityComponentSubscriber;

public abstract class EntityComponent {

  /**
   * Invoked when this component is mounted to an {@link Entity}.
   * 
   * @return Denotes if the component was successfully mounted.
   */
  public abstract boolean mounted();

  /**
   * Invoked when this component is unmounted from an {@link Entity}.
   * 
   * @return Denotes if the component was successfully unmounted.
   */
  public abstract boolean unmounted();

  /**
   * Retrieves the current instance of the component. Ensures type safety.
   *
   * @param <T> The sub-type.
   * @return The current instance.
   */
  public abstract <T extends EntityComponent> T self();

  /**
   * A thread-safe collection of subscriptions that listen for changes and or
   * events related to this component.
   */
  protected List<EntityComponentSubscriber<? extends EntityComponent>> subscribers = Collections
      .synchronizedList(new ArrayList<EntityComponentSubscriber<? extends EntityComponent>>());

  public void alert() {

    if (self() == null)
      return;

    final Iterator<EntityComponentSubscriber<? extends EntityComponent>> $iterator = subscribers.iterator();

    while ($iterator.hasNext()) {

      final EntityComponentSubscriber<? extends EntityComponent> next = $iterator.next();

      if (next == null)
        continue;

      next.alert(self());
    }
  }
}