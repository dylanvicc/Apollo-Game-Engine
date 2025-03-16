package com.apollo.engine.world.path.heuristic.impl;

import com.apollo.engine.world.Tile;
import com.apollo.engine.world.path.heuristic.Heuristic;

public class ManhattanHeuristic implements Heuristic {

  @Override
  public double calculate(Tile start, Tile destination) {
    return Math.abs(start.getX() - destination.getX()) + Math.abs(start.getY() - destination.getY());
  }
}