package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents the model of the program and the operations to be done on the ppm image
 * sent by the controller to the model.
 */
public class ImeModelImpl implements ImeModel{

    private int width;
    private int height;
    private int maxVal;
    private int[][][] pixels;
    private ImeModel image;

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
     * This is an empty constructor, used to instantiate this class.
     */
    public ImeModelImpl() {
    }

    @Override
    public ImeModel load(String p) throws FileNotFoundException {
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream(p));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File is not found");
        }

        if(p.contains(".ppm")){
            return loadPpm(sc);
        }
        return null;
    }

    /**
     * This will load ppm files.
     *
     * @param sc is the scanner object containing file path.
     * @return will give out ImeModel which represents image
     */
    private ImeModel loadPpm(Scanner sc){
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
        return this;
    }

    @Override
    public ImeModel brighten(Integer value) {
        int[][][] data = this.pixels;
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

    @Override
    public ImeModel verticalFlip() {
        int[][][] data = this.pixels;
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

    @Override
    public ImeModel horizontalFlip() {
        int[][][] data = this.pixels;
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

    @Override
    public ImeModel greyscale(int s) {
        return splitChannel(this, s);
    }

    @Override
    public Map<String, ImeModel> rgbSplit(String r, String g, String b) {
        Map<String, ImeModel> sample = new HashMap<>();
        sample.put(r, splitChannel(image, 0));
        sample.put(g, splitChannel(image, 1));
        sample.put(b, splitChannel(image, 2));
        return sample;
    }

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
    @Override
    public int[][][] getPixels(){
        return this.pixels;
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
                        channelValue = (int) Math.round(luma_value);
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

    @Override
    public ImeModel save(String newImagePath) {
        int[][][] data = this.pixels;
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
}

