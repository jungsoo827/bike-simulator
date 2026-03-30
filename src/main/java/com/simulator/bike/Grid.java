package com.simulator.bike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Grid {

  private static final Logger logger = LoggerFactory.getLogger(Grid.class);
  private int width = -1;
  private int height = -1;

  public Grid(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public boolean isValidPosition(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;

  }

  public String getBoundaryString() {
    return "(" + this.width + "," + this.height + ")";
  }

}
