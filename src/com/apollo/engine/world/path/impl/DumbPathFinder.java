package com.apollo.engine.world.path.impl;

import java.util.LinkedList;
import java.util.Queue;

import com.apollo.engine.world.Tile;
import com.apollo.engine.world.TileType;
import com.apollo.engine.world.path.PathFinder;
import com.apollo.engine.world.path.heuristic.Heuristic;

public class DumbPathFinder implements PathFinder {

  @Override
  public Queue<Tile> route(Heuristic heuristic, Tile start, Tile destination) {

    final Queue<Tile> path = new LinkedList<>();
    final Tile current = new Tile(start);

    /*
     * The start tile.
     */
    path.add(new Tile(current));

    while (!current.equals(destination)) {

      /*
       * The tile to move to.
       */
      final Tile next = new Tile(current);

      double steps = Double.MAX_VALUE;

      final Tile[] moves = { new Tile(current.getX() + 1, current.getY(), TileType.TRAVERSABLE),
          new Tile(current.getX() - 1, current.getY(), TileType.TRAVERSABLE),
          new Tile(current.getX(), current.getY() + 1, TileType.TRAVERSABLE),
          new Tile(current.getX(), current.getY() - 1, TileType.TRAVERSABLE) };

      for (Tile move : moves) {

        double distance = heuristic.calculate(move, destination);

        if (distance < steps) {
          next.set(move.getX(), move.getY(), TileType.TRAVERSABLE);

          steps = distance;
        }
      }

      /*
       * A dumb path finder does not consider path obstructions.
       */
      current.set(next.getX(), next.getY(), TileType.TRAVERSABLE);

      /*
       * Traverses the current tile.
       */
      path.add(new Tile(current));
    }

    return path;
  }
}