package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.SemestrDao;
import SchoolInformationAPI.model.Semestr;
import org.springframework.stereotype.Service;

@Service
public class SemestrService extends BaseService<Semestr, SemestrDao>{

    public SemestrService(SemestrDao dao) {
        super(dao);
    }
}
