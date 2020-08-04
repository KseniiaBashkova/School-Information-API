package SchoolInformationAPI.service;

import SchoolInformationAPI.dao.EventDao;
import SchoolInformationAPI.model.Event;
import org.springframework.stereotype.Service;

@Service
public class EventService extends BaseService<Event, EventDao> {

    public EventService(EventDao dao) {
        super(dao);
    }
}
