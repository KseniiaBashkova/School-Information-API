package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Speciality;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialityDao extends BaseDao<Speciality> {
    protected SpecialityDao() {
        super(Speciality.class);
    }
}
