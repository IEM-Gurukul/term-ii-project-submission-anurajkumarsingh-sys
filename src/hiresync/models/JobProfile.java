package hiresync.models;

/**
 * Represents a job opening posted by a company.
 */
public class JobProfile {
    private String profileId;
    private String title;
    private String description;
    private double minCgpa;
    private int maxBacklogs;
    private double salary;

    public JobProfile(String profileId, String title, String description, double minCgpa, int maxBacklogs, double salary) {
        this.profileId = profileId;
        this.title = title;
        this.description = description;
        this.minCgpa = minCgpa;
        this.maxBacklogs = maxBacklogs;
        this.salary = salary;
    }

    public String getProfileId() { return profileId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getMinCgpa() { return minCgpa; }
    public int getMaxBacklogs() { return maxBacklogs; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "JobProfile{" +
                "title='" + title + '\'' +
                ", salary=" + salary +
                ", minCgpa=" + minCgpa +
                ", maxBacklogs=" + maxBacklogs +
                '}';
    }
}
