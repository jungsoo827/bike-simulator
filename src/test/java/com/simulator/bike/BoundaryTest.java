package com.simulator.bike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.simulator.bike.enumeration.Direction;
import org.junit.jupiter.api.Test;

public class BoundaryTest {

  @Test
  public void testNorthernBoundary() {
    Grid grid = new Grid(7, 7);
    Bike bike = new Bike(grid);
    bike.place(0, 6, Direction.NORTH);
    bike.forward(); // Should not move
    assertEquals("(0,6), NORTH", bike.getReport());
  }

  @Test
  public void testSouthernBoundary() {
    Grid grid = new Grid(7, 7);
    Bike bike = new Bike(grid);
    bike.place(0, 0, Direction.SOUTH);
    bike.forward(); // Should not move
    assertEquals("(0,0), SOUTH", bike.getReport());
  }

  @Test
  public void testEasternBoundary() {
    Grid grid = new Grid(7, 7);
    Bike bike = new Bike(grid);
    bike.place(6, 0, Direction.EAST);
    bike.forward(); // Should not move
    assertEquals("(6,0), EAST", bike.getReport());
  }

  @Test
  public void testWesternBoundary() {
    Grid grid = new Grid(7, 7);
    Bike bike = new Bike(grid);
    bike.place(0, 0, Direction.WEST);
    bike.forward(); // Should not move
    assertEquals("(0,0), WEST", bike.getReport());
  }
}
