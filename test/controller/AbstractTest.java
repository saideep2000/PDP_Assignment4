package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a abstract class for the test to store the common methods used.
 */
public abstract class AbstractTest {
  /**
   * This method will extract the pixel values from the image and store them in a 3D array.
   *
   * @param filepath is the file directory location path of the image.
   * @return a 3D array that contains all the pixel values of the image.
   */
  protected int[][][] reader(String filepath) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filepath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filepath + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    int[][][] pixels = new int[height][width][3];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[y][x][0] = r;
        pixels[y][x][1] = g;
        pixels[y][x][2] = b;
      }
    }
    return pixels;
  }
}
