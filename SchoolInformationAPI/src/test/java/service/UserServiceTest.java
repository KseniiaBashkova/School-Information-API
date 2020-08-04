package service;

import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    private SchoolUserDao userDao = Mockito.mock(SchoolUserDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        UserService userService = new UserService(userDao);
        SchoolUser schoolUser = SetTemplate.setSchoolUser(1,"firstName", "secondName",
                "pass", "userName");
        String savedUser = userService.persist(schoolUser);

        assertEquals(savedUser, String.format("Success: %s has been added to the database", schoolUser.getClass().getSimpleName()));
    }

}