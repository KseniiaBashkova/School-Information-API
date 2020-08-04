package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao extends BaseDao<Department> {
    protected DepartmentDao() {
        super(Department.class);
    }
}
