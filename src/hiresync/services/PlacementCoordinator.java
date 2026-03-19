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
