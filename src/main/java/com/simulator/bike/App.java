package com.simulator.bike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    // To support test cases, execution is separated into another method
    int exitCode = execute(args);
    System.exit(exitCode);
  }

  public static int execute(String[] args) {
    logger.info("Starting Bike Simulator");
    Grid grid = new Grid(7, 7);
    Bike bike = new Bike(grid);
    BikeSimulator bikeSimulator = new BikeSimulator(grid, bike);

    if (args.length > 0) {
      // Read from file
      File file = new File(args[0]);
      if (!file.exists()) {
        logger.error("File does not exist: {}", args[0]);
        System.err.println("File not found - " + args[0]);
        return 1;
      }
      try {
        logger.info("Reading input from file: {}", args[0]);
        Scanner scanner = new Scanner(file);
        bikeSimulator.processInput(scanner);
        scanner.close();
      } catch (FileNotFoundException e) {
        logger.error("Reading file error: {}", args[0], e);
        return 2;
      }
    } else {
      // Read from STDIN
      logger.info("Reading input from STDIN");
      bikeSimulator.usage();
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!bikeSimulator.processCommand(line)) {
          break;
        }
      }
      scanner.close();
    }
    logger.info("Bike Simulator finished");
    return 0;
  }
}
