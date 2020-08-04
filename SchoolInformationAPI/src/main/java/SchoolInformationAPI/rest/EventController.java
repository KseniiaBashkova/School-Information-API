package SchoolInformationAPI.rest;

import SchoolInformationAPI.dao.EventDao;
import SchoolInformationAPI.model.Event;
import SchoolInformationAPI.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/event")
public class EventController extends BasicController<EventService, Event, EventDao> {

    public EventController(EventService service) {
        super(service);
    }
}
