package service;

import SchoolInformationAPI.dao.EventDao;
import SchoolInformationAPI.model.Event;
import SchoolInformationAPI.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import rest.SetTemplate;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class EventServiceTest {

    private EventDao eventDao = Mockito.mock(EventDao.class);

    @Test
    public void performPersist_shouldReturnSuccessNotice() {
        EventService eventService = new EventService(eventDao);
        Event event = SetTemplate.setEvent(1,1, 1, 1, 1);
        String savedEvent = eventService.persist(event);

        assertEquals(savedEvent, String.format("Success: %s has been added to the database", event.getClass().getSimpleName()));
    }

}