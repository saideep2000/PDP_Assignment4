package model;

import controller.AbstractTest;

import org.junit.Test;

import view.ImeView;
import view.ImeViewImpl;

import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for the ImeModelImpl class.
 */
public class ImeModelImplTest extends AbstractTest {

  /**
   * This method is used to test the horizontal flip functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void horizontalfliptest1() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-horizontal.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.horizontalFlip().save("koala-horizontal-test");
    int[][][] testpixel2 = reader("koala-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the vertical flip functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void verticalfliptest1() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-vertical.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.verticalFlip().save( "koala-vertical-test");
    int[][][] testpixel2 = reader("koala-vertical-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the vertical and horizontal nested functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void verticalhorizontalfliptest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-vertical-horizontal.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.horizontalFlip().verticalFlip().save(
            "koala-vertical-horizontal-test");
    int[][][] testpixel2 = reader("koala-vertical-horizontal-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the blue component functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void bluecomponenttest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-blue-greyscale.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.greyscale(2).save( "koala-blue-test");
    int[][][] testpixel2 = reader("koala-blue-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the red component functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void redcomponenttest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-red-greyscale.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.greyscale(0).save( "koala-red-test");
    int[][][] testpixel2 = reader("koala-red-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the green component functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void greencomponenttest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-green-greyscale.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.greyscale(1).save( "koala-green-test");
    int[][][] testpixel2 = reader("koala-green-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the value component functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void valuetest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-value-greyscale.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.greyscale(3).save( "koala-value-test");
    int[][][] testpixel2 = reader("koala-value-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the intensity component functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void intensitytest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-intensity-greyscale.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.greyscale(5).save(  "koala-intensity-test");
    int[][][] testpixel2 = reader("koala-intensity-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the luma component functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void lumatest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-luma-greyscale.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.greyscale(4).save(  "koala-luma-test");
    int[][][] testpixel2 = reader("koala-luma-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the brightening functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void brightentest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/koala-brighter-by-50.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/koala/koala.ppm");
    i.brighten(50).save(  "koala-brighten-test");
    int[][][] testpixel2 = reader("koala-brighten-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the darken functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void darkentest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/image-darken-test.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel i = imeM.load("res/image.ppm");
    i.brighten(-50).save( "image-darken-test");
    int[][][] testpixel2 = reader("image-darken-test.ppm");
    assertEquals(testpixels, testpixel2);
  }

  /**
   * This method is used to test the combine images functionality of the model.
   *
   * @throws FileNotFoundException when the image is not found, it throws an exception.
   */
  @Test
  public void combinetest() throws FileNotFoundException {
    int[][][] testpixels = reader("res/koala/Koala.ppm");
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeModel r = imeM.load("res/koala/koala-red-greyscale.ppm");
    ImeModel g = imeM.load("res/koala/koala-green-greyscale.ppm");
    ImeModel b = imeM.load("res/koala/koala-blue-greyscale.ppm");
    imeM.rgbCombine(r, g, b).save( "koala-combine-test");
    int[][][] testpixel2 = reader("koala-combine-test.ppm");
    assertEquals(testpixels, testpixel2);
  }


}