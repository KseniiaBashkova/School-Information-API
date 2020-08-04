package rest;

import SchoolInformationAPI.model.Faculty;
import SchoolInformationAPI.rest.FacultyController;
import SchoolInformationAPI.service.FacultyService;
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

public class FacultyControllerTest extends BaseControllerTestRunner {

    @Mock
    private FacultyService facultyServiceMock;

    @InjectMocks
    private FacultyController facultyController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(facultyController);
    }

    @Test
    public void getAllFaculties() throws Exception {
        final ArrayList<Faculty> faculties = (ArrayList<Faculty>) IntStream.range(0, 5).mapToObj(i -> {
            final Faculty faculty = SetTemplate.setFaculty(1, "code", "name");
            return faculty;
        }).collect(Collectors.toList());

        when(facultyServiceMock.findAll()).thenReturn(faculties);

        final MvcResult mvcResult = mockMvc.perform(get("/faculty")).andReturn();
        final ArrayList<Faculty> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(faculties.size(), result.size());

        verify(facultyServiceMock).findAll();
    }

    @Test
    public void getFaculty() throws Exception {
        final Faculty faculty = SetTemplate.setFaculty(1, "code", "name");

        when(facultyServiceMock.find(faculty.getId())).thenReturn(faculty);

        final MvcResult mvcResult = mockMvc.perform(get("/faculty/1")).andReturn();
        final Faculty result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(faculty.getId(), result.getId());
        assertEquals(faculty.getCode(), result.getCode());
        assertEquals(faculty.getName(), result.getName());

        verify(facultyServiceMock).find(result.getId());
    }
}
