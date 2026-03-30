package com.simulator.bike.enumeration;

public enum Direction {
  NORTH, SOUTH, EAST, WEST;

  public Direction turnLeft() {
    return switch (this) {
      case NORTH -> WEST;
      case WEST -> SOUTH;
      case SOUTH -> EAST;
      default -> NORTH;
    };
  }

  public Direction turnRight() {
    return switch (this) {
      case NORTH -> EAST;
      case EAST -> SOUTH;
      case SOUTH -> WEST;
      default -> NORTH;
    };
  }
}
