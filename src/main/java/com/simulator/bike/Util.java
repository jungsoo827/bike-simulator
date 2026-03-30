package com.simulator.bike;


import com.simulator.bike.enumeration.Command;
import com.simulator.bike.enumeration.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

  private static final Logger logger = LoggerFactory.getLogger(Util.class);

  public static boolean isValid(String input) {
    return input != null && !input.isEmpty();
  }

  public static Command parseCommand(String input) {
    if (Util.isValid(input)) {
      String[] array = input.split(" ");
      try {
        if (array.length > 1) {
          return Command.valueOf(array[0]);
        } else {
          return Command.valueOf(input);
        }
      } catch (Exception e) {
        // do nothing
      }
    }
    return null;
  }

  public static int parseInt(String input) {
    if (Util.isValid(input)) {
      try {
        return Integer.parseInt(input.trim());
      } catch (NumberFormatException e) {
        // do nothing
      }
    }
    return -1;
  }

  public static Direction parseDirection(String input) {
    if (Util.isValid(input)) {
      try {
        return Direction.valueOf(input.trim());
      } catch (Exception e) {
        // do nothing
      }
    }
    return null;
  }

}
