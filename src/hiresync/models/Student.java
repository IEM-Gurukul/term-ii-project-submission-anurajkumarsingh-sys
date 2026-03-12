package hiresync.models;

/**
 * Represents a student applying for placements.
 */
public class Student extends User {
    private double cgpa;
    private int backlogs;
    private String department;

    public Student(String id, String name, String email, String password, double cgpa, int backlogs, String department) {
        super(id, name, email, password);
        this.cgpa = cgpa;
        this.backlogs = backlogs;
        this.department = department;
    }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public int getBacklogs() { return backlogs; }
    public void setBacklogs(int backlogs) { this.backlogs = backlogs; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", cgpa=" + cgpa +
                ", backlogs=" + backlogs +
                ", department='" + department + '\'' +
                '}';
    }
}
