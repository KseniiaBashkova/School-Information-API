package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.SemestrDao;
import SchoolInformationAPI.model.Semestr;
import SchoolInformationAPI.service.SemestrService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/semestr")
public class SemestrController extends BasicController<SemestrService, Semestr, SemestrDao> {

    public SemestrController(SemestrService service) {
        super(service);
    }
}
