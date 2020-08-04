package service;

import SchoolInformationAPI.dao.FacultyDao;
import SchoolInformationAPI.model.Faculty;
import SchoolInformationAPI.service.FacultyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class FacultyServiceTest {

    private FacultyDao facultyDao = Mockito.mock(FacultyDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        FacultyService facultyService = new FacultyService(facultyDao);
        Faculty faculty = SetTemplate.setFaculty(1,"code", "name");
        String savedFaculty = facultyService.persist(faculty);

        assertEquals(savedFaculty, String.format("Success: %s has been added to the database", faculty.getClass().getSimpleName()));
    }

}