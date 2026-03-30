package com.simulator.bike;

import com.simulator.bike.enumeration.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikeMovementTest {

    @Test
    public void testBasicForwardMovement() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);
        bike.place(0, 0, Direction.NORTH);
        bike.forward();
        assertEquals("(0,1), NORTH", bike.getReport());
    }

    @Test
    public void testFullRotation() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);
        bike.place(0, 0, Direction.NORTH);

        bike.turnLeft();
        assertEquals("(0,0), WEST", bike.getReport());
        bike.turnLeft();
        assertEquals("(0,0), SOUTH", bike.getReport());
        bike.turnLeft();
        assertEquals("(0,0), EAST", bike.getReport());
        bike.turnLeft();
        assertEquals("(0,0), NORTH", bike.getReport());
    }

    @Test
    public void testMoveAroundGrid() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);
        bike.place(0, 0, Direction.NORTH);

        for (int i = 0; i < 6; i++) bike.forward();
        assertEquals("(0,6), NORTH", bike.getReport());

        bike.turnRight(); // Now EAST
        for (int i = 0; i < 6; i++) bike.forward();
        assertEquals("(6,6), EAST", bike.getReport());

        bike.turnRight(); // Now SOUTH
        for (int i = 0; i < 6; i++) bike.forward();
        assertEquals("(6,0), SOUTH", bike.getReport());

        bike.turnRight(); // Now WEST
        for (int i = 0; i < 6; i++) bike.forward();
        assertEquals("(0,0), WEST", bike.getReport());
    }
}
