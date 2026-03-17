package hiresync.exceptions;

/**
 * Exception thrown when a student does not meet the eligibility criteria for a job.
 */
public class IneligibleStudentException extends Exception {
    public IneligibleStudentException(String message) {
        super(message);
    }
}
