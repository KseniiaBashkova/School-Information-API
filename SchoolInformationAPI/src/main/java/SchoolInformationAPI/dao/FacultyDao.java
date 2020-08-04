package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Faculty;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FacultyDao extends BaseDao<Faculty> {
    protected FacultyDao() {
        super(Faculty.class);
    }

    public List getCountOfStudentByFacultyId(Faculty faculty) {
        return em.createNamedQuery("Faculty.findCountOfStudentsByFacultyId").setParameter(1, faculty.getId()).getResultList();
    }
}