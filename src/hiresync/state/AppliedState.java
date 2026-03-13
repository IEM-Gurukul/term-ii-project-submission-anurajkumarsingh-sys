package hiresync.state;

/**
 * State when a student has successfully applied for a job.
 */
public class AppliedState implements ApplicationState {
    
    @Override
    public void apply() {
        System.out.println("Already applied for this position.");
    }

    @Override
    public void scheduleInterview() {
        System.out.println("Interview scheduled successfully.");
    }

    @Override
    public void makeOffer() {
        System.out.println("Cannot make offer before interview.");
    }

    @Override
    public String getStatus() {
        return "APPLIED";
    }
}
