package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.CourseDao;
import SchoolInformationAPI.model.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends BaseService<Course, CourseDao> {

    public CourseService(CourseDao dao) {
        super(dao);
    }
}
