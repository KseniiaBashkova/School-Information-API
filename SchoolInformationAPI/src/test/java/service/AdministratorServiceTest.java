package service;

import SchoolInformationAPI.dao.AdministratorDao;
import SchoolInformationAPI.model.Administrator;
import SchoolInformationAPI.service.AdministratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class AdministratorServiceTest {

    private AdministratorDao administratorDao = Mockito.mock(AdministratorDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        AdministratorService administratorService = new AdministratorService(administratorDao);
        Administrator admin = SetTemplate.setAdministrator(1, "firstName",
                "secondName", "pass", "user");
        String savedAdmin = administratorService.persist(admin);

        assertEquals(savedAdmin, String.format("Success: %s has been added to the database", admin.getClass().getSimpleName()));
    }

}
