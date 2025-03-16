package com.apollo.engine.world.path.heuristic;

import com.apollo.engine.world.Tile;

public interface Heuristic {

  double calculate(Tile start, Tile destination);
}
