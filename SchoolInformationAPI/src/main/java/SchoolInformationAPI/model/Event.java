package SchoolInformationAPI.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "event_")
@NamedNativeQuery(name = "Event.findRoomOfEventByCodeOfCourse", query =
        "SELECT room" +
        "FROM event_" +
        "WHERE id_course = \n" +
            "(SELECT id\n" +
            "FROM course\n" +
            "WHERE code = 'test')", resultClass = Event.class)
@NamedNativeQuery(name = "Event.findDateOfEventByCourseId", query =
        "SELECT date_\n" +
        "FROM timestamp_\n" +
        "WHERE id = \n" +
            "(SELECT id_timestamp\n" +
            "FROM event_ JOIN course ON (event_.id_semestr = course.id)\n" +
            "WHERE id_course = 1)", resultClass = Event.class)
public class Event extends AbstractEntity implements IEntity {

    private Integer id_semestr;
    private Integer id_course;
    private Integer id_teacher;
    private Integer day_in_week;
    private String room;
    @Column(name = "time_")
    private Date time;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_course", insertable = false, updatable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_semestr", insertable = false, updatable = false)
    private Semestr semestr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_teacher", insertable = false, updatable = false)
    private Teacher teacher;


    public Integer getId_semestr() {
        return id_semestr;
    }

    public void setId_semestr(Integer id_semestr) {
        this.id_semestr = id_semestr;
    }

    public Integer getId_course() {
        return id_course;
    }

    public void setId_course(Integer id_course) {
        this.id_course = id_course;
    }

    public Integer getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Integer id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Integer getDay_in_week() {
        return day_in_week;
    }

    public void setDay_in_week(Integer day_in_week) {
        this.day_in_week = day_in_week;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
