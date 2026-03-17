package hiresync.exceptions;

/**
 * Exception thrown when a student is offered a job they have already accepted or been offered.
 */
public class DuplicateOfferException extends Exception {
    public DuplicateOfferException(String message) {
        super(message);
    }
}
