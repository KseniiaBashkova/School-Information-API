package SchoolInformationAPI.rest;

import SchoolInformationAPI.Exception.NotFoundException;
import SchoolInformationAPI.dao.TeacherDao;
import SchoolInformationAPI.model.*;
import SchoolInformationAPI.rest.util.RestUtils;
import SchoolInformationAPI.service.DepartmentService;
import SchoolInformationAPI.service.TeacherService;
import SchoolInformationAPI.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/teacher")
public class TeacherController extends BasicController<TeacherService, Teacher, TeacherDao> {

    private final UserService userService;
    private final DepartmentService departmentService;

    public TeacherController(TeacherService service, UserService userService, DepartmentService departmentService) {
        super(service);
        this.userService = userService;
        this.departmentService = departmentService;
    }

    /**
     * Create teacher.
     *
     * @method  POST
     * @param   teacher
     * @autor   Grigoryev Nikita
     * @date    2019-11-14
     * @return  Response entity with headers and http status.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Teacher teacher) {
        teacher.setDepartment(departmentService.find(teacher.getIdDepartment()));
        if (teacher.getDepartment() == null) {
            throw NotFoundException.create("Department", teacher.getIdDepartment());
        }
        teacher.setIdUser(userService.persistUser(teacher));
        service.persist(teacher);
        LOG.debug("Created teacher {}.", teacher);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", teacher.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/event", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getCourseByTeacher(@PathVariable("id") Integer id) {

        final Teacher teacher = service.find(id);
        if (teacher == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }

        return new ArrayList<>(teacher.getEvent());
    }
}
