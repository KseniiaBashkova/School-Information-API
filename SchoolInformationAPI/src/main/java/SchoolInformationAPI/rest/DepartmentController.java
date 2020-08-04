package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.DepartmentDao;
import SchoolInformationAPI.model.Department;
import SchoolInformationAPI.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/department")
public class DepartmentController extends BasicController<DepartmentService, Department, DepartmentDao> {

    public DepartmentController(DepartmentService service) {
        super(service);
    }
}
