package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.DepartmentDao;
import SchoolInformationAPI.model.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService<Department, DepartmentDao>{
    public DepartmentService(DepartmentDao dao) {
        super(dao);
    }
}
