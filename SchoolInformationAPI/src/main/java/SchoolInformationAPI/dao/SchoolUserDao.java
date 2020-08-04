package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.SchoolUser;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolUserDao extends BaseDao<SchoolUser> {

    protected SchoolUserDao() {
        super(SchoolUser.class);
    }

    public void remove(SchoolUser schoolUser) {
        try {
            em.createNativeQuery("DELETE FROM school_user WHERE id = ?").setParameter(1, schoolUser.getId()).executeUpdate();
        } finally {
            em.close();
        }
    }
}
