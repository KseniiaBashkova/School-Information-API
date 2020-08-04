package rest;

import SchoolInformationAPI.model.Faculty;
import SchoolInformationAPI.service.FacultyService;
import com.fasterxml.jackson.core.type.TypeReference;
import SchoolInformationAPI.model.SchoolUser;
import SchoolInformationAPI.model.Student;
import SchoolInformationAPI.rest.StudentController;
import SchoolInformationAPI.service.StudentService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest extends BaseControllerTestRunner {

    @Mock
    private StudentService studentServiceMock;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(studentController);
    }

    @Test
    public void getAllStudents() throws Exception {
        final Faculty faculty = SetTemplate.setFaculty(1, "1" , "testFaculty");
        final ArrayList<Student> students = (ArrayList<Student>) IntStream.range(0, 5).mapToObj(i -> {
            final SchoolUser schoolUser = SetTemplate.setSchoolUser(i,"test first", "test second",
                    "1234", "test"+i);
            final Student student = SetTemplate.setStudent(schoolUser.getId(), faculty.getId(),
                    "test", schoolUser.getFirstName(), schoolUser.getSecondName(),
                    schoolUser.getPassword(), schoolUser.getUsername());
            return student;
        }).collect(Collectors.toList());

        when(studentServiceMock.findAll()).thenReturn(students);

        final MvcResult mvcResult = mockMvc.perform(get("/student")).andReturn();
        final ArrayList<Student> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(students.size(), result.size());

        verify(studentServiceMock).findAll();
    }

    @Test
    public void getStudent() throws Exception {
        final Faculty faculty = SetTemplate.setFaculty(1, "1" , "testFaculty");
        final SchoolUser schoolUser = SetTemplate.setSchoolUser(1,"test first", "test second",
                "1234", "test");
        final Student student = SetTemplate.setStudent(schoolUser.getId(), faculty.getId(),
                "test", schoolUser.getFirstName(), schoolUser.getSecondName(),
                schoolUser.getPassword(), schoolUser.getUsername());

        when(studentServiceMock.find(student.getId())).thenReturn(student);

        final MvcResult mvcResult = mockMvc.perform(get("/student/1")).andReturn();
        final Student result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(student.getId(), result.getId());
        assertEquals(student.getIdFaculty(), result.getIdFaculty());
        assertEquals(student.getEmail(), result.getEmail());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getSecondName(), result.getSecondName());
        assertEquals(student.getPassword(), result.getPassword());
        assertEquals(student.getUsername(), result.getUsername());

        verify(studentServiceMock).find(student.getId());
    }

}
