package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.model.SchoolUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
public class UserService extends BaseService<SchoolUser, SchoolUserDao> {
    public UserService(SchoolUserDao dao) {
        super(dao);
    }

    @Transactional
    public int persistUser(SchoolUser user) {
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setFirstName(user.getFirstName());
        schoolUser.setPassword(user.getPassword());
        schoolUser.setUsername(user.getUsername());
        schoolUser.setSecondName(user.getSecondName());
        Objects.requireNonNull(schoolUser);
        this.dao.persist(schoolUser);
        return schoolUser.getId();
    }
}
