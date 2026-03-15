package hiresync.filters;

import hiresync.models.Student;
import java.util.List;

/**
 * Combines two criteria using a logical AND operation.
 */
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Student> meetCriteria(List<Student> students) {
        List<Student> firstCriteriaStudents = criteria.meetCriteria(students);
        return otherCriteria.meetCriteria(firstCriteriaStudents);
    }
}
