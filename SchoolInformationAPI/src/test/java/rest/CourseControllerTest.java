package rest;

import SchoolInformationAPI.model.*;
import SchoolInformationAPI.rest.CourseController;
import SchoolInformationAPI.service.CourseService;
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

public class CourseControllerTest extends BaseControllerTestRunner {

    @Mock
    private CourseService courseServiceMock;

    @InjectMocks
    private CourseController courseController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(courseController);
    }

    @Test
    public void getAllCourses() throws Exception {
        final ArrayList<Course> courses = (ArrayList<Course>) IntStream.range(0, 5).mapToObj(i -> {
            final Course course = SetTemplate.setCourse(i, "code"+i, 1, 1);
            return course;
        }).collect(Collectors.toList());

        when(courseServiceMock.findAll()).thenReturn(courses);

        final MvcResult mvcResult = mockMvc.perform(get("/course")).andReturn();
        final ArrayList<Course> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(courses.size(), result.size());

        verify(courseServiceMock).findAll();
    }

    @Test
    public void getCourse() throws Exception {
        final Course course = SetTemplate.setCourse(1, "code", 1, 1);

        when(courseServiceMock.find(course.getId())).thenReturn(course);

        final MvcResult mvcResult = mockMvc.perform(get("/course/1")).andReturn();
        final Course result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(course.getId(), result.getId());
        assertEquals(course.getCode(), result.getCode());
        assertEquals(course.getCredits(), result.getCredits());

        verify(courseServiceMock).find(result.getId());
    }
}