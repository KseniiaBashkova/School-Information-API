package service;

import SchoolInformationAPI.dao.EnrollmentDao;
import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.dao.StudentDao;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.model.Student;
import SchoolInformationAPI.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;

import javax.persistence.Access;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class StudentServiceTest {

    private StudentDao studentDao = Mockito.mock(StudentDao.class);
    private EnrollmentDao enrollmentDao = Mockito.mock(EnrollmentDao.class);
    private SchoolUserDao schoolUserDao = Mockito.mock(SchoolUserDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        StudentService studentService = new StudentService(studentDao, enrollmentDao, schoolUserDao);
        Student student = SetTemplate.setStudent(1,1, "email",
                "firstName", "secondName", "pass", "userName");
        String savedStudent = studentService.persist(student);

        assertEquals(savedStudent, String.format("Success: %s has been added to the database", student.getClass().getSimpleName()));
    }

}