package controller;

import model.ImeModel;
import view.ImeView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents the controller of the program that takes the input from the user and calls
 * the model functions by sending data based on the requirement of the user.
 */
public class ImeControllerImpl implements ImeController {

    private final ImeModel model;
    private final ImeView view;
    private final InputStream in;
    private final Map<String, Integer> components;
    private final Map<String, ImeModel> ListOfImages;
    private String[] commandsList;


    /**
     * This is a constructor for the ImeControllerImpl class.
     *
     * @param model the model called by the constructor of this program.
     * @param view  the view called by the constructor of this program.
     * @param in    the input stream to take in the input as bytes.
     */
    public ImeControllerImpl(ImeModel model, ImeView view, InputStream in) {
        this.model = model;
        this.view = view;
        this.in = in;
        components = new HashMap<>();
        components.put("red", 0);
        components.put("green", 1);
        components.put("blue", 2);
        components.put("value", 3);
        components.put("luma", 4);
        components.put("intensity", 5);
        ListOfImages = new HashMap<>();
    }

    /**
     * This method will call the necessary private methods by analyzing the input of the user.
     */
    @Override
    public void ppmgo() throws FileNotFoundException {
        Scanner scan = new Scanner(in);
        boolean rem = true;
        Scanner sc;
        while (rem) {
            String s;
            try {
                if (scan.hasNextLine()) {
                    s = scan.nextLine();
                } else {
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println(e);
                break;
            }
            if (s.isEmpty() || s.charAt(0) != '#') {
                execute(s);
            }
        }

    }

    /**
     * In this we check the command given by the vendor and call the required methods.
     *
     * @param s is the input we get from the user.
     */
    private void execute(String s) throws FileNotFoundException {
        commandsList = s.split("\\s+");

        if (commandsList.length == 0) {
            view.display("Type something...");
        }

        switch (commandsList[0].toLowerCase()) {
            case "load":
                load();
                break;
            case "brighten":
                brighten();
                break;
            case "vertical-flip":
                verticalFlip();
                break;
            case "horizontal-flip":
                horizontalFlip();
                break;
            case "greyscale":
                greyscale();
                break;
            case "save":
                save();
                break;
            case "rgb-split":
                rgbSplit();
                break;
            case "rgb-combine":
                rgbCombine();
                break;
            case "run":
                scriptFile();
                break;
            default:
                display("Incorrect Command");
        }
    }

    /**
     * This method will load the image into the hashmap and sends it to the model.
     */
    private void load() {
        if (commandsList.length != 3) {
            display("Command has to be - load filepath/filename savedTo");
        } else {
            if (!this.commandsList[1].contains(".ppm")) {
                view.display("Only Ppm files are allowed !!");
            } else {
                try {
                    display("Loading the image");
                    ListOfImages.put(this.commandsList[2], model.load(this.commandsList[1]));
                    display("Image loaded");
                } catch (FileNotFoundException e) {
                    display(String.format("The image %s doesn't exist !!", this.commandsList[1]));

                }
            }
        }
    }

    /**
     * This method will brighten the given image by the given required value.
     */
    private void brighten() {
        if (commandsList.length != 4) {
            display("Command has to be - brighten value imageName saveTo");
        } else {
            if (checkIfFileExists(commandsList[2])) {
                ListOfImages.put(commandsList[3], ListOfImages.get(commandsList[2]).brighten(Integer.valueOf(commandsList[1])));
                display("Image brightened");
            }
        }

    }

    /**
     * This method will flip the given image vertically.
     */
    private void verticalFlip() {
        if (commandsList.length != 3) {
            display("Command has to be - vertical-flip imageName saveTo");
        } else {
            if (checkIfFileExists(commandsList[1])) {
                ListOfImages.put(commandsList[2], ListOfImages.get(commandsList[1]).verticalFlip());
                display("Image flipped vertically");
            }
        }

    }

    /**
     * This method will flip the given image horizontally.
     */
    private void horizontalFlip() {
        if (commandsList.length != 3) {
            display("Command has to be - horizontal-flip imageName saveTo");
        } else {
            if (checkIfFileExists(commandsList[1])) {
                ListOfImages.put(commandsList[2], ListOfImages.get(commandsList[1]).horizontalFlip());
                display("Image flipped horizontal");
            }
        }
    }

    /**
     * This method will create a greyscale image with the user given components like red, blue,
     * green, value, intensity and luma.
     */
    private void greyscale() {
        if (commandsList.length != 4) {
            display("Command has to be - greyscale value-component imageName saveTo");
        } else {
            if (checkIfFileExists(commandsList[2])) {
                String comp = commandsList[1].split("-")[0].toLowerCase();
                if (components.containsKey(comp)) {
                    ListOfImages.put(commandsList[3], ListOfImages.get(commandsList[2]).greyscale(components.get(comp)));
                    display("Image brightened");
                    display(String.format("greyscale image with the %s component is done", comp));

                } else {
                    display(String.format("%s is not supported yet !!", comp));
                }

            }
        }
    }

    /**
     * This method will save the modified image with the user given name and path.
     */
    private void save() {
        if (commandsList.length != 3) {
            display("Command has to be - save imageName saveTo");
        } else {
            if (checkIfFileExists(commandsList[2])) {
                ListOfImages.get(commandsList[2]).save(commandsList[1]);
            }
        }

    }

    /**
     * This method will split the given user image into 3 component images of red, green and blue.
     */
    private void rgbSplit() {
        if (commandsList.length != 5) {
            display("Command has to be - rgb-split imageName saveToOfRed saveToOfGreen saveToOfBlue");
        } else {
            if (checkIfFileExists(commandsList[1])) {
                Map<String, ImeModel> sample;
                sample = ListOfImages.get(commandsList[1]).rgbSplit(commandsList[2],
                        commandsList[3], commandsList[4]);
                ListOfImages.putAll(sample);
                display(String.format("Splitted into %s1 , %s2 , %s3", commandsList[2],
                        commandsList[3], commandsList[4]));
            }
        }

    }

    /**
     * This method will combine the 3 component images into a single ppm image.
     */
    private void rgbCombine() {
        if (commandsList.length != 5) {
            display("Command has to be - rgb-combine imageName RedImage GreenImage BlueImage");
        } else {
            if (checkIfFileExists(commandsList[2]) && checkIfFileExists(commandsList[3])
                    && checkIfFileExists(commandsList[4])) {
                ListOfImages.put(commandsList[1], model.rgbCombine(ListOfImages.get(commandsList[2]),
                        ListOfImages.get(commandsList[3]), ListOfImages.get(commandsList[4])));
                display(String.format("combined into %s1", commandsList[1]));
            }
        }
    }

    /**
     * This method will send the output to the view of the program.
     *
     * @param s is the message to be sent to the view.
     */
    private void display(String s) {
        view.display(s);
    }


    /**
     * This method will check whether the file exists or not.
     *
     * @param i is the name of the image.
     * @return a boolean whether the image exists or not.
     */
    private Boolean checkIfFileExists(String i) {
        if (ListOfImages.containsKey(i)) {
            return true;
        } else {
            display(String.format("The image %s doesn't exist !!", i));
            return false;
        }
    }

    /**
     * This will read a file given by the customer as a string.
     *
     * @throws FileNotFoundException will throw exception when there is no file in the path given.
     */
    private void scriptFile() throws FileNotFoundException {
        String s = commandsList[1];
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream(s));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("The file %s doesn't exist !!", s));
        }
        //read the file line by line, and populate a string. This will throw away any comment lines
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            if (s.charAt(0) != '#') {
                execute(s);
            }
        }
    }

}
