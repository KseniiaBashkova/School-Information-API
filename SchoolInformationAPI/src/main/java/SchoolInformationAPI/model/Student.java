package SchoolInformationAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Table(name = "student")
@NamedNativeQuery(name = "Student.findByEmail", query = "SELECT * FROM student WHERE email = ?", resultClass = Student.class)
public class Student extends SchoolUser implements IEntity {

    @Column(insertable = false, updatable = false)
    private Integer id_faculty;
    private Integer id_user;
    private String email;

    public Integer getIdFaculty() {
        return this.id_faculty;
    }

    public void setIdFaculty(Integer facultyId) {
        this.id_faculty = facultyId;
    }

    /**
     * set relationship with Faculty.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-16-05
     * @return  code
     */
    @ManyToOne
    @JoinColumn(name = "id_faculty")
    private Faculty faculty;

    /**
     * set relationship with Enrollment.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  code
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments;

//

    /**
     * Returns id_user.
     *
     * @author  Nikita Grigoryev
     * @date    2019-11-13
     * @return  id_user
     */
    public Integer getIdUser() { return id_user; }

    /**
     * Sets id_user.
     *
     * @param   id_user
     * @author  Nikita Grigoryev
     * @date    2019-11-13
     */
    public void setIdUser(Integer id_user) {
        this.id_user = id_user;
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
     * Returns faculty.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     * @return  id_faculty
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Sets faculty.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-16
     */
    public void setFaculty(Faculty faculty) {

        this.faculty = faculty;
    }

    /**
     * return enrollments.
     *
     * @autor   Bashkova Kseniia
     * @date    2019-11-05
     */
    public Set<Enrollment> getEnrollment(){
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
