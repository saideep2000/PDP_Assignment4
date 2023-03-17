package controller;

import model.ImeModel;
import model.ImeModelImpl;

import org.junit.Test;

import view.ImeView;
import view.ImeViewImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;


import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for the ImeControllerImpl class.
 */
public class ImeControllerImplTest extends AbstractTest {

  /**
   * This is a mock model class created to test the controller by mimicking the model class.
   */
  public class MockModel implements ImeModel {
    private StringBuilder addsb;

    /**
     * This is a constructor for the MockModel class.
     *
     * @param addsb is the string builder to test the controller output.
     */
    public MockModel(StringBuilder addsb) {
      this.addsb = addsb;
    }

    /**
     * This method will append the string filename passed to it by the controller.
     *
     * @param p is the alias name of the image given by the user.
     * @return null.
     * @throws FileNotFoundException when the image is not found, it throws an exception.
     */
    @Override
    public ImeModel load(String p) throws FileNotFoundException {
      addsb.append(p);
      return this;
    }

    /**
     * This method will append the value and image passed to it by the controller.
     *
     * @param value by which the image may be brightened.
     * @return null.
     */
    @Override
    public ImeModel brighten(Integer value) {
      addsb.append("\n").append(value);
      return this;
    }


    /**
     * This method will append the ppm image passed to it by the controller.
     *
     * @return null.
     */
    @Override
    public ImeModel verticalFlip() {
      return this;
    }

    /**
     * This method will append the ppm image passed to it by the controller.
     *
     * @return null.
     */
    @Override
    public ImeModel horizontalFlip() {
      return null;
    }

    /**
     * This method will append the index and ppm image passed to it by the controller.
     *
     * @param s is the index of the greyscale value component.
     * @return null.
     */
    @Override
    public ImeModel greyscale(int s) {
      addsb.append("\n").append(s);
      return null;
    }

    /**
     * This method will append the ppm image and r,g,b filenames passed to it by the controller.
     *
     * @param r is the name of the red component image of the split images.
     * @param g is the name of the green component image of the split images.
     * @param b is the name of the blue component image of the split images.
     * @return null.
     */
    @Override
    public Map<String, ImeModel> rgbSplit(String r, String g, String b) {
      addsb.append("\n").append(r).append("\n").append(g).append("\n").append(b);
      return null;
    }

    /**
     * This method will append the 3 component ppm images passed to it by the controller.
     *
     * @param r is the name of the red component image.
     * @param g is the name of the green component image.
     * @param b is the name of the blue component image.
     * @return null.
     */
    @Override
    public ImeModel rgbCombine(ImeModel r, ImeModel g, ImeModel b) {
      addsb.append("\n").append(r).append("\n").append(g).append("\n").append(g);
      return null;
    }

    /**
     * This method will append the image and image path passed to it by the controller.
     *
     * @param newImagePath is the path where the image must be saved.
     * @return null.
     */
    @Override
    public ImeModel save(String newImagePath) {
      addsb.append("\n").append(newImagePath);
      return null;
    }

    /**
     * This method will return the pixels as a 3D matrix in the controller.
     *
     * @return null
     */
    @Override
    public int[][][] getPixels() {
      return null;
    }
  }


  /**
   * This test is used to test the integrity of the controller using load method and mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockLoad() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    assertEquals("res/koala/Koala.ppm", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of controller using brighten method and mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockBrighten() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nbrighten 10 koala koala-brighter");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n10", mocklog.toString());
  }

  /**
   * This test is used to test integrity of controller using horizontal flip and mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockHorizontal() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nhorizontal-flip koala koala-horizontal");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm", mocklog.toString());
  }

  /**
   * This test is used to test integrity of controller using horizontal flip and mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockVertical() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nvertical-flip koala koala-vertical");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using greyscale red method and
   * mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockGreyscaleRed() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale red-component koala koala-red-component");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n0", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using greyscale green method and
   * mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockGreyscaleGreen() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale green-component koala koala-green-component");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n1", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using greyscale blue method and
   * mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockGreyscaleBlue() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale blue-component koala koala-blue-component");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n2", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using greyscale value method and
   * mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockGreyscaleValue() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale value-component koala koala-value-component");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n3", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using greyscale intensity method
   * and mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockGreyscaleIntensity() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale intensity-component koala koala-intensity-component");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n5", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using greyscale luma method and
   * mock model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void testwithMockGreyscaleLuma() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale luma-component koala koala-luma-component");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("res/koala/Koala.ppm\n4", mocklog.toString());
  }

  /**
   * This test is used to test the integrity of the controller using save method and mock model.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void testwithMockSave() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nsave res/koala-saved.ppm koala");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImeModel imeM = new MockModel(mocklog);
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    assertEquals("res/koala/Koala.ppm\n"
            + "res/koala-saved.ppm", mocklog.toString());
  }


  /**
   * This method is used to test the text file readability of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void txtFileTest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-horizontal.ppm");
    InputStream in = null;
    String inp = ("run res/commands.txt");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }


  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetest() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image\n" + "Image loaded\n", contents);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestcapital() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("LOAD res/koala/Koala.ppm koala");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image" + "\n" + "Image loaded" + "\n", contents);

  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestcapital2() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("Load res/koala/Koala.ppm koala");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image" + "\n" + "Image loaded" + "\n", contents);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestcapital3() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("loAD res/koala/Koala.ppm koala");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image" + "\n" + "Image loaded" + "\n", contents);
  }

  /**
   * This method is used to test the horizontal flip functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void horizontalfliptest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-horizontal.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nhorizontal-flip koala koala-horizontal\n"
            + "save koala-horizontal-test.ppm koala-horizontal");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the horizontal flip functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void horizontalfliptestTwiceLoading() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-horizontal.ppm");
    InputStream in = null;
    String inp = ("load res/book.ppm koala\nload res/koala/Koala.ppm koala"
            + "\nhorizontal-flip koala koala-horizontal\n"
            + "save koala-horizontal-test.ppm koala-horizontal");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the horizontal flip functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void horizontalfliptestTwiceSaving() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-horizontal.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nhorizontal-flip koala koala-horizontal\n"
            + "save koala-horizontal-test.ppm koala-horizontal"
            + "\nsave koala-horizontal-test2.ppm koala-horizontal");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-horizontal-test.ppm");
    int[][][] testpixel3 = reader("koala-horizontal-test2.ppm");

    assertEquals(testpixels, testpixel2);
    assertEquals(testpixel2, testpixel3);
  }

  /**
   * This method is used to test the horizontal flip functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void horizontalfliptestFourTimes() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "horizontal-flip koala koala-horizontal-1\n"
            + "horizontal-flip koala-horizontal-1 koala-horizontal-2\n"
            + "horizontal-flip koala-horizontal-2 koala-horizontal-3\n"
            + "horizontal-flip koala-horizontal-3 koala-horizontal-4\n"
            + "save koala-horizontal-test.ppm koala-horizontal-4");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the vertical flip functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void verticalfliptest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-vertical.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nvertical-flip koala koala-vertical\n"
            + "save koala-vertical-test.ppm koala-vertical");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-vertical-test.ppm");
    assertEquals(testpixels, testpixel2);
  }


  /**
   * This method is used to test the vertical and horizontal nested functionality of the program.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void verticalhorizontalfliptest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-vertical-horizontal.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm  koala\nvertical-flip koala koala-vertical\n"
            + "horizontal-flip koala-vertical koala-vertical-horizontal\n"
            + "save koala-vertical-horizontal-test.ppm koala-vertical-horizontal");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-vertical-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the blue component functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void bluecomponenttest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-blue-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\ngreyscale blue-component koala koala-blue\n"
            + "save koala-blue-test.ppm koala-blue");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-blue-test.ppm");
    assertEquals(testpixels, testpixel2);
  }


  /**
   * This method is used to test the red component functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void redcomponenttest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-red-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\ngreyscale red-component koala koala-red\n"
            + "save koala-red-test.ppm koala-red");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-red-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the green component functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void greencomponenttest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-green-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\ngreyscale green-component koala koala-green\n"
            + "save koala-green-test.ppm koala-green");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-green-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the value component functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void valuetest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-value-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\ngreyscale value-component koala koala-value\n"
            + "save koala-value-test.ppm koala-value");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-value-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the intensity component functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void intensitytest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-intensity-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "greyscale intensity-component koala koala-intensity\n"
            + "save koala-intensity-test.ppm koala-intensity");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-intensity-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the luma component functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void lumaTest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-luma-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\ngreyscale luma-component koala koala-luma\n"
            + "save koala-luma-test.ppm koala-luma");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-luma-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the brightening functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void brightentest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-brighter-by-50.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nbrighten 50 koala koala-brighten\n"
            + "save koala-brighten-test.ppm koala-brighten");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-brighten-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the brightening functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void brightentestOvervalue() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-brighter-by-266.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\nbrighten 266 koala koala-brighten\n"
            + "save koala-brighten-test.ppm koala-brighten");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-brighten-test-266.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the darken functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void darkentest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/image-darken-test.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm image\nbrighten -10 image image-darken\n"
            + "save image-darken-test.ppm image-darken");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("image-darken-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the darken functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void darkentestOvervalue() throws FileNotFoundException {
    int[][][] testpixels = reader("res/image-darken-test-by-266.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm image\nbrighten -266 image image-darken\n"
            + "save image-darken-test.ppm image-darken");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("image-darken-test-266.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the combine images functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void combinetest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/Koala.ppm");
    InputStream in = null;
    String inp = ("load res/koala/koala-red-greyscale.ppm koala-red-test\n"
            + "load res/koala/koala-green-greyscale.ppm koala-green-test\n"
            + "load res/koala/koala-blue-greyscale.ppm koala-blue-test\n"
            + "rgb-combine koala-combine koala-red-test koala-green-test koala-blue-test\n"
            + "save koala-combine-test.ppm koala-combine");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixel2 = reader("koala-combine-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the split images functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void splittest() throws FileNotFoundException {
    int[][][] testpixelsred = reader("res/koala/koala-red-greyscale.ppm");
    int[][][] testpixelsgreen = reader("res/koala/koala-green-greyscale.ppm");
    int[][][] testpixelsblue = reader("res/koala/koala-blue-greyscale.ppm");
    InputStream in = null;
    String inp = ("load res/koala/Koala.ppm koala\n"
            + "rgb-split koala koala-red koala-green koala-blue\n"
            + "save koala-red-split-test.ppm koala-red\n"
            + "save koala-green-split-test.ppm koala-green\n"
            + "save koala-blue-split-test.ppm koala-blue");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    int[][][] testpixelredtest = reader("koala-red-split-test.ppm");
    int[][][] testpixelgreentest = reader("koala-green-split-test.ppm");
    int[][][] testpixelbluetest = reader("koala-blue-split-test.ppm");
    assertEquals(testpixelsred, testpixelredtest);
    assertEquals(testpixelsgreen, testpixelgreentest);
    assertEquals(testpixelsblue, testpixelbluetest);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestWrongCommand() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("loader res/koala/Koala.ppm image");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Incorrect Command\n", contents);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestWrongCommand2() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("upload res/koala/Koala.ppm image");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Incorrect Command\n", contents);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestWrongPath() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load source/koala.ppm image");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image\n"
            + "The image source/koala.ppm doesn't exist !!\n", contents);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestNoImage() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koalaa.ppm image");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image\n"
            + "The image res/koalaa.ppm doesn't exist !!\n", contents);
  }

  /**
   * This method is used to test the load image functionality of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void loadingImagetestWrongExtension() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/koala.pdp image");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Only Ppm files are allowed !!\n", contents);
  }
}