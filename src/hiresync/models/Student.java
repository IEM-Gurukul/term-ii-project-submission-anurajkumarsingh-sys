package hiresync.models;

import hiresync.state.ApplicationState;
import hiresync.state.AppliedState;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a student applying for placements.
 */
public class Student extends User {
    private double cgpa;
    private int backlogs;
    private String department;
    private Map<String, ApplicationState> applicationStatuses; // Map<JobProfileId, State>

    public Student(String id, String name, String email, String password, double cgpa, int backlogs, String department) {
        super(id, name, email, password);
        this.cgpa = cgpa;
        this.backlogs = backlogs;
        this.department = department;
        this.applicationStatuses = new HashMap<>();
    }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public int getBacklogs() { return backlogs; }
    public void setBacklogs(int backlogs) { this.backlogs = backlogs; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public void applyForJob(String jobProfileId) {
        applicationStatuses.put(jobProfileId, new AppliedState());
    }

    public ApplicationState getApplicationState(String jobProfileId) {
        return applicationStatuses.get(jobProfileId);
    }

    public void updateApplicationState(String jobProfileId, ApplicationState newState) {
        applicationStatuses.put(jobProfileId, newState);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", cgpa=" + cgpa +
                ", backlogs=" + backlogs +
                ", department='" + department + '\'' +
                ", applications=" + applicationStatuses.size() +
                '}';
    }
}
