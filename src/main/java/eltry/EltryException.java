package eltry;

/**
 * Custom exception class for the Eltry application.
 * Thrown when an error occurs related to tasks or command parsing.
 */
public class EltryException extends Exception {

    /**
     * Constructs a new EltryException with the specified detail message.
     *
     * @param message the detail message describing the error
     */
    public EltryException(String message) {
        super(message);
    }
}
