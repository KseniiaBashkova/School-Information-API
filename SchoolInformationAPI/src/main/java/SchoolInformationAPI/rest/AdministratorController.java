package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.AdministratorDao;
import SchoolInformationAPI.model.Administrator;
import SchoolInformationAPI.service.AdministratorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/administrator")
public class AdministratorController extends BasicController<AdministratorService, Administrator, AdministratorDao> {

    public AdministratorController(AdministratorService service) {
        super(service);
    }
}
