import controller.ImeController;
import controller.ImeControllerImpl;
import model.ImeModel;
import model.ImeModelImpl;
import view.ImeView;
import view.ImeViewImpl;

import java.io.FileNotFoundException;

/**
 * This is the main class of the program used to run the program through the command interface.
 */
public class Main {
    /**
     * The main method calls the controller and sends data to the model.
     *
     * @param args is command line arguments.
     * @throws FileNotFoundException when the file is not found, it throws an exception.
     */
    public static void main(String[] args) throws FileNotFoundException {
        ImeModel imeM = new ImeModelImpl();
        ImeView imeV = new ImeViewImpl(System.out);
        ImeController imeC = new ImeControllerImpl(imeM, imeV, System.in);
        imeC.ppmgo();
    }
}