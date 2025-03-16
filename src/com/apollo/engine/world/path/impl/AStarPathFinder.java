package com.apollo.engine.world.path.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.apollo.engine.world.Tile;
import com.apollo.engine.world.TileType;
import com.apollo.engine.world.path.PathFinder;
import com.apollo.engine.world.path.heuristic.Heuristic;

public class AStarPathFinder implements PathFinder {

  @Override
  public Queue<Tile> route(Heuristic heuristic, Tile start, Tile destination) {

    /*
     * Tiles that have yet to be evaluated.
     */
    final PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble(Node::getFScore));

    /*
     * Tiles that have already been evaluated.
     */
    final Set<Tile> closed = new HashSet<>();

    /*
     * Links each tile to the tile it came from in the shortest known path.
     */
    final Map<Tile, Tile> source = new HashMap<>();

    /*
     * Stores the known costs from the start tile to a given tile.
     */
    final Map<Tile, Double> scores = new HashMap<>();

    open.add(new Node(start, heuristic.calculate(start, destination)));
    scores.put(start, 0.0);

    while (!open.isEmpty()) {

      final Node current = open.poll();

      if (current.tile.equals(destination))
        return reconstruct(source, destination);

      closed.add(current.tile);

      for (Tile neighbor : current.tile.getNeighbors()) {

        if (neighbor.getType() == TileType.BLOCKED || closed.contains(neighbor))
          continue;

        final double score = scores.getOrDefault(current.tile, Double.MAX_VALUE)
            + heuristic.calculate(current.tile, neighbor);

        if (score < scores.getOrDefault(neighbor, Double.MAX_VALUE)) {

          source.put(neighbor, current.tile);
          scores.put(neighbor, score);

          final double cheapest = score + heuristic.calculate(neighbor, destination);

          if (open.stream().noneMatch(node -> node.tile.equals(neighbor))) {
            open.add(new Node(neighbor, cheapest));
          }
        }
      }
    }

    return new LinkedList<>();
  }

  private Queue<Tile> reconstruct(Map<Tile, Tile> source, Tile current) {

    final LinkedList<Tile> path = new LinkedList<>();
    path.add(current);

    while (source.containsKey(current)) {
      current = source.get(current);
      path.addFirst(current);
    }

    return path;
  }

  private static class Node {

    private Tile tile;

    /**
     * The total estimated cost of the cheapest solution path that goes through the
     * current tile
     */
    private double cheapest;

    public Node(Tile tile, double cheapest) {
      this.tile = tile;
      this.cheapest = cheapest;
    }

    public double getFScore() {
      return cheapest;
    }
  }
}
