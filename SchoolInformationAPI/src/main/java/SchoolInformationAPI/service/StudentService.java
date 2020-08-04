package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.EnrollmentDao;
import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.dao.StudentDao;
import SchoolInformationAPI.model.Enrollment;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService extends BaseService<Student, StudentDao>{

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private SchoolUserDao schoolUserDao;

    public StudentService(StudentDao dao, EnrollmentDao enrollmentDao, SchoolUserDao schoolUserDao) {
        super(dao);
        this.enrollmentDao = enrollmentDao;
        this.schoolUserDao = schoolUserDao;
    }

    public void remove(Student student) {
        ArrayList<Enrollment> enrollments = new ArrayList<>(student.getEnrollment());
        for (Enrollment enrollment : enrollments) {
            enrollmentDao.remove(enrollment);
        }
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setId(student.getId());
        this.schoolUserDao.remove(schoolUser);
    }
}
