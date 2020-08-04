package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.FacultyDao;
import SchoolInformationAPI.model.Faculty;
import SchoolInformationAPI.model.Student;
import org.springframework.stereotype.Service;

@Service
public class FacultyService extends BaseService<Faculty, FacultyDao> {
    public FacultyService(FacultyDao dao) {
        super(dao);
    }

    public Integer getCountOfStudentByFacultyId(Faculty faculty) {
        return this.dao.getCountOfStudentByFacultyId(faculty).size();
    }
}
