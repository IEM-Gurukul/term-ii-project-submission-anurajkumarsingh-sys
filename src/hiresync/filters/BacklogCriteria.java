package hiresync.filters;

import hiresync.models.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter students based on a maximum allowable backlogs.
 */
public class BacklogCriteria implements Criteria {
    private int maxBacklogs;

    public BacklogCriteria(int maxBacklogs) {
        this.maxBacklogs = maxBacklogs;
    }

    @Override
    public List<Student> meetCriteria(List<Student> students) {
        List<Student> eligibleStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getBacklogs() <= maxBacklogs) {
                eligibleStudents.add(student);
            }
        }
        return eligibleStudents;
    }
}
