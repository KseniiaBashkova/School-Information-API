package SchoolInformationAPI.dao;

import SchoolInformationAPI.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao extends BaseDao<Event> {
    protected EventDao() {
        super(Event.class);
    }
}
