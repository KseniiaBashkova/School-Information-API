package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.AdministratorDao;
import SchoolInformationAPI.model.Administrator;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService extends BaseService<Administrator, AdministratorDao>{
    public AdministratorService(AdministratorDao dao) {
        super(dao);
    }
}
