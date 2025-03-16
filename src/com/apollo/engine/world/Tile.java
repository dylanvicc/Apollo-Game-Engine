package com.apollo.engine.world;

public class Tile {

  /**
   * The X coordinate of this tile.
   */
  private int x;

  /**
   * The Y coordinate of this tile.
   */
  private int y;

  /**
   * Denotes the type of tile.
   */
  private TileType type;

  public Tile(int x, int y, TileType type) {
    this.x = x;
    this.y = y;
    this.type = type;
  }

  public Tile(Tile tile) {
    this.x = tile.x;
    this.y = tile.y;
    this.type = tile.type;
  }

  public void set(int x, int y, TileType type) {
    this.x = x;
    this.y = y;
    this.type = type;
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

  public TileType getType() {
    return type;
  }

  public void setType(TileType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Tile) {
      final Tile tile = (Tile) other;
      return (x == tile.getX() && y == tile.getY());
    }
    return false;
  }
}
