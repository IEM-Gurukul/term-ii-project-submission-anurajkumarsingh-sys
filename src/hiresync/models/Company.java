package hiresync.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company offering job profiles.
 */
public class Company extends User {
    private String industry;
    private List<JobProfile> jobProfiles;

    public Company(String id, String name, String email, String password, String industry) {
        super(id, name, email, password);
        this.industry = industry;
        this.jobProfiles = new ArrayList<>();
    }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public List<JobProfile> getJobProfiles() { return jobProfiles; }
    public void addJobProfile(JobProfile profile) { this.jobProfiles.add(profile); }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + getName() + '\'' +
                ", industry='" + industry + '\'' +
                ", profilesCount=" + jobProfiles.size() +
                '}';
    }
}
