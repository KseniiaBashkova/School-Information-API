package rest;

import SchoolInformationAPI.model.*;
import SchoolInformationAPI.rest.TeacherController;
import SchoolInformationAPI.service.TeacherService;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TeacherControllerTest extends BaseControllerTestRunner {

    @Mock
    private TeacherService teacherServiceMock;

    @InjectMocks
    private TeacherController teacherController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(teacherController);
    }

    @Test
    public void getAllTeachers() throws Exception {
        final Department department = SetTemplate.setDepartment(1, "1", "1");
        final ArrayList<Teacher> teachers = (ArrayList<Teacher>) IntStream.range(0, 5).mapToObj(i -> {
            final SchoolUser schoolUser = SetTemplate.setSchoolUser(i,"test first", "test second",
                    "1234", "test"+i);
            final Teacher teacher = SetTemplate.setTeacher(schoolUser.getId(), department.getId(),
                    "test", schoolUser.getFirstName(), schoolUser.getSecondName(),
                    schoolUser.getPassword(), schoolUser.getUsername());
            return teacher;
        }).collect(Collectors.toList());

        when(teacherServiceMock.findAll()).thenReturn(teachers);

        final MvcResult mvcResult = mockMvc.perform(get("/teacher")).andReturn();
        final ArrayList<Student> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(teachers.size(), result.size());

        verify(teacherServiceMock).findAll();
    }

    @Test
    public void getTeacher() throws Exception {
        final Department department = SetTemplate.setDepartment(1, "1", "1");
        final SchoolUser schoolUser = SetTemplate.setSchoolUser(1,"test first", "test second",
                "1234", "test");
        final Teacher teacher = SetTemplate.setTeacher(schoolUser.getId(), department.getId(),
                "test", schoolUser.getFirstName(), schoolUser.getSecondName(),
                schoolUser.getPassword(), schoolUser.getUsername());

        when(teacherServiceMock.find(teacher.getId())).thenReturn(teacher);

        final MvcResult mvcResult = mockMvc.perform(get("/teacher/1")).andReturn();
        final Teacher result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(teacher.getId(), result.getId());
        assertEquals(teacher.getDepartment(), result.getDepartment());
        assertEquals(teacher.getEmail(), result.getEmail());
        assertEquals(teacher.getFirstName(), result.getFirstName());
        assertEquals(teacher.getSecondName(), result.getSecondName());
        assertEquals(teacher.getPassword(), result.getPassword());
        assertEquals(teacher.getUsername(), result.getUsername());

        verify(teacherServiceMock).find(result.getId());
    }
}
