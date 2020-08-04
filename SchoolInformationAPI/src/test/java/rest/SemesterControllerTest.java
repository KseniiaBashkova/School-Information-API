package rest;

import SchoolInformationAPI.model.Semestr;
import SchoolInformationAPI.rest.SemestrController;
import SchoolInformationAPI.service.SemestrService;
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

public class SemesterControllerTest extends BaseControllerTestRunner {

    @Mock
    private SemestrService semesterServiceMock;

    @InjectMocks
    private SemestrController semesterController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(semesterController);
    }

    @Test
    public void getAllSemesters() throws Exception {
        final ArrayList<Semestr> semesters = (ArrayList<Semestr>) IntStream.range(0, 5).mapToObj(i -> {
            final Semestr semester = SetTemplate.setSemester(1, 'l', "code");
            return semester;
        }).collect(Collectors.toList());

        when(semesterServiceMock.findAll()).thenReturn(semesters);

        final MvcResult mvcResult = mockMvc.perform(get("/semestr")).andReturn();
        final ArrayList<Semestr> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(semesters.size(), result.size());

        verify(semesterServiceMock).findAll();
    }

    @Test
    public void getSemester() throws Exception {
        final Semestr semester = SetTemplate.setSemester(1, 'l', "code");

        when(semesterServiceMock.find(semester.getId())).thenReturn(semester);

        final MvcResult mvcResult = mockMvc.perform(get("/semestr/1")).andReturn();
        final Semestr result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(semester.getId(), result.getId());
        assertEquals(semester.getHalf_year(), result.getHalf_year());
        assertEquals(semester.getCode(), result.getCode());

        verify(semesterServiceMock).find(result.getId());
    }
}
