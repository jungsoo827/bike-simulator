package com.simulator.bike;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void testCommandsViaStdin() {
    String input = "PLACE 0,0,NORTH\nFORWARD\nGPS_REPORT\nEXIT\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    // Simulate running the app without arguments (STDIN mode)
    App.execute(new String[]{});

    String output = out.toString();
    assertTrue(output.contains("(0,1), NORTH"));
  }

  @Test
  public void testInvalidCommandsViaStdin() {
    String input = "INVALID_COMMAND\nPLACE 10,10,NORTH\nGPS_REPORT\nEXIT\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    App.execute(new String[]{});

    String output = out.toString();
    // Verify it handles invalid commands gracefully
    assertTrue(output.contains(Bike.notYetPlacedMessage()));
  }
}
