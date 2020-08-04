package service;

import SchoolInformationAPI.dao.SpecialityDao;
import SchoolInformationAPI.model.Speciality;
import SchoolInformationAPI.service.SpecialityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class SpecialityServiceTest {

    private SpecialityDao specialityDao = Mockito.mock(SpecialityDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        SpecialityService specialityService = new SpecialityService(specialityDao);
        Speciality speciality = SetTemplate.setSpeciality(1,"name", "code");
        String savedSpeciality = specialityService.persist(speciality);

        assertEquals(savedSpeciality, String.format("Success: %s has been added to the database", speciality.getClass().getSimpleName()));
    }

}