package service;

import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.dao.TeacherDao;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.model.Teacher;
import SchoolInformationAPI.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class TeacherServiceTest {

    private TeacherDao teacherDao = Mockito.mock(TeacherDao.class);
    private SchoolUserDao schoolUserDao = Mockito.mock(SchoolUserDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        TeacherService teacherService = new TeacherService(teacherDao, schoolUserDao);
        Teacher teacher = SetTemplate.setTeacher(1,1, "email",
                "firstName", "secondName", "pass", "userName");
        String savedTeacher = teacherService.persist(teacher);

        assertEquals(savedTeacher, String.format("Success: %s has been added to the database", teacher.getClass().getSimpleName()));
    }
}