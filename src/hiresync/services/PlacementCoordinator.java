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

import hiresync.exceptions.IneligibleStudentException;
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

    public void applyStudentToJob(Student student, JobProfile profile) throws IneligibleStudentException {
        List<Student> eligible = getEligibleStudents(profile);
        if (!eligible.contains(student)) {
            throw new IneligibleStudentException("Student " + student.getName() + " is not eligible for " + profile.getTitle());
        }
        student.applyForJob(profile.getProfileId());
    }

    public void scheduleInterview(Student student, JobProfile profile) {
        ApplicationState currentState = student.getApplicationState(profile.getProfileId());
        if (currentState != null) {
            currentState.scheduleInterview();
            student.updateApplicationState(profile.getProfileId(), new InterviewState());
        }
    }

    public void makeOffer(Student student, JobProfile profile) {
        ApplicationState currentState = student.getApplicationState(profile.getProfileId());
        if (currentState != null) {
            currentState.makeOffer();
            student.updateApplicationState(profile.getProfileId(), new OfferedState());
        }
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
