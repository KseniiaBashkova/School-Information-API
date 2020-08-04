package rest;

import SchoolInformationAPI.model.Speciality;
import SchoolInformationAPI.rest.SpecialityController;
import SchoolInformationAPI.service.SpecialityService;
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

public class SpecialityControllerTest extends BaseControllerTestRunner {

    @Mock
    private SpecialityService specialityServiceMock;

    @InjectMocks
    private SpecialityController specialityController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(specialityController);
    }

    @Test
    public void getAllSpecialities() throws Exception {
        final ArrayList<Speciality> specialities = (ArrayList<Speciality>) IntStream.range(0, 5).mapToObj(i -> {
            final Speciality speciality = SetTemplate.setSpeciality(1, "name", "code");
            return speciality;
        }).collect(Collectors.toList());

        when(specialityServiceMock.findAll()).thenReturn(specialities);

        final MvcResult mvcResult = mockMvc.perform(get("/speciality")).andReturn();
        final ArrayList<Speciality> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(specialities.size(), result.size());

        verify(specialityServiceMock).findAll();
    }

    @Test
    public void getSpeciality() throws Exception {
        final Speciality speciality = SetTemplate.setSpeciality(1, "name", "code");

        when(specialityServiceMock.find(speciality.getId())).thenReturn(speciality);

        final MvcResult mvcResult = mockMvc.perform(get("/speciality/1")).andReturn();
        final Speciality result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(speciality.getId(), result.getId());
        assertEquals(speciality.getName(), result.getName());
        assertEquals(speciality.getCode(), result.getCode());

        verify(specialityServiceMock).find(result.getId());
    }
}
