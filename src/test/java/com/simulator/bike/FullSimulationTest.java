package com.simulator.bike;

import com.simulator.bike.enumeration.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FullSimulationTest {

    @Test
    public void testInvalidPlacement() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);

        // Attempt to place outside grid boundaries
        bike.place(10, 10, Direction.NORTH);
        assertEquals(Bike.notYetPlacedMessage(), bike.getReport());

        // Attempt to place with invalid coordinates
        bike.place(-1, 0, Direction.NORTH);
        assertEquals(Bike.notYetPlacedMessage(), bike.getReport());
    }


    @Test
    public void testComplexSequence() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);

        // Setup
        bike.place(1, 2, Direction.EAST);

        // Sequence: forward, forward, turn_left, forward
        bike.forward();
        bike.forward();
        bike.turnLeft();
        bike.forward();

        assertEquals("(3,3), NORTH", bike.getReport());
    }

    @Test
    public void testRotationBoundary() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);

        bike.place(0, 0, Direction.SOUTH);
        bike.turnRight(); // Now WEST
        assertEquals("(0,0), WEST", bike.getReport());

        bike.turnRight(); // Now NORTH
        assertEquals("(0,0), NORTH", bike.getReport());
    }

    @Test
    public void testUnplacedBike() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);

        // Bike not placed yet
        assertEquals(Bike.notYetPlacedMessage(), bike.getReport());

        bike.forward();
        assertEquals(Bike.notYetPlacedMessage(), bike.getReport());
    }

    @Test
    public void testReplacedBike() {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike(grid);

        bike.place(0, 0, Direction.NORTH);
        bike.forward();
        bike.place(5, 5, Direction.SOUTH);

        assertEquals("(5,5), SOUTH", bike.getReport());
    }
}
