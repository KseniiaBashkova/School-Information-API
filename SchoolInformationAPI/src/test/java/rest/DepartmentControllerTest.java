package rest;

import SchoolInformationAPI.model.Department;
import SchoolInformationAPI.rest.DepartmentController;
import SchoolInformationAPI.service.DepartmentService;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class DepartmentControllerTest extends BaseControllerTestRunner {

    @Mock
    private DepartmentService departmentServiceMock;

    @InjectMocks
    private DepartmentController departmentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(departmentController);
    }

    @Test
    public void getAllDepartments() throws Exception {
        final ArrayList<Department> departments = (ArrayList<Department>) IntStream.range(0, 5).mapToObj(i -> {
            final Department department = SetTemplate.setDepartment(i, "test code", "test name");
            return department;
        }).collect(Collectors.toList());

        when(departmentServiceMock.findAll()).thenReturn(departments);

        final MvcResult mvcResult = mockMvc.perform(get("/department")).andReturn();
        final ArrayList<Department> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(departments.size(), result.size());

        verify(departmentServiceMock).findAll();
    }

    @Test
    public void getDepartment() throws Exception {
        final Department department = SetTemplate.setDepartment(1, "test code", "test name");

        when(departmentServiceMock.find(department.getId())).thenReturn(department);

        final MvcResult mvcResult = mockMvc.perform(get("/department/1")).andReturn();
        final Department result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(department.getId(), result.getId());
        assertEquals(department.getCode(), result.getCode());
        assertEquals(department.getName(), result.getName());

        verify(departmentServiceMock).find(result.getId());
    }
}
