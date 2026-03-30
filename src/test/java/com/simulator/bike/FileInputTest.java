package com.simulator.bike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class FileInputTest {

  @Test
  public void testFileProcessingSuccess() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      int statusCode = App.execute(new String[]{"src/test/resources/sample.txt"});
      assertEquals(0, statusCode);

      String output = outContent.toString().trim();
      String[] lines = output.split(System.lineSeparator());
      String lastLine = lines[lines.length - 1];

      assertEquals("(0,4), WEST", lastLine);
    } finally {
      System.setOut(originalOut);
    }
  }


  @Test
  public void testFileNotFoundHandled() throws Exception {
    int statusCode = App.execute(new String[]{"non_existent_file.txt"});

    assertEquals(1, statusCode);
  }


}
