package rest;

import SchoolInformationAPI.model.*;
import SchoolInformationAPI.rest.EventController;
import SchoolInformationAPI.service.EventService;
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

public class EventControllerTest extends BaseControllerTestRunner {

    @Mock
    private EventService eventServiceMock;

    @InjectMocks
    private EventController eventController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp(eventController);
    }

    @Test
    public void getAllEvents() throws Exception {
        final ArrayList<Event> events = (ArrayList<Event>) IntStream.range(0, 5).mapToObj(i -> {
            final Event event = SetTemplate.setEvent(i, i, i, i, i);
            return event;
        }).collect(Collectors.toList());

        when(eventServiceMock.findAll()).thenReturn(events);

        final MvcResult mvcResult = mockMvc.perform(get("/event")).andReturn();
        final ArrayList<Event> result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(events.size(), result.size());

        verify(eventServiceMock).findAll();
    }

    @Test
    public void getEvent() throws Exception {
        final Event event = SetTemplate.setEvent(1, 1, 1, 1, 1);

        when(eventServiceMock.find(event.getId())).thenReturn(event);

        final MvcResult mvcResult = mockMvc.perform(get("/event/1")).andReturn();
        final Event result = readValue(mvcResult, new TypeReference<>() {});

        assertEquals(event.getId(), result.getId());
        assertEquals(event.getId_semestr(), result.getId_semestr());
        assertEquals(event.getId_course(), result.getId_course());
        assertEquals(event.getId_teacher(), result.getId_teacher());
        assertEquals(event.getDay_in_week(), result.getDay_in_week());

        verify(eventServiceMock).find(result.getId());
    }
}
