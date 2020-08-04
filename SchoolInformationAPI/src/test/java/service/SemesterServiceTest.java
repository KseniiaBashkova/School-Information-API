package service;

import SchoolInformationAPI.dao.SemestrDao;
import SchoolInformationAPI.model.Semestr;
import SchoolInformationAPI.service.SemestrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class SemesterServiceTest {

    private SemestrDao semesterDao = Mockito.mock(SemestrDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        SemestrService semesterService = new SemestrService(semesterDao);
        Semestr semester = SetTemplate.setSemester(1,'l', "code");
        String savedSemester = semesterService.persist(semester);

        assertEquals(savedSemester, String.format("Success: %s has been added to the database", semester.getClass().getSimpleName()));
    }

}