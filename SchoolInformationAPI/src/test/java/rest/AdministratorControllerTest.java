package rest;


import SchoolInformationAPI.model.*;
import SchoolInformationAPI.rest.AdministratorController;
import SchoolInformationAPI.service.AdministratorService;
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

public class AdministratorControllerTest extends BaseControllerTestRunner {

    @Mock
    private AdministratorService administratorServiceMock;

    @InjectMocks
    private AdministratorController administratorController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(administratorController);
    }

    @Test
    public void getAllAdministrators() throws Exception {
        final ArrayList<Administrator> administrators = (ArrayList<Administrator>) IntStream.range(0, 5).mapToObj(i -> {
            final SchoolUser schoolUser = SetTemplate.setSchoolUser(i,"test first", "test second",
                    "1234", "test"+i);
            final Administrator administrator = SetTemplate.setAdministrator(schoolUser.getId(),
                    schoolUser.getFirstName(), schoolUser.getSecondName(),
                    schoolUser.getPassword(), schoolUser.getUsername());
            return administrator;
        }).collect(Collectors.toList());

        when(administratorServiceMock.findAll()).thenReturn(administrators);

        final MvcResult mvcResult = mockMvc.perform(get("/administrator")).andReturn();
        final ArrayList<Administrator> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(administrators.size(), result.size());
        verify(administratorServiceMock).findAll();
    }

    @Test
    public void getAdministrator() throws Exception {
        final SchoolUser schoolUser = SetTemplate.setSchoolUser(1,"test first", "test second",
                "1234", "test");
        final Administrator administrator = SetTemplate.setAdministrator(schoolUser.getId(),
                schoolUser.getFirstName(), schoolUser.getSecondName(),
                schoolUser.getPassword(), schoolUser.getUsername());

        when(administratorServiceMock.find(schoolUser.getId())).thenReturn(administrator);

        final MvcResult mvcResult = mockMvc.perform(get("/administrator/1")).andReturn();
        final Administrator result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(administrator.getId(), result.getId());
        assertEquals(administrator.getFirstName(), result.getFirstName());
        assertEquals(administrator.getSecondName(), result.getSecondName());
        assertEquals(administrator.getPassword(), result.getPassword());
        assertEquals(administrator.getUsername(), result.getUsername());
        verify(administratorServiceMock).find(result.getId());
    }
}
