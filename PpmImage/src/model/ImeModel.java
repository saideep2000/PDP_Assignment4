package model;


import java.io.FileNotFoundException;
import java.util.Map;

/**
 * This interface represents the set of operations on the ImeModel.
 */
public interface ImeModel {
    /**
     * This method will load the image into the hashmap and will add the user given alias name to
     * access the image for further operations.
     *
     * @param p is the alias name of the image given by the user.
     * @return a ppm image after loading it into the hashmap.
     */
    ImeModel load(String p) throws FileNotFoundException;

    /**
     * This method will brighten the given image by the given required value.
     *
     * @param value by which the image may be brightened.
     * @return a ppm image that has been brightened by the given value.
     */
    ImeModel brighten(Integer value);

    /**
     * This method is used to flip the given image vertically.
     *
     * @return a ppm image that has been flipped vertically.
     */
    ImeModel verticalFlip();

    /**
     * This method is used to flip the given image horizontally.
     *
     * @return a ppm image that has been flipped horizontally.
     */
    ImeModel horizontalFlip();

    /**
     * This method will create a greyscale image with the user given components like red, blue,
     * green, value, intensity and luma.
     *
     * @param s is the index of the greyscale value component.
     * @return a ppm image that has been converted to greyscale based on the given component.
     */
    ImeModel greyscale(int s);

    /**
     * This method will split the given user image into 3 component images of red, green and blue.
     *
     * @param r is the name of the red component image of the split images.
     * @param g is the name of the green component image of the split images.
     * @param b is the name of the blue component image of the split images.
     * @return a map that contains the split images and their names as a map.
     */
    Map<String, ImeModel> rgbSplit(String r, String g, String b);

    /**
     * This method will combine the 3 component images into a single ppm image.
     *
     * @param r is the name of the red component image.
     * @param g is the name of the green component image.
     * @param b is the name of the blue component image.
     * @return a ppm image that has been combined using the given 3 component images.
     */
    ImeModel rgbCombine(ImeModel r, ImeModel g, ImeModel b);

    /**
     * This will save the file given to it in the path.
     *
     * @param newImagePath is the place where we need to write the file to.
     * @return return the ImeModel.
     */
    ImeModel save(String newImagePath);

    /**
     * Returns the pixel values of the ppm image as a 3D array.
     *
     * @return A 3D array that represents the pixel values of the ppm image.
     */
    int[][][] getPixels();

}
