package hiresync.services;

import hiresync.filters.AndCriteria;
import hiresync.filters.BacklogCriteria;
import hiresync.filters.CgpaCriteria;
import hiresync.filters.Criteria;
import hiresync.models.Company;
import hiresync.models.JobProfile;
import hiresync.models.Student;
import java.util.ArrayList;
import java.util.List;

import hiresync.exceptions.DuplicateOfferException;
import hiresync.exceptions.IneligibleStudentException;
import hiresync.exceptions.InvalidTransitionException;
import hiresync.state.ApplicationState;
import hiresync.state.InterviewState;
import hiresync.state.OfferedState;

/**
 * Singleton class managing the placement process.
 */
public class PlacementCoordinator {
    private static PlacementCoordinator instance;
    private List<Student> students;
    private List<Company> companies;

    private PlacementCoordinator() {
        this.students = new ArrayList<>();
        this.companies = new ArrayList<>();
    }

    public static PlacementCoordinator getInstance() {
        if (instance == null) {
            instance = new PlacementCoordinator();
        }
        return instance;
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void registerCompany(Company company) {
        companies.add(company);
    }

    public void applyStudentToJob(Student student, JobProfile profile) throws IneligibleStudentException, InvalidTransitionException {
        if (student.getApplicationState(profile.getProfileId()) != null) {
            throw new InvalidTransitionException("Already applied for this job profile: " + profile.getTitle());
        }
        List<Student> eligible = getEligibleStudents(profile);
        if (!eligible.contains(student)) {
            throw new IneligibleStudentException("Student " + student.getName() + " is not eligible for " + profile.getTitle());
        }
        student.applyForJob(profile.getProfileId());
    }

    public void scheduleInterview(Student student, JobProfile profile) throws InvalidTransitionException {
        ApplicationState currentState = student.getApplicationState(profile.getProfileId());
        if (currentState == null) {
            throw new InvalidTransitionException("Cannot schedule interview: Student has not applied for this job.");
        }
        
        // The concrete state classes should ideally throw exceptions if transition is invalid
        // For now, we handle the high-level logic here to ensure transitions are strictly followed
        if (!currentState.getStatus().equals("APPLIED")) {
             throw new InvalidTransitionException("Interview can only be scheduled from APPLIED state. Current: " + currentState.getStatus());
        }

        currentState.scheduleInterview();
        student.updateApplicationState(profile.getProfileId(), new InterviewState());
    }

    public void makeOffer(Student student, JobProfile profile) throws InvalidTransitionException, DuplicateOfferException {
        ApplicationState currentState = student.getApplicationState(profile.getProfileId());
        if (currentState == null) {
            throw new InvalidTransitionException("Cannot make offer: Student has not applied for this job.");
        }

        if (!currentState.getStatus().equals("INTERVIEW_SCHEDULED")) {
            throw new InvalidTransitionException("Offer can only be made from INTERVIEW_SCHEDULED state. Current: " + currentState.getStatus());
        }

        // Edge case: check if student already has an offer from ANY company
        for (Company c : companies) {
            for (JobProfile p : c.getJobProfiles()) {
                ApplicationState state = student.getApplicationState(p.getProfileId());
                if (state != null && state.getStatus().equals("OFFERED")) {
                    throw new DuplicateOfferException("Student " + student.getName() + " already holds an offer from another company/profile.");
                }
            }
        }

        currentState.makeOffer();
        student.updateApplicationState(profile.getProfileId(), new OfferedState());
    }

    public List<Student> getEligibleStudents(JobProfile profile) {
        Criteria cgpaCriteria = new CgpaCriteria(profile.getMinCgpa());
        Criteria backlogCriteria = new BacklogCriteria(profile.getMaxBacklogs());
        Criteria eligibleCriteria = new AndCriteria(cgpaCriteria, backlogCriteria);

        return eligibleCriteria.meetCriteria(students);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies);
    }
}
