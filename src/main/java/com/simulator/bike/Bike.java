package com.simulator.bike;

import com.simulator.bike.enumeration.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bike {

  private static final Logger logger = LoggerFactory.getLogger(Bike.class);

  private int x = -1;
  private int y = -1;

  private final Grid grid;
  private boolean placed = false;
  private Direction direction;

  public Bike(Grid grid) {
    this.grid = grid;
  }


  public void place(int x, int y, Direction direction) {
    if (this.grid.isValidPosition(x, y) && direction != null) {
      this.x = x;
      this.y = y;
      this.direction = direction;
      this.placed = true;
    } else {
      logger.warn("Invalid placement: ({}, {}, {})", x, y, direction);
    }
  }

  public boolean hasPlaced() {
    return placed;
  }

  public void forward() {
    if (!hasPlaced()) {
      logger.warn("Bike has not been placed yet.");
      return;
    }
    switch (direction) {
      case NORTH:
        if (y < this.grid.getHeight() - 1) {
          y++;
        } else {
          logger.warn("Forward {} ignored because it is out of boundary. y={}", direction, y);
        }
        break;
      case EAST:
        if (x < this.grid.getWidth() - 1) {
          x++;
        } else {
          logger.warn("Forward {} ignored because it is out of boundary. x={}", direction, x);
        }
        break;
      case SOUTH:
        if (y > 0) {
          y--;
        } else {
          logger.warn("Forward {} ignored because it is out of boundary. y={}", direction, y);
        }
        break;
      case WEST:
        if (x > 0) {
          x--;
        } else {
          logger.warn("Forward {} ignored because it is out of boundary. x={}", direction, x);
        }
        break;
    }
  }

  public void turnLeft() {
    if (hasPlaced()) {
      direction = direction.turnLeft();
    } else {
      logger.warn("Bike has not been placed yet.");
    }
  }

  public void turnRight() {
    if (hasPlaced()) {
      direction = direction.turnRight();
    } else {
      logger.warn("Bike has not been placed yet.");
    }
  }

  public String getReport() {
    if (hasPlaced()) {
      return "(" + x + "," + y + "), " + direction;
    } else {
      return "Bike has not been placed yet.";
    }
  }


}
