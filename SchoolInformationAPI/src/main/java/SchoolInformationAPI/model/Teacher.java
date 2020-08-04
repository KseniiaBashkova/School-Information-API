package SchoolInformationAPI.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "teacher")
@NamedNativeQuery(name = "Teacher.findByDepartment", query = "SELECT * FROM teacher WHERE id_department = ?", resultClass = Teacher.class)
@NamedNativeQuery(name = "Teacher.findDateOfTeacherEventsByTeacherEmail", query =
        "Select date_\n" +
        "From timestamp_\n" +
        "Where id =\n" +
            "(SELECT id_timestamp\n" +
            "FROM event_ JOIN teacher ON (event_.id_teacher = teacher.id_user)\n" +
            "Where teacher.email = ?)", resultClass = Teacher.class)
public class Teacher extends SchoolUser implements IEntity {

    @Column(insertable = false, updatable = false)
    private Integer id_department;
    private String email;
    private Integer id_user;

    public Integer getIdDepartment() {
        return this.id_department;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.id_department = idDepartment;
    }

    /**
     * set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    /**
     * set relationship.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @OneToMany(mappedBy = "teacher")
    @OrderBy("day ASC")
    Set<Event> events;


    /**
     * return event.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     */
    public Set<Event> getEvent(){
        return events;
    }

    /**
     * Returns email.
     *
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     * @return  email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param   email
     * @autor   Nazariy Shukatka
     * @date    2019-11-05
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Return user.
     *
     * @author  Bashkova Kseniia
     * @date    2019-11-16
     */
    public Integer getIdUser() { return id_user; }

    /**
     * Sets id_user.
     *
     * @param   id_user
     * @author  Bashkova Kseniia
     * @date    2019-11-16
     */
    public void setIdUser(Integer id_user) {
        this.id_user = id_user;
    }
}
