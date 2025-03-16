package com.apollo.entity.component.impl;

import com.apollo.entity.component.EntityComponent;

public class LevelComponent extends EntityComponent {

  private int attack;
  private int stamina;

  public LevelComponent(int attack, int stamina) {
    this.attack = attack;
    this.stamina = stamina;
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
  public LevelComponent self() {
    return this;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getStamina() {
    return stamina;
  }

  public void setStamina(int stamina) {
    this.stamina = stamina;
  }
}
