package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.SchoolUserDao;
import SchoolInformationAPI.dao.TeacherDao;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseService<Teacher, TeacherDao>{

    @Autowired
    private SchoolUserDao schoolUserDao;

    public TeacherService(TeacherDao dao, SchoolUserDao schoolUserDao) {
        super(dao);
        this.schoolUserDao = schoolUserDao;
    }

    public void remove(Teacher teacher) {
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setId(teacher.getId());
        this.schoolUserDao.remove(schoolUser);
    }
}
