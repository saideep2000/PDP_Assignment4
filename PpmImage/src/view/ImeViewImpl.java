package view;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * This class represents the view of the program that displays the output to the user.
 */
public class ImeViewImpl implements ImeView {

    private OutputStream out;

    /**
     * Constructor for the ImeViewImpl class.
     *
     * @param out is the output from the view methods.
     */
    public ImeViewImpl(OutputStream out) {
        this.out = out;

    }

    /**
     * This method will display the output from the model to the user.
     *
     * @param s is the string value to be displayed.
     */
    @Override
    public void display(String s) {
        PrintStream outStream = new PrintStream(this.out);
        outStream.printf(s + "\n");
    }
}
