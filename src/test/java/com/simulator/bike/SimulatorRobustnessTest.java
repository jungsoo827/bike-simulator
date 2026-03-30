package com.simulator.bike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimulatorRobustnessTest {

    private Grid grid;
    private Bike bike;
    private BikeSimulator simulator;

    @BeforeEach
    public void setup() {
        grid = new Grid(5, 5);
        bike = new Bike(grid);
        simulator = new BikeSimulator(grid, bike);
    }

    @Test
    public void testCaseInsensitiveCommands() {
        assertTrue(simulator.processCommand("place 0,0,north"));
        assertEquals("(0,0), NORTH", bike.getReport());
        
        assertTrue(simulator.processCommand("forward"));
        assertEquals("(0,1), NORTH", bike.getReport());
        
        assertTrue(simulator.processCommand("turn_left"));
        assertEquals("(0,1), WEST", bike.getReport());
    }

    @Test
    public void testWhitespaceResilience() {
        assertTrue(simulator.processCommand("  PLACE   0, 0,  NORTH  "));
        assertEquals("(0,0), NORTH", bike.getReport());
    }

    @Test
    public void testInvalidPlacementCoordinates() {
        // Place outside boundary (5x5 grid)
        simulator.processCommand("PLACE 10,10,NORTH");
        assertFalse(bike.hasPlaced());
    }

    @Test
    public void testExitCommand() {
        assertFalse(simulator.processCommand("EXIT"));
    }
}
