package service;

import SchoolInformationAPI.dao.DepartmentDao;
import SchoolInformationAPI.model.Department;
import SchoolInformationAPI.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class DepartmentServiceTest {

    private DepartmentDao departmentDao = Mockito.mock(DepartmentDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        DepartmentService departmentService = new DepartmentService(departmentDao);
        Department department = SetTemplate.setDepartment(1,"code", "name");
        String savedDepartment = departmentService.persist(department);

        assertEquals(savedDepartment, String.format("Success: %s has been added to the database", department.getClass().getSimpleName()));
    }

}