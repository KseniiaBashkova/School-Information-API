package service;

import SchoolInformationAPI.dao.CourseDao;
import SchoolInformationAPI.model.Course;
import SchoolInformationAPI.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class CourseServiceTest {

    private CourseDao courseDao = Mockito.mock(CourseDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        CourseService courseService = new CourseService(courseDao);
        Course course = SetTemplate.setCourse(1, "code", 1, 1);
        String savedCourse = courseService.persist(course);

        assertEquals(savedCourse, String.format("Success: %s has been added to the database", course.getClass().getSimpleName()));
    }

}