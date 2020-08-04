package SchoolInformationAPI.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
public class Course extends AbstractEntity implements IEntity {

    private String code;
    private Integer credits;
    private Integer id_department;


    /**
     * set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-16-05
     * @return  code
     */
    @ManyToOne
    @JoinColumn(name = "id_department", insertable = false, updatable = false)
    private Department department;


    /**
     * set relationship with Enrollment.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-16-05
     * @return  code
     */
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Event> events;

    /**
     * Returns code.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param   code
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns credits.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  credits
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * Sets credits.
     *
     * @param   credits
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    /**
     * Returns department.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-05
     * @return  id_department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets department.
     *

     * @autor   Bashkova Kseniia
     * @date    2019-11-05
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


    public Integer getId_department() {
        return id_department;
    }

    public void setId_department(Integer id_department) {
        this.id_department = id_department;
    }
}
