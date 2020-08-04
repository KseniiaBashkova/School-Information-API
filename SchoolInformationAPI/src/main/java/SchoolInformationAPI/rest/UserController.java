package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController extends BasicController<UserService, SchoolUser, SchoolUserDao> {

    public UserController(UserService service) {
        super(service);
    }
}
