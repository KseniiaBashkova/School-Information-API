package SchoolInformationAPI.rest;

import SchoolInformationAPI.Exception.NotFoundException;
import SchoolInformationAPI.dao.FacultyDao;
import SchoolInformationAPI.model.Enrollment;
import SchoolInformationAPI.model.Event;
import SchoolInformationAPI.model.Faculty;
import SchoolInformationAPI.model.Student;
import SchoolInformationAPI.service.FacultyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/faculty")
public class FacultyController extends BasicController<FacultyService, Faculty, FacultyDao> {

    public FacultyController(FacultyService service) {
        super(service);
    }

    @RequestMapping(value = "/{id}/student_count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEventByCourseId(@PathVariable("id") Integer id) {

        final Faculty faculty = service.find(id);
        if (faculty == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }

        return "Students on faculty " + faculty.getName() + " : " + this.service.getCountOfStudentByFacultyId(faculty);
    }
}
