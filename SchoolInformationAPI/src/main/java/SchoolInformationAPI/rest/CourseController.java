package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.CourseDao;
import SchoolInformationAPI.model.Course;
import SchoolInformationAPI.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/course")
public class CourseController extends BasicController<CourseService, Course, CourseDao> {
    public CourseController(CourseService service) {
        super(service);
    }
}
