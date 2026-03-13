package hiresync.state;

/**
 * Interface for the State Design Pattern to handle student application lifecycle.
 */
public interface ApplicationState {
    void apply();
    void scheduleInterview();
    void makeOffer();
    String getStatus();
}
