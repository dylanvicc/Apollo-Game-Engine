package com.apollo.entity.component.impl;

import com.apollo.entity.component.EntityComponent;

public class NameComponent extends EntityComponent {

  private String name;

  public NameComponent(String name) {
    this.name = name;
  }

  @Override
  public boolean mounted() {
    return true;
  }

  @Override
  public boolean unmounted() {
    return true;
  }

  @Override
  public NameComponent self() {
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
