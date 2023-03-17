package view;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import controller.AbstractTest;
import controller.ImeController;
import controller.ImeControllerImpl;
import model.ImeModel;
import model.ImeModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for the ImeViewImpl class.
 */
public class ImeViewImplTest extends AbstractTest {


  /**
   * This method is used to test the view's display function of the program.
   */
  @Test
  public void viewTestWithLoadView() {
    OutputStream out = new ByteArrayOutputStream();
    ImeView imeV = new ImeViewImpl(out);
    imeV.display("Image Loaded");
    String contents = out.toString();
    assertEquals("Image Loaded\n", contents);
  }


  /**
   * This method is used to test the view's display function of the program.
   *
   * @throws FileNotFoundException when the file is not found, it throws an exception.
   */
  @Test
  public void viewTestWithLoad() throws FileNotFoundException {
    InputStream in = null;
    String inp = ("load res/images/hand.ppm image");
    in = new ByteArrayInputStream(inp.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    ImeModel imeM = new ImeModelImpl();
    ImeView imeV = new ImeViewImpl(out);
    ImeController imeC = new ImeControllerImpl(imeM, imeV, in);
    imeC.ppmgo();
    String contents = out.toString();
    assertEquals("Loading the image\n" + "Image loaded\n", contents);
  }

}