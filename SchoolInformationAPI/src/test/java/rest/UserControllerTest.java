package rest;

import SchoolInformationAPI.rest.UserController;
import SchoolInformationAPI.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import SchoolInformationAPI.model.SchoolUser;

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

public class UserControllerTest extends BaseControllerTestRunner {

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(userController);
    }

    @Test
    public void getAllUsers() throws Exception {
        final ArrayList<SchoolUser> users = (ArrayList<SchoolUser>) IntStream.range(0, 5).mapToObj(i -> {
            final SchoolUser schoolUser = SetTemplate.setSchoolUser(i,"test first", "test second",
                    "1234", "test"+i);
            return schoolUser;
        }).collect(Collectors.toList());

        when(userServiceMock.findAll()).thenReturn(users);

        final MvcResult mvcResult = mockMvc.perform(get("/user")).andReturn();
        final ArrayList<SchoolUser> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(users.size(), result.size());

        verify(userServiceMock).findAll();
    }

    @Test
    public void getUser() throws Exception {
        final SchoolUser schoolUser = SetTemplate.setSchoolUser(1,"test first", "test second",
                    "1234", "test");

        when(userServiceMock.find(schoolUser.getId())).thenReturn(schoolUser);

        final MvcResult mvcResult = mockMvc.perform(get("/user/1")).andReturn();
        final SchoolUser result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(schoolUser.getId(), result.getId());
        assertEquals(schoolUser.getFirstName(), result.getFirstName());
        assertEquals(schoolUser.getSecondName(), result.getSecondName());
        assertEquals(schoolUser.getPassword(), result.getPassword());
        assertEquals(schoolUser.getUsername(), result.getUsername());

        verify(userServiceMock).find(schoolUser.getId());
    }
}
