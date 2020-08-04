package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.SpecialityDao;
import SchoolInformationAPI.model.Speciality;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService extends BaseService<Speciality, SpecialityDao>{

    public SpecialityService(SpecialityDao dao) {
        super(dao);
    }
}
