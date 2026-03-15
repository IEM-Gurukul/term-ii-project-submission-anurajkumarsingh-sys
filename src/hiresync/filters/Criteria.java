package hiresync.filters;

import hiresync.models.Student;
import java.util.List;

/**
 * Interface for the Filter Design Pattern to filter students based on criteria.
 */
public interface Criteria {
    List<Student> meetCriteria(List<Student> students);
}
