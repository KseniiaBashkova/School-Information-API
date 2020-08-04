package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Enrollment;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public class EnrollmentDao extends BaseDao<Enrollment> {
    protected EnrollmentDao() {
        super(Enrollment.class);
    }

    @Override
    public List<Enrollment> findAll() {
        try {
            return em.createNativeQuery("SELECT * " +
                    "FROM enrollment").getResultList();
        } finally {
            em.close();
        }
    }
}
