package hiresync.state;

/**
 * State when a student has received a job offer.
 */
public class OfferedState implements ApplicationState {

    @Override
    public void apply() {
        System.out.println("Already received an offer for this position.");
    }

    @Override
    public void scheduleInterview() {
        System.out.println("Interview already completed, offer received.");
    }

    @Override
    public void makeOffer() {
        System.out.println("Offer already made.");
    }

    @Override
    public String getStatus() {
        return "OFFERED";
    }
}
