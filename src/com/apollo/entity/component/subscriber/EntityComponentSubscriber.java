package com.apollo.entity.component.subscriber;

import com.apollo.entity.component.EntityComponent;

public abstract class EntityComponentSubscriber<T extends EntityComponent> {

  public abstract void alert(T component);
}
