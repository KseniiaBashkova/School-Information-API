package SchoolInformationAPI.rest;

import SchoolInformationAPI.Exception.NotFoundException;
import SchoolInformationAPI.dao.StudentDao;
import SchoolInformationAPI.model.Enrollment;
import SchoolInformationAPI.model.Event;
import SchoolInformationAPI.model.Student;
import SchoolInformationAPI.rest.util.RestUtils;
import SchoolInformationAPI.service.FacultyService;
import SchoolInformationAPI.service.StudentService;
import SchoolInformationAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/student")
public class StudentController extends BasicController<StudentService, Student, StudentDao> {

    private final FacultyService facultyService;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService service, FacultyService facultyService, UserService userService) {
        super(service);
        this.facultyService = facultyService;
        this.userService = userService;
    }

    /**
     * Create student.
     *
     * @method  POST
     * @param   student
     * @autor   Grigoryev Nikita
     * @date    2019-11-14
     * @return  Response with headers and http status.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Student student) {
        student.setFaculty(facultyService.find(student.getIdFaculty()));
        if (student.getFaculty() == null) {
            throw NotFoundException.create("Faculty", student.getIdFaculty());
        }
        student.setIdUser(userService.persistUser(student));
        this.service.persist(student);
        this.LOG.debug("Created student {}.", student);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", student.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/enrollments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable("id") Integer id) {
        final Student student = service.find(id);
        if (student == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }
        return new ArrayList<>(student.getEnrollment());
    }

    @RequestMapping(value = "/{id}/enrollments/without_mark", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enrollment> getEnrollmentsByStudentIdWithoutMark(@PathVariable("id") Integer id) {

        final Student student = service.find(id);
        if (student == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }
        ArrayList<Enrollment> enrollments = new ArrayList<>(student.getEnrollment());
        ArrayList<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getGrade() == null) {
                result.add(enrollment);
            }
        }

        return result;
    }


    @RequestMapping(value = "/{id}/course/{idCourse}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventByCourseId(@PathVariable("id") Integer id, @PathVariable("idCourse") Integer idCourse) {

        final Student student = service.find(id);
        if (student == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }

        ArrayList<Enrollment> enrollments = new ArrayList<>(student.getEnrollment());
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId_course().equals(idCourse)) {
                return new ArrayList<>(enrollment.getCourse().getEvents());
            }
        }


        return null;
    }


    @RequestMapping(value = "/{id}/day/{day}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventByDay(@PathVariable("id") Integer id, @PathVariable("day") Integer day) {

        final Student student = service.find(id);
        if (student == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }

        ArrayList<Enrollment> enrollments = new ArrayList<>(student.getEnrollment());
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId_course().equals(day)) {

                ArrayList<Event> events = new ArrayList<>(enrollment.getCourse().getEvents());
                ArrayList<Event> result = new ArrayList<>();

                for (Event event : events) {
                    if (event.getDay_in_week().equals(day)) {
                        result.add(event);
                    }
                }

                return result;
            }
        }


        return null;
    }
}



