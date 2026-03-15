package hiresync.filters;

import hiresync.models.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter students based on a minimum CGPA requirement.
 */
public class CgpaCriteria implements Criteria {
    private double minCgpa;

    public CgpaCriteria(double minCgpa) {
        this.minCgpa = minCgpa;
    }

    @Override
    public List<Student> meetCriteria(List<Student> students) {
        List<Student> eligibleStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getCgpa() >= minCgpa) {
                eligibleStudents.add(student);
            }
        }
        return eligibleStudents;
    }
}
