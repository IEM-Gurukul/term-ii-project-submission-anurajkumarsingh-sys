package hiresync.state;

/**
 * State when a student is in the interview process.
 */
public class InterviewState implements ApplicationState {

    @Override
    public void apply() {
        System.out.println("Already in interview process.");
    }

    @Override
    public void scheduleInterview() {
        System.out.println("Interview already scheduled.");
    }

    @Override
    public void makeOffer() {
        System.out.println("Offer made successfully after interview.");
    }

    @Override
    public String getStatus() {
        return "INTERVIEW_SCHEDULED";
    }
}
