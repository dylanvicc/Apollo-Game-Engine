package com.apollo.engine.world.path.heuristic.impl;

import com.apollo.engine.world.Tile;
import com.apollo.engine.world.path.heuristic.Heuristic;

public class EuclideanHeuristic implements Heuristic {

  @Override
  public double calculate(Tile start, Tile destination) {
    return Math.sqrt(Math.pow(start.getX() - destination.getX(), 2) + Math.pow(start.getY() - destination.getY(), 2));
  }
}
