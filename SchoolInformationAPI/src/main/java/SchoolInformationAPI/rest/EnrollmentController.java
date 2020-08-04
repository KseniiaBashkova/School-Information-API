package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.EnrollmentDao;
import SchoolInformationAPI.model.Enrollment;
import SchoolInformationAPI.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/enrollment")
public class EnrollmentController extends BasicController<EnrollmentService, Enrollment, EnrollmentDao> {

    public EnrollmentController(EnrollmentService service) {
        super(service);
    }
}
