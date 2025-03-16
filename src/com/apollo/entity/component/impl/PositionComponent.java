package com.apollo.entity.component.impl;

import java.util.Queue;

import com.apollo.engine.world.Tile;
import com.apollo.engine.world.TileType;
import com.apollo.engine.world.path.PathFinder;
import com.apollo.engine.world.path.heuristic.impl.ManhattanHeuristic;
import com.apollo.entity.component.EntityComponent;
import com.apollo.entity.component.subscriber.impl.PositionComponentSubscriber;;

public class PositionComponent extends EntityComponent {

  private int x;
  private int y;

  public PositionComponent(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void route(PathFinder finder, Tile destination) {

    final Queue<Tile> routes = finder.route(new ManhattanHeuristic(), new Tile(x, y, TileType.TRAVERSABLE),
        destination);

    if (routes.size() == 0)
      return;

    while (routes.isEmpty() == false) {

      final Tile step = routes.poll();

      if (step == null)
        continue;

      set(step.getX(), step.getY());
    }

    alert();
  }

  @Override
  public boolean mounted() {
    subscribers.add(new PositionComponentSubscriber());
    return true;
  }

  @Override
  public boolean unmounted() {
    return true;
  }

  @Override
  public PositionComponent self() {
    return this;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void set(int x, int y) {
    this.x = x;
    this.y = y;
  }
}