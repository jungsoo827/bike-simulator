package com.simulator.bike;

import com.simulator.bike.enumeration.Command;
import com.simulator.bike.enumeration.Direction;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BikeSimulator {

  private static final Logger logger = LoggerFactory.getLogger(BikeSimulator.class);

  private final Bike bike;
  private final Grid grid;

  public BikeSimulator(Grid grid, Bike bike) {
    this.grid = grid;
    this.bike = bike;
  }


  public void usage() {
    System.out.println("");
    System.out.println("----------------------------------------------------------------------------");
    System.out.println("Usage:");
    System.out.println("  PLACE x,y,direction - Position bike at specified coordinates and direction");
    System.out.println("  FORWARD             - Move bike one unit forward in current direction");
    System.out.println("  TURN_LEFT           - Rotate bike 90 degrees to the left");
    System.out.println("  TURN_RIGHT          - Rotate bike 90 degrees to the right");
    System.out.println("  GPS_REPORT          - Output current position and direction");
    System.out.println("  USAGE               - Usage description");
    System.out.println("  help                - Usage description");
    System.out.println("  EXIT                - End bike simulation");
    System.out.println("");
    System.out.println("Valid directions: NORTH, SOUTH, EAST, WEST");
    System.out.println("Grid size: " + this.grid.getBoundaryString() + " and (0,0) is the south-west corner");
    System.out.println("----------------------------------------------------------------------------");
    System.out.println("");
  }


  private void placeCommand(String params) {
    String[] args = params.split(",");
    if (args.length != 3) {
      return;
    }

    try {
      int tempX = Util.parseInt(args[0]);
      int tempY = Util.parseInt(args[1]);
      if (tempX == -1 || tempY == -1) {
        logger.error("Invalid bike position x ({}) y ({})", args[0], args[1]);
        return;
      }
      if (!this.grid.isValidPosition(tempX, tempX)) {
        logger.warn("Bike cannot be placed outside boundary. " + this.grid.getBoundaryString());
        return;
      }
      Direction direction = Util.parseDirection(args[2]);
      if (direction == null) {
        logger.warn("Invalid direction. " + args[2]);
      }
      bike.place(tempX, tempY, direction);
    } catch (NumberFormatException e) {
      logger.error("Invalid bike position x (" + args[0] + ") y (" + args[1] + ")");
    }
  }

  public void processInput(Scanner scanner) {
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      if (line.isEmpty()) {
        continue;
      }
      processCommand(line);
    }
  }

  /**
   * Processes a single command string by parsing it and invoking the corresponding action on the bike.
   *
   * @param command The raw command string to be parsed and executed.
   * @return true if the command was processed successfully (or was invalid but ignored), false if the command indicates an exit.
   */
  public boolean processCommand(String command) {
    try {
      logger.debug("Command: {}", command);
      if (!Util.isValid(command)) {
        logger.error("Command not valid: " + command);
        return true;
      }
      Command cmd = Util.parseCommand(command);
      if (cmd == null) {
        logger.error("Command not valid: " + command);
        return true;
      }

      switch (cmd) {
        case PLACE:
          String params = command.substring(6);
          placeCommand(params);
          break;
        case FORWARD:
          bike.forward();
          break;
        case TURN_LEFT:
          bike.turnLeft();
          break;
        case TURN_RIGHT:
          bike.turnRight();
          break;
        case GPS_REPORT:
          System.out.println(bike.getReport());
          break;
        case USAGE:
        case help:
          usage();
          break;
        case EXIT:
          return false;
      }
    } catch (IllegalArgumentException e) {
      // Ignore invalid commands
    }
    return true;
  }

}
