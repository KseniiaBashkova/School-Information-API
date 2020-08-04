package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDao extends BaseDao<Course> {
    protected CourseDao() {
        super(Course.class);
    }

}
