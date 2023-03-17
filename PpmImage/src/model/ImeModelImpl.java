package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents Ppm Image entity and the operations to be done on the ppm image
 * sent by the controller to the model.
 */
public class ImeModelImpl implements ImeModel {

  private int width;
  private int height;
  private int maxVal;
  private int[][][] pixels;

  /**
   * This is a constructor for the PpmImageImpl class.
   *
   * @param width  is the width of the given image.
   * @param height is the height of the given image.
   * @param maxVal is the max amount of values for each pixel.
   * @param pixels is a 3D matrix that contains the pixel values of the image.
   */
  public ImeModelImpl(int width, int height, int maxVal, int[][][] pixels) {
    this.width = width;
    this.height = height;
    this.maxVal = maxVal;
    this.pixels = pixels;
  }


  /**
   * This method will load the image into the hashmap and will add the user given alias name to
   * access the image for further operations.
   *
   * @param p is the alias name of the image given by the user.
   * @return an ppm image.
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Override
  public ImeModel load(String p) throws FileNotFoundException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(p));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File is not found");
    }

    if (p.contains(".ppm")) {
      return loadPpm(sc);
    }
    return null;
  }

  /**
   * This will only load ppm files.
   *
   * @param sc is the scanner object containing file path.
   * @return will give out ImeModel which represents image
   */
  private ImeModel loadPpm(Scanner sc) {
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

    this.width = sc.nextInt();
    this.height = sc.nextInt();
    this.maxVal = sc.nextInt();
    this.pixels = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j][0] = r;
        pixels[i][j][1] = g;
        pixels[i][j][2] = b;

      }
    }
    return new ImeModelImpl(this.width, this.height, this.maxVal, pixels);
  }

  /**
   * This method will brighten or darken the given image by the given required value.
   *
   * @param value by which the image may be brightened.
   * @return an image that has been brightened by the given value.
   */
  @Override
  public ImeModel brighten(Integer value) {
    int[][][] data = clonePixels(this.pixels);
    int[][][] pixels = new int[this.height][this.width][3];
    for (int x = 0; x < this.height; x++) {
      for (int y = 0; y < this.width; y++) {
        pixels[x][y][0] = Math.max(Math.min(data[x][y][0] + value, 255), 0);
        pixels[x][y][1] = Math.max(Math.min(data[x][y][1] + value, 255), 0);
        pixels[x][y][2] = Math.max(Math.min(data[x][y][2] + value, 255), 0);
      }
    }
    return new ImeModelImpl(this.width, this.height, this.maxVal, pixels);
  }

  /**
   * This method is used to flip the given image vertically.
   *
   * @return an image that has been flipped vertically.
   */
  @Override
  public ImeModel verticalFlip() {
    int[][][] data = clonePixels(this.pixels);
    int[] temp;
    for (int l = 0; l < (this.height / 2); l++) {
      for (int m = 0; m < (this.width); m++) {
        temp = data[l][m];
        data[l][m] = data[this.height - l - 1][m];
        data[this.height - l - 1][m] = temp;
      }
    }
    return new ImeModelImpl(this.width, this.height, this.maxVal, data);
  }

  /**
   * This method is used to flip the given image horizontally.
   *
   * @return an image that has been flipped horizontally.
   */
  @Override
  public ImeModel horizontalFlip() {
    int[][][] data = clonePixels(this.pixels);
    int[] temp;
    for (int l = 0; l < this.height; l++) {
      for (int m = 0; m < (this.width / 2); m++) {
        temp = data[l][m];
        data[l][m] = data[l][this.width - m - 1];
        data[l][this.width - m - 1] = temp;
      }
    }
    return new ImeModelImpl(this.width, this.height, this.maxVal, data);
  }

  /**
   * This method will create a greyscale image with the user given components like red, blue,
   * green, value, intensity and luma.
   *
   * @param s is the index of the greyscale value component.
   * @return an image that has been converted to greyscale based on the given component.
   */
  @Override
  public ImeModel greyscale(int s) {
    return splitChannel(this, s);
  }

  /**
   * This method will split the given user image into 3 component images of red, green and blue.
   *
   * @param r is the name of the red component image of the split images.
   * @param g is the name of the green component image of the split images.
   * @param b is the name of the blue component image of the split images.
   * @return a map that contains the split images and their names as a map.
   */
  @Override
  public Map<String, ImeModel> rgbSplit(String r, String g, String b) {
    Map<String, ImeModel> sample = new HashMap<>();
    sample.put(r, splitChannel(this, 0));
    sample.put(g, splitChannel(this, 1));
    sample.put(b, splitChannel(this, 2));
    return sample;
  }

  /**
   * This method will combine the 3 component images into a single ppm image.
   *
   * @param r is the name of the red component image.
   * @param g is the name of the green component image.
   * @param b is the name of the blue component image.
   * @return an image that has been combined using the given 3 component images.
   */
  @Override
  public ImeModel rgbCombine(ImeModel r, ImeModel g, ImeModel b) {
    int[][][] pixels_r = r.getPixels();
    int[][][] pixels_g = g.getPixels();
    int[][][] pixels_b = b.getPixels();

    int[][][] channelPixels = new int[height][width][3];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        channelPixels[y][x][0] = pixels_r[y][x][0];
        channelPixels[y][x][1] = pixels_g[y][x][0];
        channelPixels[y][x][2] = pixels_b[y][x][0];
      }
    }
    return new ImeModelImpl(width, height, maxVal, channelPixels);
  }

  /**
   * Returns the pixel values of the ppm image as a 3D array.
   *
   * @return A 3D array that represents the pixel values of the ppm image.
   */
  @Override
  public int[][][] getPixels() {
    return clonePixels(this.pixels);
  }

  /**
   * This is an empty constructor, used to instantiate this class.
   */
  public ImeModelImpl() {

    //     The empty constructor is used to create a new instance of the ImeModel during the testing
    //     because ImeModel is also our image and to allow the controller to pass the image to the
    //     model. This also enable us for future proofing.

  }

  /**
   * This method will convert the image into greyscale based on the component needed by the user.
   *
   * @param i            is the image to be converted into greyscale.
   * @param channelIndex is the index value based on the input of the user.
   * @return a ImeModel image that has been converted onto the required greyscale component.
   */
  private ImeModel splitChannel(ImeModel i, int channelIndex) {
    int[][][] pixels = i.getPixels();
    int channelValue = 0;

    int[][][] channelPixels = new int[height][width][3];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (channelIndex < 3) {
          channelValue = pixels[y][x][channelIndex];
        } else {
          if (channelIndex == 3) {
            int temp = Math.max(pixels[y][x][0], pixels[y][x][1]);
            temp = Math.max(temp, pixels[y][x][2]);
            channelValue = Math.round(temp);
          } else if (channelIndex == 4) {
            double luma_value = ((0.2126 * pixels[y][x][0])
                    + (0.7152 * pixels[y][x][1])
                    + (0.0722 * pixels[y][x][2]));
            channelValue = (int) luma_value;
          } else if (channelIndex == 5) {
            int avg = (pixels[y][x][0] + pixels[y][x][1] + pixels[y][x][2]) / 3;
            channelValue = avg;
          }
        }
        channelPixels[y][x][0] = channelValue;
        channelPixels[y][x][1] = channelValue;
        channelPixels[y][x][2] = channelValue;

      }
    }
    return new ImeModelImpl(width, height, maxVal, channelPixels);
  }

  /**
   * This method will save the ppm image from the hashmap into the file directory.
   *
   * @param newImagePath the path of the image to be saved as.
   * @return a ppm image that has been saved into the file directory.
   */
  @Override
  public ImeModel save(String newImagePath) {
    int[][][] data = clonePixels(this.pixels);
    try {
      if (!newImagePath.contains(".ppm")) {
        newImagePath = newImagePath + ".ppm";
      }

      OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newImagePath));

      writer.write("P3\n");
      writer.write(this.width + " " + this.height + "\n");
      writer.write(this.maxVal + "\n");

      for (int row = 0; row < this.height; row++) {
        for (int col = 0; col < this.width; col++) {
          int[] pixel = data[row][col];
          writer.write(pixel[0] + "\n" + pixel[1] + "\n" + pixel[2] + "\n");
        }
      }

      writer.close();
      System.out.println("PPM file created successfully.");
    } catch (IOException e) {
      System.out.println("Error creating PPM file: " + e.getMessage());
    }
    return this;
  }

  /**
   * This method will clone the pixels from the image stored in the hashmap to prevent the changing
   * of original values due to referencing.
   *
   * @param matrix is a 3D matrix containing the pixels of the image.
   * @return a cloned matrix of the given matrix.
   */
  private int[][][] clonePixels(int[][][] matrix) {
    int[][][] cloneArray = new int[this.height][this.width][];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        cloneArray[i][j] = matrix[i][j].clone();
      }
    }
    return cloneArray;
  }
}

