package hiresync.exceptions;

/**
 * Exception thrown when an invalid state transition is attempted in the application process.
 */
public class InvalidTransitionException extends Exception {
    public InvalidTransitionException(String message) {
        super(message);
    }
}
