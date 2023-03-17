package controller;

import java.io.FileNotFoundException;

/**
 * This interface represents a set of operations in the controller of the program.
 */
public interface ImeController {
    /**
     * This method will call the necessary private methods by analyzing the input of the user.
     *
     * @throws FileNotFoundException is thrown when the required file is not found.
     */
    void ppmgo() throws FileNotFoundException;
}
