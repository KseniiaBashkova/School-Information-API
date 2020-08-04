package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Semestr;
import org.springframework.stereotype.Repository;

@Repository
public class SemestrDao extends BaseDao<Semestr> {
    protected SemestrDao() {
        super(Semestr.class);
    }
}