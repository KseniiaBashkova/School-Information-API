package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.SpecialityDao;
import SchoolInformationAPI.model.Speciality;
import SchoolInformationAPI.service.SpecialityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/speciality")
public class SpecialityController extends BasicController<SpecialityService, Speciality, SpecialityDao> {

    public SpecialityController(SpecialityService service) {
        super(service);
    }
}
