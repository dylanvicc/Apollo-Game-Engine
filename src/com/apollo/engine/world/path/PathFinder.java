package com.apollo.engine.world.path;

import java.util.Queue;

import com.apollo.engine.world.Tile;
import com.apollo.engine.world.path.heuristic.Heuristic;

public interface PathFinder {

  Queue<Tile> route(Heuristic heuristic, Tile start, Tile destination);
}
